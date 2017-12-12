package com.xmg.p2p.business.service.impl;

import com.xmg.p2p.base.domain.Account;
import com.xmg.p2p.base.util.BidConst;
import com.xmg.p2p.business.domain.AccountFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmg.p2p.business.mapper.AccountFlowMapper;
import com.xmg.p2p.business.service.IAccountFlowService;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class AccountFlowServiceImpl implements IAccountFlowService {
	@Autowired
	private AccountFlowMapper accountFlowMapper;

	@Override
	public int save(AccountFlow accountFlow) {
		return accountFlowMapper.insert(accountFlow);
	}

	@Override
	public void createRechareFlow(Account account, BigDecimal amount) {
		createFlow(account,amount,BidConst.ACCOUNT_ACTIONTYPE_RECHARGE_OFFLINE,"线下充值"+amount+"元");
	}

	@Override
	public void createBidFlow(Account account, BigDecimal amount) {
		createFlow(account,amount,BidConst.ACCOUNT_ACTIONTYPE_BID_FREEZED,"投标冻结"+amount+"元");
	}

	@Override
	public void createBidFailedFlow(Account account, BigDecimal amount) {
		createFlow(account,amount,BidConst.ACCOUNT_ACTIONTYPE_BID_UNFREEZED,"投标取消冻结"+amount+"元");
	}

	@Override
	public void createBorrowSuccessFlow(Account account, BigDecimal amount) {
		createFlow(account,amount,BidConst.ACCOUNT_ACTIONTYPE_BIDREQUEST_SUCCESSFUL,"借款成功"+amount+"元");
	}

	@Override
	public void createManagementChargeFlow(Account account, BigDecimal amount) {
		createFlow(account,amount,BidConst.ACCOUNT_ACTIONTYPE_CHARGE,"支付平台借款手续费"+amount+"元");
	}

	@Override
	public void createBidSuccessFlow(Account account, BigDecimal amount) {
		createFlow(account,amount,BidConst.ACCOUNT_ACTIONTYPE_BID_SUCCESSFUL,"投标成功流水,冻结金额减少"+amount+"元");
	}

	@Override
	public void createReturnMoneyFlow(Account account, BigDecimal amount) {
		createFlow(account,amount,BidConst.ACCOUNT_ACTIONTYPE_RETURN_MONEY,"还款成功,可用金额减少"+amount+"元");
	}

	@Override
	public void createGainReturnMoneyFlow(Account account, BigDecimal amount) {
		createFlow(account,amount,BidConst.ACCOUNT_ACTIONTYPE_CALLBACK_MONEY,"回款成功,可用金额增加"+amount+"元");
	}

	@Override
	public void createPayInterestManagerChargeFlow(Account account, BigDecimal amount) {
		createFlow(account,amount,BidConst.ACCOUNT_ACTIONTYPE_INTEREST_SHARE,"支付平台利息管理费,可用金额减少"+amount+"元");
	}

	private void createFlow(Account account, BigDecimal amount, int actionType, String remark){
		AccountFlow flow = new AccountFlow();
		flow.setAccountId(account.getId());
		flow.setActionType(actionType);
		flow.setUsableAmount(account.getUsableAmount());
		flow.setAmount(amount);
		flow.setFreezedAmount(account.getFreezedAmount());
		flow.setTradeTime(new Date());
		flow.setRemark(remark);
		this.save(flow);
	}
}
