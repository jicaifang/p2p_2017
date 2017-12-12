package com.xmg.p2p.base.mapper;

import com.xmg.p2p.base.domain.Logininfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogininfoMapper {

    int insert(Logininfo record);

    Logininfo selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Logininfo record);

    int selectCountByUsername(String username);

    Logininfo login(@Param("username") String username, @Param("password")String password,@Param("userType")int userType);

    int selectCountByType(int usertypeManager);

    List<Logininfo> autoComplete(String keyword);
}