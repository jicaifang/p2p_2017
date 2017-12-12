package com.xmg.p2p.business.service;

import com.xmg.p2p.base.page.PageResult;
import com.xmg.p2p.business.domain.RechargeOffline;
import com.xmg.p2p.business.query.RechargeOfflineQueryObject;

public interface IRechargeOfflineService {
    int save(RechargeOffline rechargeOffline);
    int update(RechargeOffline rechargeOffline);

    /**
     * 充值申请
     * @param rechargeOffline
     */
    void apply(RechargeOffline rechargeOffline);

    PageResult queryPage(RechargeOfflineQueryObject qo);

    /**
     * 充值申请的审核
     * @param id
     * @param state
     * @param remark
     */
    void audit(Long id, int state, String remark);
}
