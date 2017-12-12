package com.xmg.p2p.base.service.impl;

import com.xmg.p2p.base.domain.SystemDictionary;
import com.xmg.p2p.base.page.PageResult;
import com.xmg.p2p.base.query.SystemDictionaryQueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmg.p2p.base.mapper.SystemDictionaryMapper;
import com.xmg.p2p.base.service.ISystemDictionaryService;

import java.util.List;

@Service
public class SystemDictionaryServiceImpl implements ISystemDictionaryService {
	@Autowired
	private SystemDictionaryMapper systemDictionaryMapper;

	@Override
	public PageResult queryPage(SystemDictionaryQueryObject qo) {
		Long count = this.systemDictionaryMapper.queryPageCount(qo);
		if(count<=0){
		    return PageResult.empty(qo.getPageSize());
		}
		List data = this.systemDictionaryMapper.queryPageData(qo);
		return new PageResult(data,count.intValue(),qo.getCurrentPage(),qo.getPageSize());
	}

	@Override
	public int save(SystemDictionary dictionary) {
		return systemDictionaryMapper.insert(dictionary);
	}

	@Override
	public int update(SystemDictionary dictionary) {
		return systemDictionaryMapper.updateByPrimaryKey(dictionary);
	}

	@Override
	public void saveOrUpdate(SystemDictionary dictionary) {
		if(dictionary.getId()==null){
			this.save(dictionary);
		}else{
			this.update(dictionary);
		}
	}

	@Override
	public List<SystemDictionary> selectAll() {
		return systemDictionaryMapper.selectAll();
	}
}
