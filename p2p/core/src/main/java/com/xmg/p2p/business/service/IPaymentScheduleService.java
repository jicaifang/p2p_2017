package com.xmg.p2p.business.service;

import com.xmg.p2p.base.page.PageResult;
import com.xmg.p2p.business.domain.PaymentSchedule;
import com.xmg.p2p.business.query.PaymentScheduleQueryObject;

import java.util.List;

public interface IPaymentScheduleService {
    int save(PaymentSchedule paymentSchedule);
    int update(PaymentSchedule paymentSchedule);

    PageResult queryPage(PaymentScheduleQueryObject qo);

    /**
     * 还款
     * @param id
     */
    void returnMoney(Long id);

    /**
     * 根据借款id获取该借款的还款集合
     * @param bidRequestId
     * @return
     */
    List<PaymentSchedule> queryListByBidRequestId(Long bidRequestId);
}
