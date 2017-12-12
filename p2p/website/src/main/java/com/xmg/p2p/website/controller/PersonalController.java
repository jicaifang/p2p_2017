package com.xmg.p2p.website.controller;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.xmg.p2p.base.domain.Account;
import com.xmg.p2p.base.service.IAccountService;
import com.xmg.p2p.base.service.IUserinfoService;
import com.xmg.p2p.base.util.AjaxResult;
import com.xmg.p2p.base.util.UserContext;
import com.xmg.p2p.website.util.RequiredLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by seemygo on 2017/10/24.
 */
@Controller
public class PersonalController {
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IUserinfoService userinfoService;
    @RequestMapping("/personal")
    @RequiredLogin
    public String personalPage(Model model){
       model.addAttribute("account",accountService.getCurrent());
       model.addAttribute("userinfo",userinfoService.getCurrent());
       return "personal";
    }

    @RequestMapping("/bindPhone")
    @ResponseBody
    public AjaxResult bindPhone(String phoneNumber,String verifyCode){
        AjaxResult result = null;
        try{
            userinfoService.bindPhone(phoneNumber,verifyCode);
            result = new AjaxResult();
        }catch(Exception e){
            e.printStackTrace();
            result = new AjaxResult(false,e.getMessage());
        }
        return result;
    }
    @RequestMapping("/bindEmail")
    public String bindEmail(String key,Model model){
        try{
            //
            userinfoService.bindEmail(key);
            model.addAttribute("success",true);
        }catch (Exception e){
            model.addAttribute("success",false);
            model.addAttribute("msg",e.getMessage());
            e.printStackTrace();
        }
        return "checkmail_result";
    }
}
