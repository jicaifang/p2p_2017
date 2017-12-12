package com.xmg.p2p.website.controller;

import com.xmg.p2p.base.domain.RealAuth;
import com.xmg.p2p.base.domain.Userinfo;
import com.xmg.p2p.base.service.IRealAuthService;
import com.xmg.p2p.base.service.IUserinfoService;
import com.xmg.p2p.website.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by seemygo on 2017/10/28.
 */
@Controller
public class RealAuthController {
    @Autowired
    private IUserinfoService userinfoService;
    @Autowired
    private IRealAuthService realAuthService;
    @Value("${uploadPath}")
    private String uploadPath;
    @RequestMapping("/realAuth")
    public String realAuthPage(Model model){
        Userinfo userinfo = userinfoService.getCurrent();
        //1.判断用户是否已经实名认证
        if(userinfo.getIsRealAuth()){
             //2.如果已经实名认证,获取到userinfo中的realAuthId查询对应的实名认证对象,放入模型中,跳转到realauth_result.ftl
            model.addAttribute("auditing",false);
            model.addAttribute("realAuth",realAuthService.get(userinfo.getRealAuthId()));
            return "realAuth_result";
        }else{
            //3.如果没有实名认证
            if(userinfo.getRealAuthId()==null){
                //3.2userinfo中的realAuthId为null,跳转到realAuth.ftl
                return "realAuth";
            }else{
                //3.1userinfo中的realAuthId不为null,跳转到realauth_result.ftl,添加auditing=true的参数
                model.addAttribute("auditing",true);
                return "realauth_result";
            }

        }
    }
    @RequestMapping("/realAuth_save")
    public String apply(RealAuth realAuth){
        realAuthService.apply(realAuth);
        return "redirect:/realAuth";
    }

    @RequestMapping("/uploadImage")
    @ResponseBody
    public String uploadImage(MultipartFile image){
        String imagePath = UploadUtil.upload(image, uploadPath);
        return imagePath;
    }
}
