package com.xmg.p2p.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by seemygo on 2017/10/26.
 */
@Controller
public class MeiLianController {
    @RequestMapping("/sendMsg")
    @ResponseBody
    public String sendSms(String username,String password,String apikey,String phoneNumber,String content){
        System.out.println("用户名:"+username);
        System.out.println("密码:"+password);
        System.out.println("apiKey:"+apikey);
        System.out.println("手机:"+phoneNumber);
        System.out.println("发送的内容:"+content);
        return "success";
    }
}
