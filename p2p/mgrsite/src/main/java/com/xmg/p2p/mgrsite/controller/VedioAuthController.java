package com.xmg.p2p.mgrsite.controller;

import com.xmg.p2p.base.domain.Logininfo;
import com.xmg.p2p.base.query.VedioAuthQueryObject;
import com.xmg.p2p.base.service.ILogininfoService;
import com.xmg.p2p.base.service.IVedioAuthService;
import com.xmg.p2p.base.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by seemygo on 2017/10/28.
 */
@Controller
public class VedioAuthController {
    @Autowired
    private IVedioAuthService vedioAuthService;
    @Autowired
    private ILogininfoService logininfoService;
    @RequestMapping("/vedioAuth")
    public String vedioAuthPage(@ModelAttribute("qo") VedioAuthQueryObject qo, Model model){
        model.addAttribute("pageResult",vedioAuthService.queryPage(qo));
        return "vedioAuth/list";
    }
    @RequestMapping("/vedioAuth_audit")
    @ResponseBody
    public AjaxResult audit(Long loginInfoValue,int state,String remark){
        AjaxResult result = null;
        try{
            vedioAuthService.audit(loginInfoValue,state,remark);
            result = new AjaxResult();
        }catch(Exception e){
            e.printStackTrace();
            result = new AjaxResult(false,e.getMessage());
        }
        return result;
    }
    @RequestMapping("/vedioAuth_autoComplete")
    @ResponseBody
    public List<Logininfo> autoComplete(String keyword){
        List<Logininfo> result = null;
        result = logininfoService.autoComplete(keyword);
        return result;
    }
}
