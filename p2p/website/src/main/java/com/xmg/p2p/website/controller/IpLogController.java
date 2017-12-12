package com.xmg.p2p.website.controller;

import com.xmg.p2p.base.page.PageResult;
import com.xmg.p2p.base.query.IpLogQueryObject;
import com.xmg.p2p.base.service.IIpLogService;
import com.xmg.p2p.base.util.UserContext;
import com.xmg.p2p.website.util.RequiredLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by seemygo on 2017/10/24.
 * 前台的日志控制器
 */
@Controller
public class IpLogController {
    @Autowired
    private IIpLogService ipLogService;
    @RequestMapping("/ipLog")
    @RequiredLogin
    public String ipLogPage(@ModelAttribute("qo") IpLogQueryObject qo, Model model){
        qo.setUsername(UserContext.getCurrent().getUsername());
        PageResult pageResult = ipLogService.queryPage(qo);
        model.addAttribute("pageResult",pageResult);
        return "iplog_list";
    }
}
