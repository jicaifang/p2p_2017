package com.xmg.p2p.base.service;

import com.xmg.p2p.base.domain.UserFile;
import com.xmg.p2p.base.page.PageResult;
import com.xmg.p2p.base.query.UserFileQueryObject;

import java.util.List;

public interface IUserFileService {
    int save(UserFile userFile);
    int update(UserFile userFile);

    void apply(String imagePath);

    void choiceType(Long[] ids, Long[] fileTypes);

    List<UserFile> selectFileTypeList(boolean isSelectFileType);

    PageResult queryPage(UserFileQueryObject qo);

    /**
     * 审核方法
     * @param id
     * @param score
     * @param state
     * @param remark
     */
    void audit(Long id, int score, int state, String remark);

    List<UserFile> queryAuditListByLogininfo(Long id);
}
