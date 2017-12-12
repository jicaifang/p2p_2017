package com.xmg.p2p.base.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by seemygo on 2017/10/26.
 */
@Setter@Getter
public class VerifyCodeVo implements Serializable {
    private String phoneNumber;
    private String verifyCode;
    private Date sendTime;
}
