package com.xmg.p2p.business.service;

import com.xmg.p2p.business.domain.SystemAccount;
import com.xmg.p2p.business.domain.SystemAccountFlow;

import java.math.BigDecimal;

public interface ISystemAccountFlowService {
    int save(SystemAccountFlow systemAccountFlow);

    /**
     * 收取用户借款手续费流水
     * @param account
     * @param amount
     */
    void createReceiveManagementChargeFlow(SystemAccount account, BigDecimal amount);

    /**
     * 收取利息管理费
     * @param account
     * @param amount
     */
    void createReceiveInterestManagerChargeFlow(SystemAccount account, BigDecimal amount);
}
