package com.xmg.p2p.business.service.impl;

import com.xmg.p2p.base.domain.Account;
import com.xmg.p2p.base.domain.Userinfo;
import com.xmg.p2p.base.page.PageResult;
import com.xmg.p2p.base.service.IAccountService;
import com.xmg.p2p.base.service.IUserinfoService;
import com.xmg.p2p.base.util.BidConst;
import com.xmg.p2p.base.util.BitStatesUtils;
import com.xmg.p2p.base.util.UserContext;
import com.xmg.p2p.business.domain.*;
import com.xmg.p2p.business.query.BidRequestQueryObject;
import com.xmg.p2p.business.service.*;
import com.xmg.p2p.business.util.CalculatetUtil;
import com.xmg.p2p.business.util.DecimalFormatUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmg.p2p.business.mapper.BidRequestMapper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class BidRequestServiceImpl implements IBidRequestService {
	@Autowired
	private BidRequestMapper bidRequestMapper;
	@Autowired
	private IUserinfoService userinfoService;
	@Autowired
	private IAccountService accountService;
	@Autowired
	private IBidRequestAuditHistoryService bidRequestAuditHistoryService;
	@Autowired
	private IBidService bidService;
	@Autowired
	private IAccountFlowService accountFlowService;
	@Autowired
	private ISystemAccountService systemAccountService;
	@Autowired
	private ISystemAccountFlowService systemAccountFlowService;
	@Autowired
	private IPaymentScheduleService paymentScheduleService;
	@Autowired
	private IPaymentScheduleDetailService paymentScheduleDetailService;
	@Override
	public int save(BidRequest bidRequest) {
		return bidRequestMapper.insert(bidRequest);
	}

	@Override
	public BidRequest get(Long id) {
		return bidRequestMapper.selectByPrimaryKey(id);
	}

	@Override
	public int update(BidRequest bidRequest) {
		int count = bidRequestMapper.updateByPrimaryKey(bidRequest);
		if(count<=0){
			throw new RuntimeException("乐观锁异常,bidRequestId="+bidRequest.getId());
		}
		return count;
	}

	@Override
	public boolean canApplyBorrow(Userinfo userinfo) {
		if(userinfo.getIsBasicInfo()&&//基本信息认证
				userinfo.getIsVedioAuth()&&//视频认证
				userinfo.getIsRealAuth()&&//实名认证
				userinfo.getScore()>= BidConst.CREDIT_BORROW_SCORE//信用材料分数>信用借款所需最低分数30分
				){
			return true;
		}
		return false;
	}

	@Override
	public void apply(BidRequest bidRequest) {
		//1.判断用户是否有借款资格(实名认证,基本资料认证,视频认证,分数>30分)
		//2.系统最小借款金额<=借款金额<=用户的剩余授信额度
		//3.系统最小的利息<=利息<=系统最大的利息
		//4.最小投标>=系统最小投标
		//5.用户没有正在执行的借款申请
		Userinfo userinfo = userinfoService.getCurrent();
		Account account = accountService.getCurrent();
		if(this.canApplyBorrow(userinfo)&&//借款资格
				!userinfo.getHasBidRequestProcess()&&//没有正在借款的申请
				bidRequest.getBidRequestAmount().compareTo(BidConst.SMALLEST_BIDREQUEST_AMOUNT)>=0&&//借款金额>=系统最小借款金额
				bidRequest.getBidRequestAmount().compareTo(account.getRemainBorrowLimit())<=0&&//借款金额<=用户的剩余授信额度
				bidRequest.getCurrentRate().compareTo(BidConst.SMALLEST_CURRENT_RATE)>=0&&//利息>=系统最小利息
				bidRequest.getCurrentRate().compareTo(BidConst.MAX_CURRENT_RATE)<=0&&//利息<=系统最大利息
				bidRequest.getMinBidAmount().compareTo(BidConst.SMALLEST_BID_AMOUNT)>=0
				){
			//给借款对象设置相关的信息
			BidRequest br = new BidRequest();
			br.setApplyTime(new Date());
			br.setBidRequestAmount(bidRequest.getBidRequestAmount());
			br.setBidRequestState(BidConst.BIDREQUEST_STATE_PUBLISH_PENDING);
			br.setBidRequestType(BidConst.BIDREQUEST_TYPE_NORMAL);
			br.setCreateUser(UserContext.getCurrent());
			br.setCurrentRate(bidRequest.getCurrentRate());
			br.setDescription(bidRequest.getDescription());
			br.setDisableDays(bidRequest.getDisableDays());
			br.setMinBidAmount(bidRequest.getMinBidAmount());
			br.setMonthes2Return(bidRequest.getMonthes2Return());
			br.setReturnType(BidConst.RETURN_TYPE_MONTH_INTEREST_PRINCIPAL);
			br.setTitle(bidRequest.getTitle());
			br.setTotalRewardAmount(CalculatetUtil.calTotalInterest(br.getReturnType(),br.getBidRequestAmount(),br.getCurrentRate(),br.getMonthes2Return()));
			//保存借款对象
			this.save(br);
			//修改申请人的状态.
			userinfo.addState(BitStatesUtils.HAS_BIDREQUEST_PROCESS);
			userinfoService.update(userinfo);
		}

	}

	@Override
	public PageResult queryPage(BidRequestQueryObject qo) {
		Long count = this.bidRequestMapper.queryPageCount(qo);
		if(count<=0){
		    return PageResult.empty(qo.getPageSize());
		}
		List data = this.bidRequestMapper.queryPageData(qo);
		return new PageResult(data,count.intValue(),qo.getCurrentPage(),qo.getPageSize());
	}

	@Override
	public void publisAudit(Long id, int state, String remark) {
		//1.根据id获取BidRequest对象,判断是否处于待发布的状态
		BidRequest bidRequest = bidRequestMapper.selectByPrimaryKey(id);
		if(bidRequest!=null && bidRequest.getBidRequestState()==BidConst.BIDREQUEST_STATE_PUBLISH_PENDING){
			//2.创建审核历史对象,设置相关属性
			BidRequestAuditHistory brah = new BidRequestAuditHistory();
			brah.setApplier(bidRequest.getCreateUser());
			brah.setApplyTime(bidRequest.getApplyTime());
			brah.setBidRequestId(bidRequest.getId());
			brah.setAuditType(BidRequestAuditHistory.PUBLISH_AUDIT);
			brah.setAuditor(UserContext.getCurrent());
			brah.setAuditTime(new Date());
			brah.setRemark(remark);
			if(state==BidRequestAuditHistory.STATE_PASS){
			//3.如果审核成功
				//审核对象的状态,
				brah.setState(BidRequestAuditHistory.STATE_PASS);
				//借款对象?状态--->招标中  截止时间=当前时间+招标天数   发布时间=当前时间  风控已经
				bidRequest.setBidRequestState(BidConst.BIDREQUEST_STATE_BIDDING);
				bidRequest.setDisableDate(DateUtils.addDays(new Date(),bidRequest.getDisableDays()));
				bidRequest.setPublishTime(new Date());
				bidRequest.setNote(remark);
			}else{
				//4.审核失败
					//审核对象的状态,
				brah.setState(BidRequestAuditHistory.STATE_REJECT);
				bidRequest.setBidRequestState(BidConst.BIDREQUEST_STATE_PUBLISH_REFUSE);
				//移除正在借款的状态码,更新借款人信息
				Userinfo applier = userinfoService.get(bidRequest.getCreateUser().getId());
				applier.removeState(BitStatesUtils.HAS_BIDREQUEST_PROCESS);
				userinfoService.update(applier);
			}
			bidRequestAuditHistoryService.save(brah);
			this.update(bidRequest);

		}


	}

	@Override
	public List<BidRequest> listIndexData(BidRequestQueryObject qo) {
		return this.bidRequestMapper.queryPageData(qo);
	}

	@Override
	public void bid(Long bidRequestId, BigDecimal amount) {
		//判断逻辑
		BidRequest bidRequest = this.get(bidRequestId);
		Account bidUserAccount = accountService.getCurrent();
		//1根据bidRquestId获取借款对象,判断是否为Null,是否处于招标中的状态
		if(bidRequest!=null && bidRequest.getBidRequestState()==BidConst.BIDREQUEST_STATE_BIDDING&&//是否处于招标中的状态
				amount.compareTo(bidRequest.getMinBidAmount())>=0&&//2.投标的金额>=设置的最小投标
				amount.compareTo(bidUserAccount.getUsableAmount().min(bidRequest.getRemainAmount()))<=0&&//投标金额<=MIN(用户可用金额,该标的还需要投资的金额)
				!UserContext.getCurrent().getId().equals(bidRequest.getCreateUser().getId())//4.当前投资用户不是借款人
				){
			//对于借款对象
			bidRequest.setBidCount(bidRequest.getBidCount()+1);
			//	BidCount投资数量+1,投资的金额+这次投资的金额
			bidRequest.setCurrentSum(bidRequest.getCurrentSum().add(amount));
			//对于投标对象
			//	创建投标对象Bid
			Bid bid = new Bid();
			bid.setActualRate(bidRequest.getCurrentRate());
			bid.setAvailableAmount(amount);
			bid.setBidRequestId(bidRequestId);
			bid.setBidRequestState(bidRequest.getBidRequestState());
			bid.setBidTime(new Date());
			bid.setBidRequestTitle(bidRequest.getTitle());
			bid.setBidUser(UserContext.getCurrent());
			bidService.save(bid);
			//对于投资人
			//	投资人账号可用金额减少,冻结金额增加,生成投标冻结的流水
			bidUserAccount.setUsableAmount(bidUserAccount.getUsableAmount().subtract(amount));
			bidUserAccount.setFreezedAmount(bidUserAccount.getFreezedAmount().add(amount));
			accountService.update(bidUserAccount);
			accountFlowService.createBidFlow(bidUserAccount,amount);
			//如果已经投满
			if(bidRequest.getCurrentSum().compareTo(bidRequest.getBidRequestAmount())==0){
				//借款对象的状态修改为满标一审
				bidRequest.setBidRequestState(BidConst.BIDREQUEST_STATE_APPROVE_PENDING_1);
				//投标对象的状态需要修改为满标一审
				bidService.updateState(bidRequestId,BidConst.BIDREQUEST_STATE_APPROVE_PENDING_1);
			}
			this.update(bidRequest);

		}
	}

	@Override
	public void fullAudit1(Long id, int state, String remark) {
		//需要审核判断条件?
		BidRequest bidRequest = this.get(id);
		//1.根据id获取借款对象,判断是否数据满标一审的状态
		if(bidRequest!=null&&bidRequest.getBidRequestState()==BidConst.BIDREQUEST_STATE_APPROVE_PENDING_1){
			//2.创建审核历史对象,设置相关的属性
			createBidRequestAuditHistory(bidRequest,remark,state,BidRequestAuditHistory.FULL_AUDIT_1);
			//如果审核通过?
			if(state==BidRequestAuditHistory.STATE_PASS){
				//进入满标二审的状态,修改借款的状态,标的状态
				bidRequest.setBidRequestState(BidConst.BIDREQUEST_STATE_APPROVE_PENDING_2);
				bidService.updateState(bidRequest.getId(),BidConst.BIDREQUEST_STATE_APPROVE_PENDING_2);
			}else{
				//如果审核失败?
				auditReject(bidRequest);
			}
			//更新借款状态
			this.update(bidRequest);
		}
	}

	@Override
	public void fullAudit2(Long id, int state, String remark) {
		//判断借款状态(处于满标二审状态)
		BidRequest bidRequest = this.get(id);
		if(bidRequest!=null && bidRequest.getBidRequestState()==BidConst.BIDREQUEST_STATE_APPROVE_PENDING_2){
			//创建审核历史对象,并设置相关的属性
			createBidRequestAuditHistory(bidRequest,remark,state,BidRequestAuditHistory.FULL_AUDIT_2);
			//审核通过
			if(state==BidRequestAuditHistory.STATE_PASS){
				//	1.借款状态和投标的状态(更改为还款中)
				bidRequest.setBidRequestState(BidConst.BIDREQUEST_STATE_PAYING_BACK);
				bidService.updateState(bidRequest.getId(),BidConst.BIDREQUEST_STATE_PAYING_BACK);
				//	2.借款人
				//		2.1借款人账号可用金额增加,生成借款成功的流水
				Account createUserAccount = accountService.get(bidRequest.getCreateUser().getId());
				createUserAccount.setUsableAmount(createUserAccount.getUsableAmount().add(bidRequest.getBidRequestAmount()));
				accountFlowService.createBorrowSuccessFlow(createUserAccount,bidRequest.getBidRequestAmount());
				//		2.2借款人的待还本息增加
				//用户的待还金额=原用户的待还金额+这次借款的金额+这次借款的利息
				createUserAccount.setUnReturnAmount(createUserAccount.getUnReturnAmount().add(bidRequest.getBidRequestAmount()).add(bidRequest.getTotalRewardAmount()));
				//		2.3借款人的授信额度减少  借款人的授信额度=原借款人的授信额度-该次借款金额
				createUserAccount.setRemainBorrowLimit(createUserAccount.getRemainBorrowLimit().subtract(bidRequest.getBidRequestAmount()));
				//		2.4移除借款人的正在申请的借款流程状态码
				Userinfo createUserUserinfo = userinfoService.get(bidRequest.getCreateUser().getId());
				createUserUserinfo.removeState(BitStatesUtils.HAS_BIDREQUEST_PROCESS);
				userinfoService.update(createUserUserinfo);
				//		2.5支付平台的手续费.可用金额减少,生成支付平台手续费的流水
				BigDecimal managementCharge = CalculatetUtil.calAccountManagementCharge(bidRequest.getBidRequestAmount());
				createUserAccount.setUsableAmount(createUserAccount.getUsableAmount().subtract(managementCharge));
				accountService.update(createUserAccount);
				accountFlowService.createManagementChargeFlow(createUserAccount,managementCharge);
				//		2.6系统账户收取用户的借款手续费,系统账户的可用金额增加,生成收取系统手续费的流水
				SystemAccount systemAccount = systemAccountService.selectCurrent();
				systemAccount.setUsableAmount(systemAccount.getUsableAmount().add(managementCharge));
				systemAccountFlowService.createReceiveManagementChargeFlow(systemAccount,managementCharge);


				//	4.还款(根据借款期数)
				//	5.还款明细(根据投资人个数定的)
				List<PaymentSchedule> paymentSchedules = createPayScheduleList(bidRequest);

				//	3.投资人
				//		3.1遍历投标集合,获取到每一个投资人的账号.
				//		3.2投资人账号的冻结金额减少,待收本金,待收利息增加(根据还款明细累加求和的),生成投标成功的流水
				Map<Long,Account> accountMap = new HashMap<Long, Account>();
				Account bidUserAccount;
				Long bidUserId;
				for(Bid bid:bidRequest.getBids()){
					bidUserId = bid.getBidUser().getId();
					bidUserAccount = accountMap.get(bidUserId);
					if(bidUserAccount==null){
						bidUserAccount = accountService.get(bidUserId);
						accountMap.put(bidUserId,bidUserAccount);
					}
					bidUserAccount.setFreezedAmount(bidUserAccount.getFreezedAmount().subtract(bid.getAvailableAmount()));
					accountFlowService.createBidSuccessFlow(bidUserAccount,bid.getAvailableAmount());
				}
				for(PaymentSchedule ps:paymentSchedules){
					for(PaymentScheduleDetail psd:ps.getDetails()){
						//找到对应的用户,添加上该还款明细的本金和利息
						bidUserAccount = accountMap.get(psd.getInvestorId());
						bidUserAccount.setUnReceiveInterest(bidUserAccount.getUnReceiveInterest().add(psd.getInterest()));
						bidUserAccount.setUnReceivePrincipal(bidUserAccount.getUnReceivePrincipal().add(psd.getPrincipal()));
					}
				}
				//统一更新用户Account
				for(Account account:accountMap.values()){
					accountService.update(account);
				}
			}else{
				//审核拒绝
				auditReject(bidRequest);
			}
			//更新借款状态
			this.update(bidRequest);
		}
	}

	private List<PaymentSchedule> createPayScheduleList(BidRequest bidRequest) {
		List<PaymentSchedule> paymentSchedules = new ArrayList<PaymentSchedule>();
		//创建几个了?
		PaymentSchedule paymentSchedule;
		BigDecimal principalTemp = BidConst.ZERO;
		BigDecimal interestTemp = BidConst.ZERO;
		for(int i=0;i<bidRequest.getMonthes2Return();i++){
			paymentSchedule = new PaymentSchedule();
			//设置相关的参数
			paymentSchedule.setBidRequestId(bidRequest.getId());
			paymentSchedule.setBidRequestTitle(bidRequest.getTitle());
			paymentSchedule.setBidRequestType(bidRequest.getBidRequestType());
			paymentSchedule.setBorrowUser(bidRequest.getCreateUser());
			paymentSchedule.setDeadLine(DateUtils.addMonths(bidRequest.getPublishTime(),i+1));
			paymentSchedule.setMonthIndex(i+1);
			paymentSchedule.setReturnType(bidRequest.getReturnType());
			//是否最后一期的还款
			if(i<bidRequest.getMonthes2Return()-1){
				//不是最后一期
				//该期还款金额=计算出来的金额
				paymentSchedule.setTotalAmount(CalculatetUtil.calMonthToReturnMoney(bidRequest.getReturnType()
						,bidRequest.getBidRequestAmount(),bidRequest.getCurrentRate(),i+1,bidRequest.getMonthes2Return()));
				//该期利息
				paymentSchedule.setInterest(CalculatetUtil.calMonthlyInterest(bidRequest.getReturnType(),bidRequest.getBidRequestAmount()
						,bidRequest.getCurrentRate(),i+1,bidRequest.getMonthes2Return()));
				//该期本金=该期还款金额-该期利息
				paymentSchedule.setPrincipal(paymentSchedule.getTotalAmount().subtract(paymentSchedule.getInterest()));
				principalTemp = principalTemp.add(paymentSchedule.getPrincipal());
				interestTemp = interestTemp.add(paymentSchedule.getInterest());
			}else{
				//最后一期
				//利息=借款总利息-前N-1期的利息之和
				paymentSchedule.setInterest(bidRequest.getTotalRewardAmount().subtract(interestTemp));
				//本金=借款总金额-前N-1期的本金之和
				paymentSchedule.setPrincipal(bidRequest.getBidRequestAmount().subtract(principalTemp));
				//还款金额=利息+本金
				paymentSchedule.setTotalAmount(paymentSchedule.getInterest().add(paymentSchedule.getPrincipal()));
			}
			paymentScheduleService.save(paymentSchedule);
			//创建还款明细对象
			createPayScheduleDetail(paymentSchedule,bidRequest);
			paymentSchedules.add(paymentSchedule);
		}
		return paymentSchedules;
	}

	private void createPayScheduleDetail(PaymentSchedule paymentSchedule, BidRequest bidRequest) {
		//根据投资人个数来定还款明细对象
		Bid bid;
		PaymentScheduleDetail psd;
		BigDecimal principalTemp = BidConst.ZERO;
		BigDecimal interestTemp = BidConst.ZERO;
		for(int i=0;i<bidRequest.getBids().size();i++){
			bid = bidRequest.getBids().get(i);
			psd = new PaymentScheduleDetail();
			psd.setBidAmount(bid.getAvailableAmount());
			psd.setBidId(bid.getId());
			psd.setBidRequestId(bidRequest.getId());
			psd.setBorrowUser(bidRequest.getCreateUser());
			psd.setDeadLine(paymentSchedule.getDeadLine());
			psd.setInvestorId(bid.getBidUser().getId());
			psd.setMonthIndex(paymentSchedule.getMonthIndex());
			psd.setPaymentScheduleId(paymentSchedule.getId());
			psd.setReturnType(bidRequest.getReturnType());
			if(i<bidRequest.getBids().size()-1){
				//不是最后一个投资人
				//对应投资人投资的比例
				BigDecimal rate = bid.getAvailableAmount().divide(bidRequest.getBidRequestAmount(),BidConst.CAL_SCALE, RoundingMode.HALF_UP);
				//该还款明细对象的利息=投资比例*该期的利息
				psd.setInterest(DecimalFormatUtil.formatBigDecimal(paymentSchedule.getInterest().multiply(rate),BidConst.STORE_SCALE));
				//psd.setInterest(rate.multiply(paymentSchedule.getInterest()));
				//该还款明细对象的本金=投资比例*该期的本金
				psd.setPrincipal(DecimalFormatUtil.formatBigDecimal(paymentSchedule.getPrincipal().multiply(rate),BidConst.STORE_SCALE));
				//psd.setPrincipal(rate.multiply(paymentSchedule.getPrincipal()));
				//该还款明细对象的还款金额=该还款明细对象的利息+/该还款明细对象的本金
				psd.setTotalAmount(psd.getInterest().add(psd.getPrincipal()));
				principalTemp = principalTemp.add(psd.getPrincipal());
				interestTemp = interestTemp.add(psd.getInterest());
			}else{
				//最后一个投资人
				//该还款明细对象的利息=该还款对象的利息-前N-1个投资人的利息之和
				psd.setInterest(paymentSchedule.getInterest().subtract(interestTemp));
				//该还款明细对象的本金=该还款对象的本金-前N-1个投资人的本金之和
				psd.setPrincipal(paymentSchedule.getPrincipal().subtract(principalTemp));
				//该还款明细对象的还款金额=该还款明细对象的利息+/该还款明细对象的本金
				psd.setTotalAmount(psd.getInterest().add(psd.getPrincipal()));
			}
  			paymentScheduleDetailService.save(psd);
			paymentSchedule.getDetails().add(psd);
		}
	}

	private void createBidRequestAuditHistory(BidRequest bidRequest,String remark,int state,int actionType){
		BidRequestAuditHistory brah = new BidRequestAuditHistory();
		brah.setApplier(bidRequest.getCreateUser());
		brah.setApplyTime(new Date());
		brah.setAuditor(UserContext.getCurrent());
		brah.setAuditTime(new Date());
		brah.setBidRequestId(bidRequest.getId());
		brah.setRemark(remark);
		brah.setAuditType(actionType);
		if(state==BidRequestAuditHistory.STATE_PASS){
			brah.setState(BidRequestAuditHistory.STATE_PASS);
		}else{
			brah.setState(BidRequestAuditHistory.STATE_REJECT);
		}
		bidRequestAuditHistoryService.save(brah);
	}
	private void auditReject(BidRequest bidRequest){
		//如果审核失败?
		bidRequest.setBidRequestState(BidConst.BIDREQUEST_STATE_REJECTED);
		bidService.updateState(bidRequest.getId(),BidConst.BIDREQUEST_STATE_REJECTED);
		//进入满标审核的状态,修改借款的状态,标的状态
		List<Bid> bids = bidRequest.getBids();
		//获取到所有的投资人的账号,冻结金额减少,可用金额增加,生成流标失败的流水
		Map<Long,Account> accountMap = new HashMap<Long,Account>();
		Account bidUserAccount;
		Long bidUserId;
		for(Bid bid:bids){
			bidUserId = bid.getBidUser().getId();
			bidUserAccount = accountMap.get(bidUserId);
			if(bidUserAccount==null){
				bidUserAccount = accountService.get(bidUserId);
				accountMap.put(bidUserId,bidUserAccount);
			}
			bidUserAccount.setFreezedAmount(bidUserAccount.getFreezedAmount().subtract(bid.getAvailableAmount()));
			bidUserAccount.setUsableAmount(bidUserAccount.getUsableAmount().add(bid.getAvailableAmount()));
			accountFlowService.createBidFailedFlow(bidUserAccount,bid.getAvailableAmount());
		}
		for(Account account:accountMap.values()){
			accountService.update(account);
		}
		//取消借款人的正在借款的状态码.
		Userinfo bidUserUserinfo = userinfoService.get(bidRequest.getCreateUser().getId());
		bidUserUserinfo.removeState(BitStatesUtils.HAS_BIDREQUEST_PROCESS);
		userinfoService.update(bidUserUserinfo);
	}
}
