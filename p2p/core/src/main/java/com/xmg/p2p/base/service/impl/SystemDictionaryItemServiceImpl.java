package com.xmg.p2p.base.service.impl;

import com.xmg.p2p.base.domain.SystemDictionaryItem;
import com.xmg.p2p.base.page.PageResult;
import com.xmg.p2p.base.query.SystemDictionaryItemQueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmg.p2p.base.mapper.SystemDictionaryItemMapper;
import com.xmg.p2p.base.service.ISystemDictionaryItemService;

import java.util.List;

@Service
public class SystemDictionaryItemServiceImpl implements ISystemDictionaryItemService {
	@Autowired
	private SystemDictionaryItemMapper systemDictionaryItemMapper;

	@Override
	public PageResult queryPage(SystemDictionaryItemQueryObject qo) {
		Long count = systemDictionaryItemMapper.queryPageCount(qo);
		if(count<=0){
		    return PageResult.empty(qo.getPageSize());
		}
		List data = systemDictionaryItemMapper.queryPageData(qo);
		return new PageResult(data,count.intValue(),qo.getCurrentPage(),qo.getPageSize());
	}

	@Override
	public int save(SystemDictionaryItem systemDictionaryItem) {
		return systemDictionaryItemMapper.insert(systemDictionaryItem);
	}

	@Override
	public int update(SystemDictionaryItem systemDictionaryItem) {
		return systemDictionaryItemMapper.updateByPrimaryKey(systemDictionaryItem);
	}

	@Override
	public void saveOrUpdate(SystemDictionaryItem systemDictionaryItem) {
		if(systemDictionaryItem.getId()==null){
			this.save(systemDictionaryItem);
		}else{
			this.update(systemDictionaryItem);
		}
	}

	@Override
	public List<SystemDictionaryItem> queryListBySn(String sn) {
		return this.systemDictionaryItemMapper.queryListBySn(sn);
	}
}
