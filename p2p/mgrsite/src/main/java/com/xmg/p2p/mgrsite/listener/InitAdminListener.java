package com.xmg.p2p.mgrsite.listener;

import com.xmg.p2p.base.service.ILogininfoService;
import com.xmg.p2p.business.service.ISystemAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by seemygo on 2017/10/26.
 */
@Component
public class InitAdminListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private ILogininfoService logininfoService;
    @Autowired
    private ISystemAccountService systemAccountService;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //初始化第一个管理员
        logininfoService.initAdmin();
        //初始化系统账户
        systemAccountService.initAccount();

    }
}
