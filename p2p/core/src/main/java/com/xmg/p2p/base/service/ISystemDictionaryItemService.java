package com.xmg.p2p.base.service;
import com.xmg.p2p.base.domain.SystemDictionaryItem;
import com.xmg.p2p.base.page.PageResult;
import com.xmg.p2p.base.query.SystemDictionaryItemQueryObject;

import java.util.List;

public interface ISystemDictionaryItemService {
    PageResult queryPage(SystemDictionaryItemQueryObject qo);
    int save(SystemDictionaryItem systemDictionaryItem);
    int update(SystemDictionaryItem systemDictionaryItem);
    void saveOrUpdate(SystemDictionaryItem systemDictionaryItem);

    /**
     * 根据SN获取对应明细对象集合
     * @param educationBackground
     * @return
     */
    List<SystemDictionaryItem> queryListBySn(String educationBackground);
}
