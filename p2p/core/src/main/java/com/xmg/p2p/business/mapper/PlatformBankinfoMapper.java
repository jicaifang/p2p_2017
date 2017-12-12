package com.xmg.p2p.business.mapper;

import com.xmg.p2p.base.query.QueryObject;
import com.xmg.p2p.business.domain.PlatformBankinfo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PlatformBankinfoMapper {
    int insert(PlatformBankinfo record);
    PlatformBankinfo selectByPrimaryKey(Long id);
    int updateByPrimaryKey(PlatformBankinfo record);
	Long queryPageCount(QueryObject qo);
	List<PlatformBankinfo> queryPageData(QueryObject qo);

    List<PlatformBankinfo> selectAll();
}