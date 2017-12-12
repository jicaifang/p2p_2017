package com.xmg.p2p.website.controller;

import com.xmg.p2p.base.domain.Logininfo;
import com.xmg.p2p.base.service.IAccountService;
import com.xmg.p2p.base.service.IUserinfoService;
import com.xmg.p2p.base.util.BidConst;
import com.xmg.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by seemygo on 2017/10/27.
 */
@Controller
public class BorrowIndexController {
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IUserinfoService userinfoService;
    @RequestMapping("/borrowIndex")
    public String borrowIndex(Model model){
        //判断用户是否有登录
        Logininfo current = UserContext.getCurrent();
        if(current==null){
            return "redirect:/borrow.html";
        }else{
            model.addAttribute("account",accountService.getCurrent());
            model.addAttribute("userinfo",userinfoService.getCurrent());
            model.addAttribute("creditBorrowScore", BidConst.CREDIT_BORROW_SCORE);
            return "borrow";
        }
    }
}
