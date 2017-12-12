package com.xmg.p2p.mgrsite.controller;

import com.xmg.p2p.base.util.AjaxResult;
import com.xmg.p2p.business.query.RechargeOfflineQueryObject;
import com.xmg.p2p.business.service.IPlatformBankinfoService;
import com.xmg.p2p.business.service.IRechargeOfflineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by seemygo on 2017/11/1.
 */
@Controller
public class RechargeController {
    @Autowired
    private IRechargeOfflineService rechargeOfflineService;
    @Autowired
    private IPlatformBankinfoService platformBankinfoService;
    @RequestMapping("/rechargeOffline")
    public String rechargePage(@ModelAttribute("qo") RechargeOfflineQueryObject qo, Model model){
        model.addAttribute("pageResult",rechargeOfflineService.queryPage(qo));
        model.addAttribute("banks",platformBankinfoService.selectAll());
        return "rechargeoffline/list";
    }
    @RequestMapping("/rechargeOffline_audit")
    @ResponseBody
    public AjaxResult audit(Long id,int state,String remark){
        AjaxResult result = null;
        try{
            rechargeOfflineService.audit(id,state,remark);
            result = new AjaxResult();
        }catch(Exception e){
            e.printStackTrace();
            result = new AjaxResult(false,e.getMessage());
        }
        return result;
    }
}
