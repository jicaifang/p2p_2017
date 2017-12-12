package com.xmg.p2p.mgrsite.controller;

import com.xmg.p2p.base.domain.Logininfo;
import com.xmg.p2p.base.service.ILogininfoService;
import com.xmg.p2p.base.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by seemygo on 2017/10/26.
 */
@Controller
public class LoginController {
    @Autowired
    private ILogininfoService logininfoService;
    @RequestMapping("/mgrLogin")
    @ResponseBody
    public AjaxResult login(String username,String password){
        AjaxResult result = null;
        Logininfo logininfo = logininfoService.login(username, password, Logininfo.USERTYPE_MANAGER);
        if(logininfo==null){
            result = new AjaxResult(false,"登录失败,账号密码有误!");
        }else{
            result = new AjaxResult("登录成功");
        }
        return result;
    }
}
