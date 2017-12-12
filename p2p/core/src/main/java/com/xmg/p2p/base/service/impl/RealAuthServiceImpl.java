package com.xmg.p2p.base.service.impl;

import com.xmg.p2p.base.domain.RealAuth;
import com.xmg.p2p.base.domain.Userinfo;
import com.xmg.p2p.base.page.PageResult;
import com.xmg.p2p.base.query.RealAuthQueryObject;
import com.xmg.p2p.base.service.IUserinfoService;
import com.xmg.p2p.base.util.BitStatesUtils;
import com.xmg.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmg.p2p.base.mapper.RealAuthMapper;
import com.xmg.p2p.base.service.IRealAuthService;

import java.util.Date;
import java.util.List;

@Service
public class RealAuthServiceImpl implements IRealAuthService {
	@Autowired
	private RealAuthMapper realAuthMapper;
	@Autowired
	private IUserinfoService userinfoService;
	@Override
	public RealAuth get(Long id) {
		return realAuthMapper.selectByPrimaryKey(id);
	}

	@Override
	public void apply(RealAuth realAuth) {
			Userinfo userinfo = userinfoService.getCurrent();
		//判断用户没有实名认证,userinfo中的realAuthId==null
		if(!userinfo.getIsRealAuth() && userinfo.getRealAuthId()==null){
			//封装RealAuth相关的参数
			RealAuth r = new RealAuth();
			r.setAddress(realAuth.getAddress());
			r.setApplier(UserContext.getCurrent());
			r.setApplyTime(new Date());
			r.setBornDate(realAuth.getBornDate());
			r.setIdNumber(realAuth.getIdNumber());
			r.setImage1(realAuth.getImage1());
			r.setImage2(realAuth.getImage2());
			r.setRealName(realAuth.getRealName());
			r.setSex(realAuth.getSex());
			r.setState(RealAuth.STATE_NORMAL);
			//保存入库
			realAuthMapper.insert(r);
			//把realAuthId设置userinfo中
			userinfo.setRealAuthId(r.getId());
			userinfoService.update(userinfo);
		}

	}

	@Override
	public PageResult queryPage(RealAuthQueryObject qo) {
		Long count = this.realAuthMapper.queryPageCount(qo);
		if(count<=0){
		    return PageResult.empty(qo.getPageSize());
		}
		List data = this.realAuthMapper.queryPageData(qo);
		return new PageResult(data,count.intValue(),qo.getCurrentPage(),qo.getPageSize());
	}

	@Override
	public void audit(Long id, int state, String remark) {
		//1.根据传入的id查询对应的实名认证审核对象
		RealAuth realAuth = this.get(id);
		//2.审核对象处于待审核状态
		if(realAuth!=null&&realAuth.getState()==RealAuth.STATE_NORMAL){
			//3.给这个审核对象设置公共属性,审核人,审核时间,审核状态
			realAuth.setAuditor(UserContext.getCurrent());
			realAuth.setAuditTime(new Date());
			realAuth.setRemark(remark);
			Userinfo applierUserinfo = userinfoService.get(realAuth.getApplier().getId());
			if(state==RealAuth.STATE_PASS){
				//4.审核成功
				realAuth.setState(RealAuth.STATE_PASS);
				//设置userinfo的realName和idNUmber
				applierUserinfo.setRealName(realAuth.getRealName());
				applierUserinfo.setIdNumber(realAuth.getIdNumber());
				//给申请人添加实名认证的状态码,更新userinfo
				applierUserinfo.addState(BitStatesUtils.OP_REAL_AUTH);
			}else{
				//5.审核失败
				realAuth.setState(RealAuth.STATE_REJECT);
				//设置申请人的userinfo中的realAuthId设置为null
				applierUserinfo.setRealAuthId(null);
			}
			realAuthMapper.updateByPrimaryKey(realAuth);
			userinfoService.update(applierUserinfo);
		}

	}
}
