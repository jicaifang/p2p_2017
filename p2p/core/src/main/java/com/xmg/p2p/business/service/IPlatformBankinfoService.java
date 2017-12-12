package com.xmg.p2p.business.service;

import com.xmg.p2p.base.page.PageResult;
import com.xmg.p2p.business.domain.PlatformBankinfo;
import com.xmg.p2p.business.query.PlatformBankinfoQueryObject;

import java.util.List;

public interface IPlatformBankinfoService {
    int save(PlatformBankinfo platformBankinfo);
    int update(PlatformBankinfo platformBankinfo);

    PageResult queryPage(PlatformBankinfoQueryObject qo);

    void saveOrUpdate(PlatformBankinfo platformBankinfo);

    List<PlatformBankinfo> selectAll();
}
