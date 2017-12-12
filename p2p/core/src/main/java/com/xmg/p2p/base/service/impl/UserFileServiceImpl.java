package com.xmg.p2p.base.service.impl;

import com.xmg.p2p.base.domain.SystemDictionaryItem;
import com.xmg.p2p.base.domain.UserFile;
import com.xmg.p2p.base.domain.Userinfo;
import com.xmg.p2p.base.page.PageResult;
import com.xmg.p2p.base.query.UserFileQueryObject;
import com.xmg.p2p.base.service.IUserinfoService;
import com.xmg.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmg.p2p.base.mapper.UserFileMapper;
import com.xmg.p2p.base.service.IUserFileService;

import java.util.Date;
import java.util.List;

@Service
public class UserFileServiceImpl implements IUserFileService {
	@Autowired
	private UserFileMapper userFileMapper;
	@Autowired
	private IUserinfoService userinfoService;

	@Override
	public int save(UserFile userFile) {
		return userFileMapper.insert(userFile);
	}

	@Override
	public int update(UserFile userFile) {
		return userFileMapper.updateByPrimaryKey(userFile);
	}

	@Override
	public void apply(String imagePath) {
		UserFile userFile = new UserFile();
		userFile.setImage(imagePath);
		userFile.setApplier(UserContext.getCurrent());
		userFile.setApplyTime(new Date());
		userFile.setState(UserFile.STATE_NORMAL);
		this.save(userFile);
	}



	@Override
	public void choiceType(Long[] ids, Long[] fileTypes) {
		if(ids!=null && fileTypes!=null &&ids.length==fileTypes.length){
			UserFile userFile = null;
			SystemDictionaryItem fileType = null;
			for(int i=0;i<ids.length;i++){
				userFile = userFileMapper.selectByPrimaryKey(ids[i]);
				//只能修改自己的风控材料记录
				if(userFile.getApplier().getId().equals(UserContext.getCurrent().getId())){
					fileType = new SystemDictionaryItem();
					fileType.setId(fileTypes[i]);
					userFile.setFileType(fileType);
					userFileMapper.updateByPrimaryKey(userFile);
				}
			}
		}
	}

	@Override
	public List<UserFile> selectFileTypeList(boolean isSelectFileType) {
		return userFileMapper.unSelectFileTypeList(UserContext.getCurrent().getId(),isSelectFileType);
	}

	@Override
	public PageResult queryPage(UserFileQueryObject qo) {
		Long count = userFileMapper.queryPageCount(qo);
		if(count<=0){
		    return PageResult.empty(qo.getPageSize());
		}
		List data = userFileMapper.queryPageData(qo);
		return new PageResult(data,count.intValue(),qo.getCurrentPage(),qo.getPageSize());
	}

	@Override
	public void audit(Long id, int score, int state, String remark) {
		//1.根据id查询出对应的风控材料对象
		UserFile userFile = userFileMapper.selectByPrimaryKey(id);
		//判断是否为null,处于待审核的状态
		if(userFile!=null && userFile.getState()==UserFile.STATE_NORMAL){
			userFile.setAuditor(UserContext.getCurrent());
			userFile.setAuditTime(new Date());
			userFile.setRemark(remark);
			if(state==UserFile.STATE_PASS){
				//设置相关的参数
				userFile.setScore(score);
				userFile.setState(UserFile.STATE_PASS);
				//如果审核通过,找到申请人给score添加对应的分数
				Userinfo applierUserinfo = userinfoService.get(userFile.getApplier().getId());
				applierUserinfo.setScore(applierUserinfo.getScore()+score);
				userinfoService.update(applierUserinfo);
			}else{
				//如果审核失败.
				userFile.setState(UserFile.STATE_REJECT);
			}
			userFileMapper.updateByPrimaryKey(userFile);
		}

	}

	@Override
	public List<UserFile> queryAuditListByLogininfo(Long logininfoId) {
		return userFileMapper.queryAuditListByLogininfo(logininfoId, UserFile.STATE_PASS);
	}
}
