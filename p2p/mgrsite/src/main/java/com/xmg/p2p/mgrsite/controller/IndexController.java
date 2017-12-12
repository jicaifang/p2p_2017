package com.xmg.p2p.mgrsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by seemygo on 2017/10/26.
 */
@Controller
public class IndexController {
    @RequestMapping("/index")
    public String index(){
        return "main";
    }
}
