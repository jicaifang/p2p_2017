package com.xmg.p2p.business.service;

import com.xmg.p2p.base.domain.Account;
import com.xmg.p2p.business.domain.AccountFlow;

import java.math.BigDecimal;

public interface IAccountFlowService {
    int save(AccountFlow accountFlow);

    /**
     * 创建线下充值的流水
     * @param account
     * @param amount
     */
    void createRechareFlow(Account account, BigDecimal amount);

    /**
     * 创建投标冻结的流水
     * @param account
     * @param amount
     */
    void createBidFlow(Account account, BigDecimal amount);

    /**
     * 创建投标失败,取消冻结的流水
     * @param account
     * @param amount
     */
    void createBidFailedFlow(Account account, BigDecimal amount);

    /**
     * 借款成功的流水
     * @param account
     * @param amount
     */
    void createBorrowSuccessFlow(Account account, BigDecimal amount);

    /**
     * 支付借款手续费流水
     * @param account
     * @param amount
     */
    void createManagementChargeFlow(Account account, BigDecimal amount);

    /**
     * 投标成功的流水
     * @param account
     * @param amount
     */
    void createBidSuccessFlow(Account account, BigDecimal amount);

    /**
     * 还款流水
     * @param account
     * @param amount
     */
    void createReturnMoneyFlow(Account account, BigDecimal amount);

    /**
     * 回款流水
     * @param account
     * @param amount
     */
    void createGainReturnMoneyFlow(Account account, BigDecimal amount);

    /**
     * 支付平台利息管理费
     * @param account
     * @param amount
     */
    void createPayInterestManagerChargeFlow(Account account, BigDecimal amount);
}
