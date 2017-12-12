package com.xmg.p2p.base.service.impl;

import com.xmg.p2p.base.domain.Logininfo;
import com.xmg.p2p.base.domain.Userinfo;
import com.xmg.p2p.base.domain.VedioAuth;
import com.xmg.p2p.base.page.PageResult;
import com.xmg.p2p.base.query.VedioAuthQueryObject;
import com.xmg.p2p.base.service.IUserinfoService;
import com.xmg.p2p.base.util.BitStatesUtils;
import com.xmg.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmg.p2p.base.mapper.VedioAuthMapper;
import com.xmg.p2p.base.service.IVedioAuthService;

import java.util.Date;
import java.util.List;

@Service
public class VedioAuthServiceImpl implements IVedioAuthService {
	@Autowired
	private VedioAuthMapper vedioAuthMapper;
	@Autowired
	private IUserinfoService userinfoService;
	@Override
	public PageResult queryPage(VedioAuthQueryObject qo) {
		Long count = this.vedioAuthMapper.queryPageCount(qo);
		if(count<=0){
		    return PageResult.empty(qo.getPageSize());
		}
		List data = this.vedioAuthMapper.queryPageData(qo);
		return new PageResult(data,count.intValue(),qo.getCurrentPage(),qo.getPageSize());
	}

	@Override
	public void audit(Long loginInfoValue, int state, String remark) {
		//1.获取需要视频认证的用户,判断是否已经视频认证了.
		Userinfo applierUserinfo = userinfoService.get(loginInfoValue);
		//2.创建VedioAuth对象,设置相关的参数 申请人,申请时间,审核人,审核时间,审核备注
		if(applierUserinfo!=null && !applierUserinfo.getIsVedioAuth()){
			VedioAuth vedioAuth = new VedioAuth();
			Logininfo applier = new Logininfo();
			applier.setId(loginInfoValue);
			vedioAuth.setApplier(applier);//设置申请人
			vedioAuth.setApplyTime(new Date());
			vedioAuth.setAuditor(UserContext.getCurrent());
			vedioAuth.setAuditTime(new Date());
			vedioAuth.setRemark(remark);
			if(state==VedioAuth.STATE_PASS){
				//3.审核通过
				vedioAuth.setState(VedioAuth.STATE_PASS);
				//设置状态
				applierUserinfo.addState(BitStatesUtils.OP_VEDIO_AUTH);
				//给该用户添加视频认证的状态,更新userinfo
				userinfoService.update(applierUserinfo);
			}else{
				//4.审核失败,设置状态
				vedioAuth.setState(VedioAuth.STATE_REJECT);
			}
			//把vedioAuth保存入库
			vedioAuthMapper.insert(vedioAuth);
		}

	}

}
