package com.xmg.p2p.business.mapper;

import com.xmg.p2p.base.query.QueryObject;
import com.xmg.p2p.business.domain.SystemAccount;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SystemAccountMapper {
    int insert(SystemAccount record);
    SystemAccount selectCurrent();
    int updateByPrimaryKey(SystemAccount record);
}