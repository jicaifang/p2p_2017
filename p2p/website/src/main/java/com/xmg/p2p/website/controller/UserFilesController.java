package com.xmg.p2p.website.controller;

import com.xmg.p2p.base.domain.UserFile;
import com.xmg.p2p.base.service.ISystemDictionaryItemService;
import com.xmg.p2p.base.service.IUserFileService;
import com.xmg.p2p.base.util.AjaxResult;
import com.xmg.p2p.base.util.UserContext;
import com.xmg.p2p.website.util.RequiredLogin;
import com.xmg.p2p.website.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by seemygo on 2017/10/30.
 */
@Controller
public class UserFilesController {
    @Value("${uploadPath}")
    private String uploadPath;
    @Autowired
    private IUserFileService userFileService;
    @Autowired
    private ISystemDictionaryItemService systemDictionaryItemService;
    @RequestMapping("/userFile")
    public String userFilesPage(Model model, HttpSession session){
        /*查询没有选择风控类型的集合unSelectFileList.size();
        if(unSelectFileList.size()==0){
            //说明用户上传的所有风控材料都已经选择了风控类型.
            //跳转到显示的页面A,查询已经选择风控类型的记录集合,在页面中展示
        }else
            //说明这个用户还存在记录是没有选择风控类型的记录.
            //跳转到提交的页面B,把没有选择风控类型的数据放到域中.
        }*/
        List<UserFile> unSelectFileList = userFileService.selectFileTypeList(false);
        if(unSelectFileList.size()==0){
            model.addAttribute("sessionId",session.getId());
            model.addAttribute("userFiles",userFileService.selectFileTypeList(true));
            return "userFiles";
        }else{
            model.addAttribute("userFiles",unSelectFileList);
            model.addAttribute("fileTypes",systemDictionaryItemService.queryListBySn("userFileType"));
            return "userFiles_commit";
        }



        /*List<UserFile> selectFileList = userFileService.selectFileTypeList(true);
        model.addAttribute("userFiles",selectFileList);
        return "userFiles";*/

       //1.查询未选择风控类型的记录
       /*List<UserFile> unSelectFileList = userFileService.unSelectFileTypeList(false);
       model.addAttribute("userFiles",unSelectFileList);
       //2.构建下拉框数据
        model.addAttribute("fileTypes",systemDictionaryItemService.queryListBySn("userFileType"));
       return "userFiles_commit";*/
    }
    @RequestMapping("/userFileUpload")
    @ResponseBody
    public String userFileUpload(MultipartFile file){
        //1.上传文件到本地
        String imagePath = UploadUtil.upload(file, uploadPath);
        //2.保存为一条风控材料记录
        userFileService.apply(imagePath);
        return imagePath;
    }
    @RequestMapping("/userFile_selectType")
    @ResponseBody
    public AjaxResult selectType(Long[] id,Long[] fileType){
        AjaxResult result = null;
        try{
            userFileService.choiceType(id,fileType);
            result = new AjaxResult();
        }catch(Exception e){
            e.printStackTrace();
            result = new AjaxResult(false,e.getMessage());
        }
        return result;
    }
}
