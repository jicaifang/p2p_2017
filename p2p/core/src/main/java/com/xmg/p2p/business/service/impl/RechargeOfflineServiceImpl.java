package com.xmg.p2p.business.service.impl;

import com.xmg.p2p.base.domain.Account;
import com.xmg.p2p.base.page.PageResult;
import com.xmg.p2p.base.service.IAccountService;
import com.xmg.p2p.base.util.UserContext;
import com.xmg.p2p.business.domain.RechargeOffline;
import com.xmg.p2p.business.query.RechargeOfflineQueryObject;
import com.xmg.p2p.business.service.IAccountFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmg.p2p.business.mapper.RechargeOfflineMapper;
import com.xmg.p2p.business.service.IRechargeOfflineService;

import java.util.Date;
import java.util.List;

@Service
public class RechargeOfflineServiceImpl implements IRechargeOfflineService {
	@Autowired
	private RechargeOfflineMapper rechargeOfflineMapper;
	@Autowired
	private IAccountService accountService;
	@Autowired
	private IAccountFlowService accountFlowService;

	@Override
	public int save(RechargeOffline rechargeOffline) {
		return rechargeOfflineMapper.insert(rechargeOffline);
	}

	@Override
	public int update(RechargeOffline rechargeOffline) {
		return rechargeOfflineMapper.updateByPrimaryKey(rechargeOffline);
	}

	@Override
	public void apply(RechargeOffline rechargeOffline) {
		RechargeOffline ro = new RechargeOffline();
		ro.setAmount(rechargeOffline.getAmount());
		ro.setBankInfo(rechargeOffline.getBankInfo());
		ro.setNote(rechargeOffline.getNote());
		ro.setTradeCode(rechargeOffline.getTradeCode());
		ro.setTradeTime(rechargeOffline.getTradeTime());
		ro.setApplier(UserContext.getCurrent());
		ro.setApplyTime(new Date());
		ro.setState(RechargeOffline.STATE_NORMAL);
		this.save(ro);
	}

	@Override
	public PageResult queryPage(RechargeOfflineQueryObject qo) {
		Long count = this.rechargeOfflineMapper.queryPageCount(qo);
		if(count<=0){
		    return PageResult.empty(qo.getPageSize());
		}
		List data = this.rechargeOfflineMapper.queryPageData(qo);
		return new PageResult(data,count.intValue(),qo.getCurrentPage(),qo.getPageSize());
	}

	@Override
	public void audit(Long id, int state, String remark) {
		//1.根据id获取审核对象,需要判断申请时候处于待审核的状态
		RechargeOffline ro = this.rechargeOfflineMapper.selectByPrimaryKey(id);
		if(ro!=null && ro.getState()==RechargeOffline.STATE_NORMAL){
			//2.设置相关的审核信息
			ro.setAuditor(UserContext.getCurrent());
			ro.setAuditTime(new Date());
			ro.setRemark(remark);
			if(state == RechargeOffline.STATE_PASS){
				//3.如果审核成功--->设置状态,给申请人的可用金额添加对应的金额,创建流水对象
				ro.setState(RechargeOffline.STATE_PASS);
				Account applierAccount = accountService.get(ro.getApplier().getId());
				applierAccount.setUsableAmount(applierAccount.getUsableAmount().add(ro.getAmount()));
				accountService.update(applierAccount);
				accountFlowService.createRechareFlow(applierAccount,ro.getAmount());
			}else{
				//4.如果审核拒绝--->设置状态
				ro.setState(RechargeOffline.STATE_REJECT);
			}
			this.rechargeOfflineMapper.updateByPrimaryKey(ro);
		}
	}
}
