package com.xmg.p2p.mgrsite.controller;

import com.xmg.p2p.base.domain.SystemDictionaryItem;
import com.xmg.p2p.base.query.SystemDictionaryItemQueryObject;
import com.xmg.p2p.base.service.ISystemDictionaryItemService;
import com.xmg.p2p.base.service.ISystemDictionaryService;
import com.xmg.p2p.base.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by seemygo on 2017/10/27.
 */
@Controller
public class SystemDicationaryItemController {
    @Autowired
    private ISystemDictionaryItemService systemDictionaryItemService;
    @Autowired
    private ISystemDictionaryService systemDictionaryService;
    @RequestMapping("/systemDictionaryItem_list")
    public String systemDicationaryItemPage(@ModelAttribute("qo") SystemDictionaryItemQueryObject qo, Model model){
        model.addAttribute("pageResult",systemDictionaryItemService.queryPage(qo));
        model.addAttribute("systemDictionaryGroups",systemDictionaryService.selectAll());
        return "systemdic/systemDictionaryItem_list";
    }
    @RequestMapping("/systemDictionaryItem_update")
    @ResponseBody
    public AjaxResult update(SystemDictionaryItem systemDictionaryItem){
        AjaxResult result = null;
        try{
            systemDictionaryItemService.saveOrUpdate(systemDictionaryItem);
            result = new AjaxResult();
        }catch(Exception e){
            e.printStackTrace();
            result = new AjaxResult(false,e.getMessage());
        }
        return result;
    }
}
