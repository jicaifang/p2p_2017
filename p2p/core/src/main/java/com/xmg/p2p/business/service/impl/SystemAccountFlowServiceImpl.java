package com.xmg.p2p.business.service.impl;

import com.xmg.p2p.base.util.BidConst;
import com.xmg.p2p.business.domain.SystemAccount;
import com.xmg.p2p.business.domain.SystemAccountFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmg.p2p.business.mapper.SystemAccountFlowMapper;
import com.xmg.p2p.business.service.ISystemAccountFlowService;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class SystemAccountFlowServiceImpl implements ISystemAccountFlowService {
	@Autowired
	private SystemAccountFlowMapper systemAccountFlowMapper;

	@Override
	public int save(SystemAccountFlow systemAccountFlow) {
		return systemAccountFlowMapper.insert(systemAccountFlow);
	}

	@Override
	public void createReceiveManagementChargeFlow(SystemAccount account, BigDecimal amount) {
		createFlow(account,amount, BidConst.SYSTEM_ACCOUNT_ACTIONTYPE_MANAGE_CHARGE,"收取用户借款手续费"+amount+"元");
	}

	@Override
	public void createReceiveInterestManagerChargeFlow(SystemAccount account, BigDecimal amount) {
		createFlow(account,amount, BidConst.SYSTEM_ACCOUNT_ACTIONTYPE_INTREST_MANAGE_CHARGE,"收取用户利息管理费"+amount+"元");
	}

	private void createFlow(SystemAccount account, BigDecimal amount, int actionType, String remark){
		SystemAccountFlow flow = new SystemAccountFlow();
		flow.setActionTime(new Date());
		flow.setActionType(actionType);
		flow.setAmount(amount);
		flow.setFreezedAmount(account.getFreezedAmount());
		flow.setUsableAmount(account.getUsableAmount());
		flow.setRemark(remark);
		this.save(flow);
	}
}
