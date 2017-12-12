package com.xmg.p2p.business.service.impl;

import com.xmg.p2p.base.domain.Account;
import com.xmg.p2p.base.page.PageResult;
import com.xmg.p2p.base.service.IAccountService;
import com.xmg.p2p.base.util.BidConst;
import com.xmg.p2p.base.util.UserContext;
import com.xmg.p2p.business.domain.BidRequest;
import com.xmg.p2p.business.domain.PaymentSchedule;
import com.xmg.p2p.business.domain.PaymentScheduleDetail;
import com.xmg.p2p.business.domain.SystemAccount;
import com.xmg.p2p.business.query.PaymentScheduleQueryObject;
import com.xmg.p2p.business.service.*;
import com.xmg.p2p.business.util.CalculatetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmg.p2p.business.mapper.PaymentScheduleMapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentScheduleServiceImpl implements IPaymentScheduleService {
	@Autowired
	private PaymentScheduleMapper paymentScheduleMapper;
	@Autowired
	private IAccountService accountService;
	@Autowired
	private IPaymentScheduleDetailService paymentScheduleDetailService;
	@Autowired
	private IAccountFlowService accountFlowService;
	@Autowired
	private ISystemAccountService systemAccountService;
	@Autowired
	private ISystemAccountFlowService systemAccountFlowService;
	@Autowired
	private IBidRequestService bidRequestService;
	@Autowired
	private IBidService bidService;

	@Override
	public int save(PaymentSchedule paymentSchedule) {
		return paymentScheduleMapper.insert(paymentSchedule);
	}

	@Override
	public int update(PaymentSchedule paymentSchedule) {
		return paymentScheduleMapper.updateByPrimaryKey(paymentSchedule);
	}

	@Override
	public PageResult queryPage(PaymentScheduleQueryObject qo) {
		Long count = paymentScheduleMapper.queryPageCount(qo);
		if(count<=0){
		    return PageResult.empty(qo.getPageSize());
		}
		List data = paymentScheduleMapper.queryPageData(qo);
		return new PageResult(data,count.intValue(),qo.getCurrentPage(),qo.getPageSize());
	}

	@Override
	public void returnMoney(Long id) {
		//还款有哪些限制.?
		PaymentSchedule ps = this.paymentScheduleMapper.selectByPrimaryKey(id);
		Account account = accountService.getCurrent();
		if(ps!=null && ps.getState()== BidConst.PAYMENT_STATE_NORMAL&&//1.还款对象的状态需要是正常待还
				account.getUsableAmount().compareTo(ps.getTotalAmount())>=0&&//2.当前用户的账户余额>=该期需要还款的钱
				UserContext.getCurrent().getId().equals(ps.getBorrowUser().getId())//3.当前用户需要是该期的还款人
				){
			//对于还款对象和还款明细对象需要做什么操作?
			//需要设置还款对象的还款日期payDate
			ps.setPayDate(new Date());
			//设置还款对象的状态--->还清
			ps.setState(BidConst.PAYMENT_STATE_DONE);
			this.update(ps);
			//设置还款明细中的还款日期
			paymentScheduleDetailService.updateState(ps.getId(),ps.getPayDate());

			//对于借款人账户有什么变化?
			//借款人的可用金额减少,待还本息减少,剩余授信额度增加(还款本金),生成还款成功的流水
			account.setUsableAmount(account.getUsableAmount().subtract(ps.getTotalAmount()));
			account.setUnReturnAmount(account.getUnReturnAmount().subtract(ps.getTotalAmount()));
			account.setRemainBorrowLimit(account.getRemainBorrowLimit().add(ps.getPrincipal()));
			accountService.update(account);
			accountFlowService.createReturnMoneyFlow(account,ps.getTotalAmount());
			//对于投资人账户有什么变化
			//获取到所有的投资人,找到投资人的账户,可用金额增加,待收本金和待收利息减少.生成回款流水

			Map<Long,Account> accountMap = new HashMap<Long,Account>();
			Account bidUserAccount;
			Long bidUserId;
			SystemAccount systemAccount = systemAccountService.selectCurrent();
			for(PaymentScheduleDetail psd:ps.getDetails()){
				bidUserId = psd.getInvestorId();
				bidUserAccount = accountMap.get(bidUserId);
				if(bidUserAccount==null){
					bidUserAccount = accountService.get(bidUserId);
					accountMap.put(bidUserId,bidUserAccount);
				}
				bidUserAccount.setUsableAmount(bidUserAccount.getUsableAmount().add(psd.getTotalAmount()));
				bidUserAccount.setUnReceivePrincipal(bidUserAccount.getUnReceivePrincipal().subtract(psd.getPrincipal()));
				bidUserAccount.setUnReceiveInterest(bidUserAccount.getUnReceiveInterest().subtract(psd.getInterest()));
				accountFlowService.createGainReturnMoneyFlow(bidUserAccount,psd.getTotalAmount());
				//支付平台利息管理费,投资人的账户可用金额减少,生成支付平台利息费的流水
				BigDecimal interestManagerCharge = CalculatetUtil.calInterestManagerCharge(psd.getInterest());
				bidUserAccount.setUsableAmount(bidUserAccount.getUsableAmount().subtract(interestManagerCharge));
				accountFlowService.createPayInterestManagerChargeFlow(bidUserAccount,interestManagerCharge);
				//系统账户收取利息管理费,可用金额增加,生成收取利息管理费的流水
				systemAccount.setUsableAmount(systemAccount.getUsableAmount().add(interestManagerCharge));
				systemAccountFlowService.createReceiveInterestManagerChargeFlow(systemAccount,interestManagerCharge);
			}
			//对账户进行统一更新
			for(Account buAccount:accountMap.values()){
				accountService.update(buAccount);
			}
			//对系统账户进行统一的更新
			systemAccountService.update(systemAccount);
			//怎么判断这次的借款已经都还清了?
			List<PaymentSchedule> paymentSchedules = this.queryListByBidRequestId(ps.getBidRequestId());
			//通过还款对象---->借款对象Id---->属于该借款的所有还款对象集合
			//遍历这个集合,判断每一个还款是否都是属于还清的状态.
			boolean isAllReturn = true;
			for(PaymentSchedule paymentSchedule:paymentSchedules){
				if(paymentSchedule.getState()!=BidConst.PAYMENT_STATE_DONE){
					isAllReturn = false;
				}
			}
			//如果是,所有的都还清了
			//所有的还款都还了,借款对象和投标对象需要有哪些变化了?
			if(isAllReturn){
				//修改借款对象的状态---->已完成
				BidRequest bidRequest = bidRequestService.get(ps.getBidRequestId());
				bidRequest.setBidRequestState(BidConst.BIDREQUEST_STATE_COMPLETE_PAY_BACK);
				bidRequestService.update(bidRequest);
				//修改投标对象的状态---->已完成
				bidService.updateState(ps.getBidRequestId(),BidConst.BIDREQUEST_STATE_COMPLETE_PAY_BACK);
			}

		}
	}

	@Override
	public List<PaymentSchedule> queryListByBidRequestId(Long bidRequestId) {
		return this.paymentScheduleMapper.queryListByBidRequestId(bidRequestId);
	}
}
