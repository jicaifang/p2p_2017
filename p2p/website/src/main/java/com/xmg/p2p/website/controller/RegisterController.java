package com.xmg.p2p.website.controller;

import com.xmg.p2p.base.service.ILogininfoService;
import com.xmg.p2p.base.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by seemygo on 2017/10/23.
 */
@Controller
public class RegisterController {
    @Autowired
    private ILogininfoService logininfoService;
    @RequestMapping("/userRegister")
    @ResponseBody
    public AjaxResult register(String username, String password){
        AjaxResult result = null;
        try{
            logininfoService.register(username,password);
            result = new AjaxResult("注册成功");
        }catch (Exception e){
            e.printStackTrace();
            result = new AjaxResult(false,"注册失败,"+e.getMessage());
        }
        return result;
    }
    @RequestMapping("/checkUsername")
    @ResponseBody
    public boolean checkUsername(String username){
        return logininfoService.checkUsername(username);
    }
}
