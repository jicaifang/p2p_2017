package com.xmg.p2p.base.service;
import com.xmg.p2p.base.domain.SystemDictionary;
import com.xmg.p2p.base.page.PageResult;
import com.xmg.p2p.base.query.SystemDictionaryQueryObject;

import java.util.List;

public interface ISystemDictionaryService {
    PageResult queryPage(SystemDictionaryQueryObject qo);
    int save(SystemDictionary dictionary);
    int update(SystemDictionary dictionary);
    void saveOrUpdate(SystemDictionary dictionary);

    List<SystemDictionary> selectAll();
}
