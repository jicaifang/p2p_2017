package com.xmg.p2p.website.controller;

import com.xmg.p2p.base.util.AjaxResult;
import com.xmg.p2p.business.domain.RechargeOffline;
import com.xmg.p2p.business.service.IPlatformBankinfoService;
import com.xmg.p2p.business.service.IRechargeOfflineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by seemygo on 2017/11/1.
 */
@Controller
public class RechargeController {
    @Autowired
    private IPlatformBankinfoService platformBankinfoService;
    @Autowired
    private IRechargeOfflineService rechargeOfflineService;
    @RequestMapping("/recharge")
    public String rechargetPage(Model model){
        model.addAttribute("banks",platformBankinfoService.selectAll());
        return "recharge";
    }
    @RequestMapping("/recharge_save")
    @ResponseBody
    public AjaxResult save(RechargeOffline rechargeOffline){
        AjaxResult result = null;
        try{
            rechargeOfflineService.apply(rechargeOffline);
            result = new AjaxResult();
        }catch(Exception e){
            e.printStackTrace();
            result = new AjaxResult(false,e.getMessage());
        }
        return result;
    }
}
