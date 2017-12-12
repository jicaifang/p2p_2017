package com.xmg.p2p.website.controller;

import com.xmg.p2p.base.service.IAccountService;
import com.xmg.p2p.base.util.AjaxResult;
import com.xmg.p2p.base.util.UserContext;
import com.xmg.p2p.business.domain.PaymentSchedule;
import com.xmg.p2p.business.query.PaymentScheduleQueryObject;
import com.xmg.p2p.business.service.IPaymentScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by seemygo on 2017/11/3.
 */
@Controller
public class ReturnMoneyController {
    @Autowired
    private IPaymentScheduleService paymentScheduleService;
    @Autowired
    private IAccountService accountService;
    @RequestMapping("/borrowBidReturn_list")
    public String borrowBidReturnPage(@ModelAttribute("qo") PaymentScheduleQueryObject qo, Model model){
        qo.setBorrowUserId(UserContext.getCurrent().getId());
        model.addAttribute("pageResult",paymentScheduleService.queryPage(qo));
        model.addAttribute("account",accountService.getCurrent());
        return "returnmoney_list";
    }
    @RequestMapping("/returnMoney")
    @ResponseBody
    public AjaxResult returnMoney(Long id){
        AjaxResult result = null;
        try{
            paymentScheduleService.returnMoney(id);
            result = new AjaxResult();
        }catch(Exception e){
            e.printStackTrace();
            result = new AjaxResult(false,e.getMessage());
        }
        return result;
    }
}
