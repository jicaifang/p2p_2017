package com.xmg.p2p.mgrsite.controller;

import com.xmg.p2p.business.domain.PlatformBankinfo;
import com.xmg.p2p.business.query.PlatformBankinfoQueryObject;
import com.xmg.p2p.business.service.IPlatformBankinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by seemygo on 2017/11/1.
 */
@Controller
public class PlatFormBankInfoController {
    @Autowired
    private IPlatformBankinfoService platformBankinfoService;
    @RequestMapping("/companyBank_list")
    public String platFormBankInfoPage(@ModelAttribute("qo") PlatformBankinfoQueryObject qo, Model model){
        model.addAttribute("pageResult",platformBankinfoService.queryPage(qo));
        return "platformbankinfo/list";
    }
    @RequestMapping("/companyBank_update")
    public String saveOrUpdate(PlatformBankinfo platformBankinfo){
        platformBankinfoService.saveOrUpdate(platformBankinfo);
        return "redirect:/companyBank_list";
    }
}
