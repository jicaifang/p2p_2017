package com.xmg.p2p.base.service.impl;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.xmg.p2p.base.domain.MailVerify;
import com.xmg.p2p.base.domain.Userinfo;
import com.xmg.p2p.base.service.IMailVerifyService;
import com.xmg.p2p.base.service.IVerifyCodeService;
import com.xmg.p2p.base.util.BidConst;
import com.xmg.p2p.base.util.BitStatesUtils;
import com.xmg.p2p.base.util.DateUtils;
import com.xmg.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmg.p2p.base.mapper.UserinfoMapper;
import com.xmg.p2p.base.service.IUserinfoService;

import java.util.Date;

@Service
public class UserinfoServiceImpl implements IUserinfoService {
	@Autowired
	private UserinfoMapper userinfoMapper;
	@Autowired
	private IVerifyCodeService verifyCodeService;
	@Autowired
	private IMailVerifyService mailVerifyService;
	@Override
	public int save(Userinfo userinfo) {
		return userinfoMapper.insert(userinfo);
	}

	@Override
	public int update(Userinfo userinfo) {
		int count = userinfoMapper.updateByPrimaryKey(userinfo);
		if(count<=0){
			throw new RuntimeException("乐观锁的异常,UserinfoId="+userinfo.getId());
		}
		return count;
	}

	@Override
	public Userinfo get(Long id) {
		return userinfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public Userinfo getCurrent() {
		return this.get(UserContext.getCurrent().getId());
	}

	@Override
	public void bindPhone(String phoneNumber, String verifyCode) {
		//1.先判断用户是否有绑定手机
		Userinfo userinfo = this.getCurrent();
		if(userinfo.getIsBindPhone()){
			throw new RuntimeException("您已经绑定手机号码,请不要重复绑定");
		}
		//2.校验手机号是否一致,验证码是否一致,验证是否在有效时间之内
		boolean vaild = verifyCodeService.validate(phoneNumber,verifyCode);
		if(vaild){
			//3.给用户userinfo设置手机号码,给用户userinfo中bitState添加手机认证的状态码
			userinfo.setPhoneNumber(phoneNumber);
			userinfo.addState(BitStatesUtils.OP_BIND_PHONE);
			this.update(userinfo);
		}else{
			throw new RuntimeException("验证码验证失败.");
		}
	}

	@Override
	public void bindEmail(String key) {
		MailVerify mailVerify = mailVerifyService.selectByUUID(key);
		if(mailVerify==null){
			throw new RuntimeException("验证地址有误,请重新发送邮件.");
		}
		if(DateUtils.getBetweenTime(mailVerify.getSendTime(),new Date())> BidConst.EMAIL_VAILD_DAY*24*60*60){
			throw new RuntimeException("邮件已经失效,请重新发送.");
		}
		//把邮箱绑定到用户信息中.
		Userinfo userinfo = this.get(mailVerify.getUserinfoId());
		if(userinfo.getIsBindEmail()){
			throw new RuntimeException("您已经绑定邮箱,请不要重复绑定.");
		}
		userinfo.setEmail(mailVerify.getEmail());
		userinfo.addState(BitStatesUtils.OP_BIND_EMAIL);
		this.update(userinfo);
	}

	@Override
	public void basicInfoSave(Userinfo userinfo) {
		//1.查询出userinfo对象
		Userinfo current = this.getCurrent();
		System.out.println("**************************");
		System.out.println(userinfo.getHouseCondition());
		System.out.println("**************************");

		current.setEducationBackground(userinfo.getEducationBackground());
		current.setHouseCondition(userinfo.getHouseCondition());
		current.setIncomeGrade(userinfo.getIncomeGrade());
		current.setKidCount(userinfo.getKidCount());
		current.setMarriage(userinfo.getMarriage());

		System.out.println("88888888888888888888888888");
		System.out.println(current.getHouseCondition());
		System.out.println("88888888888888888888888888");

		if(!current.getIsBasicInfo()){
			current.addState(BitStatesUtils.OP_BASIC_INFO);
		}
		this.update(current);
	}
}
