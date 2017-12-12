package com.xmg.p2p.base.service;

import com.xmg.p2p.base.domain.Logininfo;

import java.util.List;

/**
 * Created by seemygo on 2017/10/23.
 */
public interface ILogininfoService {
    /**
     * 用户注册方法
     * @param username
     * @param password
     */
    void register(String username,String password);

    /**
     * 校验用户名是否有效
     * @param username
     * @return
     */
    boolean checkUsername(String username);

    /**
     * 登录方法
     * @param username
     * @param password
     * @return
     */
    Logininfo login(String username, String password,int userType);

    /**
     * 初始化后台的第一个管理员
     */
    void initAdmin();

    List<Logininfo> autoComplete(String keyword);
}
