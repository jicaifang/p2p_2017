package com.xmg.p2p.base.service;

/**
 * Created by seemygo on 2017/10/26.
 */
public interface IVerifyCodeService {
    /**
     * 发送手机验证码
     * @param phoneNumber
     */
    void sendVerifyCode(String phoneNumber);

    /**
     * 检验传入的手机号码和验证码是否有效
     * @param phoneNumber
     * @param verifyCode
     * @return
     */
    boolean validate(String phoneNumber, String verifyCode);
}
