package com.xmg.p2p.business.service;

import com.xmg.p2p.business.domain.PaymentScheduleDetail;

import java.util.Date;

public interface IPaymentScheduleDetailService {
    int save(PaymentScheduleDetail paymentScheduleDetail);
    int update(PaymentScheduleDetail paymentScheduleDetail);

    /**
     * 更新属于这个还款对象id下的所有还款明细的还款时间
     * @param psId
     * @param payDate
     */
    void updateState(Long psId, Date payDate);
}
