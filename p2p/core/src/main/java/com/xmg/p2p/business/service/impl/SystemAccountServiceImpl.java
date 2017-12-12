package com.xmg.p2p.business.service.impl;

import com.xmg.p2p.business.domain.SystemAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmg.p2p.business.mapper.SystemAccountMapper;
import com.xmg.p2p.business.service.ISystemAccountService;

@Service
public class SystemAccountServiceImpl implements ISystemAccountService {
	@Autowired
	private SystemAccountMapper systemAccountMapper;

	@Override
	public int save(SystemAccount systemAccount) {
		return systemAccountMapper.insert(systemAccount);
	}

	@Override
	public int update(SystemAccount systemAccount) {
		int count = systemAccountMapper.updateByPrimaryKey(systemAccount);
		if(count<=0){
			throw new RuntimeException("乐观锁异常,systemAccountId"+systemAccount.getId());
		}
		return count;
	}

	@Override
	public SystemAccount selectCurrent() {
		return systemAccountMapper.selectCurrent();
	}

	@Override
	public void initAccount() {
		SystemAccount current = this.selectCurrent();
		if(current==null){
			current = new SystemAccount();
			this.save(current);
		}
	}
}
