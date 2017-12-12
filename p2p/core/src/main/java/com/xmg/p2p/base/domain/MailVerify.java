package com.xmg.p2p.base.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by seemygo on 2017/10/27.
 */
@Setter@Getter
public class MailVerify extends BaseDomain {
    private Long userinfoId;//需要绑定邮箱的用户
    private String email;//需要绑定的邮箱地址
    private Date sendTime;//邮件发送的时间
    private String uuid;//随机编码
}
