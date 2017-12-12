package com.xmg.p2p.base.service;

/**
 * Created by seemygo on 2017/10/27.
 */
public interface IEmailService {
    /**
     * 发送验证的邮件
     * @param email
     */
    void sendEmail(String email);
}
