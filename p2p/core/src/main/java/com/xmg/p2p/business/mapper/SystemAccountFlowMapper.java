package com.xmg.p2p.business.mapper;

import com.xmg.p2p.base.query.QueryObject;
import com.xmg.p2p.business.domain.SystemAccountFlow;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SystemAccountFlowMapper {
    int insert(SystemAccountFlow record);
}