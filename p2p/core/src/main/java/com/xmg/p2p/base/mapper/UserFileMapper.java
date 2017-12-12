package com.xmg.p2p.base.mapper;

import com.xmg.p2p.base.domain.UserFile;
import com.xmg.p2p.base.query.QueryObject;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserFileMapper {
    int insert(UserFile record);
    UserFile selectByPrimaryKey(Long id);
    int updateByPrimaryKey(UserFile record);
	Long queryPageCount(QueryObject qo);
	List<UserFile> queryPageData(QueryObject qo);

    List<UserFile> unSelectFileTypeList(@Param("currentUserId") Long currentUserId,@Param("isSelectFileType")Boolean isSelectFileType);

    List<UserFile> queryAuditListByLogininfo(@Param("logininfoId")Long logininfoId,@Param("state") int state);
}