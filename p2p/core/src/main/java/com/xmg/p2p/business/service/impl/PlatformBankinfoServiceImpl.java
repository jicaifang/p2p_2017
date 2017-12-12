package com.xmg.p2p.business.service.impl;

import com.xmg.p2p.base.page.PageResult;
import com.xmg.p2p.business.domain.PlatformBankinfo;
import com.xmg.p2p.business.query.PlatformBankinfoQueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmg.p2p.business.mapper.PlatformBankinfoMapper;
import com.xmg.p2p.business.service.IPlatformBankinfoService;

import java.util.List;

@Service
public class PlatformBankinfoServiceImpl implements IPlatformBankinfoService {
	@Autowired
	private PlatformBankinfoMapper platformBankinfoMapper;

	@Override
	public int save(PlatformBankinfo platformBankinfo) {
		return platformBankinfoMapper.insert(platformBankinfo);
	}

	@Override
	public int update(PlatformBankinfo platformBankinfo) {
		return platformBankinfoMapper.updateByPrimaryKey(platformBankinfo);
	}

	@Override
	public PageResult queryPage(PlatformBankinfoQueryObject qo) {
		Long count = this.platformBankinfoMapper.queryPageCount(qo);
		if(count<=0){
		    return PageResult.empty(qo.getPageSize());
		}
		List data = this.platformBankinfoMapper.queryPageData(qo);
		return new PageResult(data,count.intValue(),qo.getCurrentPage(),qo.getPageSize());
	}

	@Override
	public void saveOrUpdate(PlatformBankinfo platformBankinfo) {
		if(platformBankinfo.getId()==null){
			this.save(platformBankinfo);
		}else{
			this.update(platformBankinfo);
		}
	}

	@Override
	public List<PlatformBankinfo> selectAll() {
		return this.platformBankinfoMapper.selectAll();
	}
}
