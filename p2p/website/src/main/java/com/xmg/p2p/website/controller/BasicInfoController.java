package com.xmg.p2p.website.controller;

import com.xmg.p2p.base.domain.Userinfo;
import com.xmg.p2p.base.service.ISystemDictionaryItemService;
import com.xmg.p2p.base.service.IUserinfoService;
import com.xmg.p2p.base.util.AjaxResult;
import com.xmg.p2p.website.util.RequiredLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by seemygo on 2017/10/27.
 */
@Controller
public class BasicInfoController {
    @Autowired
    private IUserinfoService userinfoService;
    @Autowired
    private ISystemDictionaryItemService systemDictionaryItemService;
    @RequestMapping("/basicInfo")
    @RequiredLogin
    public String basicInfoPage(Model model){
        model.addAttribute("userinfo",userinfoService.getCurrent());
        model.addAttribute("educationBackgrounds",systemDictionaryItemService.queryListBySn("educationBackground"));
        model.addAttribute("incomeGrades",systemDictionaryItemService.queryListBySn("incomeGrade"));
        model.addAttribute("marriages",systemDictionaryItemService.queryListBySn("marriage"));
        model.addAttribute("kidCounts",systemDictionaryItemService.queryListBySn("kidCount"));
        model.addAttribute("houseConditions",systemDictionaryItemService.queryListBySn("houseCondition"));
        return "userInfo";

    }
    @RequestMapping("/basicInfo_save")
    @ResponseBody
    @RequiredLogin
    public AjaxResult save(Userinfo userinfo){
        AjaxResult result = null;
        try{
            userinfoService.basicInfoSave(userinfo);
            result = new AjaxResult();
        }catch(Exception e){
            e.printStackTrace();
            result = new AjaxResult(false,e.getMessage());
        }
        return result;
    }
}
