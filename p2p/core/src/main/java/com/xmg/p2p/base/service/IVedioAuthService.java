package com.xmg.p2p.base.service;
import com.xmg.p2p.base.domain.Logininfo;
import com.xmg.p2p.base.page.PageResult;
import com.xmg.p2p.base.query.VedioAuthQueryObject;

import java.util.List;

public interface IVedioAuthService {
    PageResult queryPage(VedioAuthQueryObject qo);

    void audit(Long loginInfoValue, int state, String remark);
}
