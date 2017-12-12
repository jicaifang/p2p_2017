package com.xmg.p2p.base.service;

import com.xmg.p2p.base.domain.Userinfo;
import com.xmg.p2p.business.domain.BidRequest;

public interface IUserinfoService {
    int save(Userinfo userinfo);
    int update(Userinfo userinfo);
    Userinfo get(Long id);
    Userinfo getCurrent();

    /**
     * 用户绑定手机号码
     * @param phoneNumber
     * @param verifyCode
     */
    void bindPhone(String phoneNumber, String verifyCode);

    /**
     * 用户绑定邮箱
     * @param key
     */
    void bindEmail(String key);

    void basicInfoSave(Userinfo userinfo);


}
