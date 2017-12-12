package com.xmg.p2p.business.mapper;

import com.xmg.p2p.base.query.QueryObject;
import com.xmg.p2p.business.domain.PaymentSchedule;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PaymentScheduleMapper {
    int insert(PaymentSchedule record);
    PaymentSchedule selectByPrimaryKey(Long id);
    int updateByPrimaryKey(PaymentSchedule record);
	Long queryPageCount(QueryObject qo);
	List<PaymentSchedule> queryPageData(QueryObject qo);

    List<PaymentSchedule> queryListByBidRequestId(Long bidRequestId);
}