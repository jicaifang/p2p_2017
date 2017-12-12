package com.xmg.p2p.base.service.impl;

import com.xmg.p2p.base.domain.Account;
import com.xmg.p2p.base.domain.IpLog;
import com.xmg.p2p.base.domain.Logininfo;
import com.xmg.p2p.base.domain.Userinfo;
import com.xmg.p2p.base.mapper.LogininfoMapper;
import com.xmg.p2p.base.service.IAccountService;
import com.xmg.p2p.base.service.IIpLogService;
import com.xmg.p2p.base.service.ILogininfoService;
import com.xmg.p2p.base.service.IUserinfoService;
import com.xmg.p2p.base.util.BidConst;
import com.xmg.p2p.base.util.MD5;
import com.xmg.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by seemygo on 2017/10/23.
 */
@Service
public class LogininfoServiceImpl implements ILogininfoService {
    @Autowired
    private LogininfoMapper logininfoMapper;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IUserinfoService userinfoService;
    @Autowired
    private IIpLogService ipLogService;
    @Override
    public void register(String username, String password) {
       //1.判断用户名是否已经被注册了.
        int count = logininfoMapper.selectCountByUsername(username);
        if(count>0){
            throw new RuntimeException("用户名已经被注册.");
        }
        // 2.创建Logininfo对象,设置相关的属性,保存入库
        Logininfo logininfo = new Logininfo();
        logininfo.setUsername(username);
        logininfo.setPassword(MD5.encode(password));
        logininfo.setState(Logininfo.STATE_NORMAL);
        logininfo.setUserType(Logininfo.USERTYPE_USER);
        logininfoMapper.insert(logininfo);

        //初始化Account对象和Userinfo对象
        Account account = new Account();
        account.setId(logininfo.getId());
        accountService.save(account);

        Userinfo userinfo = new Userinfo();
        userinfo.setId(logininfo.getId());
        userinfoService.save(userinfo);
    }

    @Override
    public boolean checkUsername(String username) {
        return logininfoMapper.selectCountByUsername(username)<=0;
    }

    @Override
    public Logininfo login(String username, String password,int userType) {
        Logininfo logininfo = logininfoMapper.login(username,MD5.encode(password),userType);
        IpLog ipLog = new IpLog();
        ipLog.setUsername(username);
        ipLog.setLoginTime(new Date());
        ipLog.setIp(UserContext.getIp());
        ipLog.setUserType(userType);
        if(logininfo==null){
            ipLog.setState(IpLog.LOGIN_FAILED);
        }else{
            ipLog.setState(IpLog.LOGIN_SUCCESS);
            //把对象放入到session中.
            UserContext.setCurrent(logininfo);
        }
        ipLogService.save(ipLog);
        return logininfo;
    }

    @Override
    public void initAdmin() {
        //1.去数据库中查询是否有管理员
        int count = logininfoMapper.selectCountByType(Logininfo.USERTYPE_MANAGER);
        //2.如果没有就创建一个.
        if(count<=0){
            Logininfo logininfo = new Logininfo();
            logininfo.setUsername(BidConst.DEFAULT_ADMIN_ACCOUNT);
            logininfo.setPassword(MD5.encode(BidConst.DEFAULT_ADMIN_PASSWORD));
            logininfo.setState(Logininfo.STATE_NORMAL);
            logininfo.setUserType(Logininfo.USERTYPE_MANAGER);
            logininfoMapper.insert(logininfo);
        }
    }

    @Override
    public List<Logininfo> autoComplete(String keyword) {
        return logininfoMapper.autoComplete(keyword);
    }
}
