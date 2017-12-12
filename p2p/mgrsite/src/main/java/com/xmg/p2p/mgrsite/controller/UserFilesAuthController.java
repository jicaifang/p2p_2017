package com.xmg.p2p.mgrsite.controller;

import com.xmg.p2p.base.query.UserFileQueryObject;
import com.xmg.p2p.base.service.IUserFileService;
import com.xmg.p2p.base.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by seemygo on 2017/10/30.
 */
@Controller
public class UserFilesAuthController {
    @Autowired
    private IUserFileService userFileService;
    @RequestMapping("/userFileAuth")
    public String userFilesPage(@ModelAttribute("qo") UserFileQueryObject qo, Model model){
        model.addAttribute("pageResult",userFileService.queryPage(qo));
        return "userFileAuth/list";
    }
    @RequestMapping("/userFile_audit")
    @ResponseBody
    public AjaxResult audit(Long id,int score,int state,String remark){
        AjaxResult result = null;
        try{
            userFileService.audit(id,score,state,remark);
            result = new AjaxResult();
        }catch(Exception e){
            e.printStackTrace();
            result = new AjaxResult(false,e.getMessage());
        }
        return result;
    }
}
