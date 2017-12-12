package com.xmg.p2p.business.service;

import com.xmg.p2p.business.domain.Bid;

public interface IBidService {
    int save(Bid bid);
    /**
     * 批量更新投标对象的状态
     * @param bidRequestId
     * @param state
     */
    void updateState(Long bidRequestId, int state);

}
