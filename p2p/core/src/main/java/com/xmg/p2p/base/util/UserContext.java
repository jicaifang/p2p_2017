package com.xmg.p2p.base.util;

import com.xmg.p2p.base.domain.Logininfo;
import com.xmg.p2p.base.vo.VerifyCodeVo;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by seemygo on 2017/10/23.
 */
public class UserContext {
    public static final String USER_IN_SESSION = "logininfo";
    public static final String VERIFYCODE_IN_SESSION = "verifyCode";
    private static HttpServletRequest getRequest(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }
    public static void setCurrent(Logininfo logininfo){
        getRequest().getSession().setAttribute(USER_IN_SESSION,logininfo);
    }
    public static Logininfo getCurrent(){
        return (Logininfo) getRequest().getSession().getAttribute(USER_IN_SESSION);
    }

    public static String getIp() {
        return getRequest().getRemoteAddr();
    }

    public static void setVerifyCodeVo(VerifyCodeVo vo) {
        getRequest().getSession().setAttribute(VERIFYCODE_IN_SESSION,vo);
    }
    public static VerifyCodeVo getVerifyCode(){
        return (VerifyCodeVo) getRequest().getSession().getAttribute(VERIFYCODE_IN_SESSION);
    }


}
