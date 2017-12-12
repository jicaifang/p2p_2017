package com.xmg.p2p.business.service;
import com.xmg.p2p.base.domain.Userinfo;
import com.xmg.p2p.base.page.PageResult;
import com.xmg.p2p.business.domain.BidRequest;
import com.xmg.p2p.business.query.BidRequestQueryObject;

import java.math.BigDecimal;
import java.util.List;

public interface IBidRequestService {
    int save(BidRequest bidRequest);
    int update(BidRequest bidRequest);
    /**
     * 判断用户是否有借款资格
     * @param userinfo
     * @return
     */
    boolean canApplyBorrow(Userinfo userinfo);

    /**
     * 用户借款
     * @param bidRequest
     */
    void apply(BidRequest bidRequest);

    PageResult queryPage(BidRequestQueryObject qo);

    /**
     * 发标请前审核
     * @param id
     * @param state
     * @param remark
     */
    void publisAudit(Long id, int state, String remark);

    BidRequest get(Long id);

    List<BidRequest> listIndexData(BidRequestQueryObject qo);

    /**
     * 投标
     * @param bidRequestId
     * @param amount
     */
    void bid(Long bidRequestId, BigDecimal amount);

    /**
     * 满标一审逻辑
     * @param id
     * @param state
     * @param remark
     */
    void fullAudit1(Long id, int state, String remark);

    /**
     * 满标二审逻辑
     * @param id
     * @param state
     * @param remark
     */
    void fullAudit2(Long id, int state, String remark);
}
