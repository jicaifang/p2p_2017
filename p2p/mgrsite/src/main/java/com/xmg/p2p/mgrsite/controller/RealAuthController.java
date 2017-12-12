package com.xmg.p2p.mgrsite.controller;

import com.xmg.p2p.base.query.RealAuthQueryObject;
import com.xmg.p2p.base.service.IRealAuthService;
import com.xmg.p2p.base.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by seemygo on 2017/10/28.
 */
@Controller
public class RealAuthController {
    @Autowired
    private IRealAuthService realAuthService;
    @RequestMapping("/realAuth")
    public String realAuthPage(@ModelAttribute("qo") RealAuthQueryObject qo, Model model){
        model.addAttribute("pageResult",realAuthService.queryPage(qo));
        return "realAuth/list";
    }
    @RequestMapping("/realAuth_audit")
    @ResponseBody
    public AjaxResult audit(Long id,int state,String remark){
        AjaxResult result = null;
        try{
            realAuthService.audit(id,state,remark);
            result = new AjaxResult();
        }catch(Exception e){
            e.printStackTrace();
            result = new AjaxResult(false,e.getMessage());
        }
        return result;
    }
}
