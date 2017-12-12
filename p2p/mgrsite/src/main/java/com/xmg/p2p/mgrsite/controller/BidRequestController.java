package com.xmg.p2p.mgrsite.controller;

import com.xmg.p2p.base.domain.Userinfo;
import com.xmg.p2p.base.service.IRealAuthService;
import com.xmg.p2p.base.service.IUserFileService;
import com.xmg.p2p.base.service.IUserinfoService;
import com.xmg.p2p.base.util.AjaxResult;
import com.xmg.p2p.base.util.BidConst;
import com.xmg.p2p.business.domain.BidRequest;
import com.xmg.p2p.business.query.BidRequestQueryObject;
import com.xmg.p2p.business.service.IBidRequestAuditHistoryService;
import com.xmg.p2p.business.service.IBidRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by seemygo on 2017/10/31.
 */
@Controller
public class BidRequestController {
    @Autowired
    private IBidRequestService bidRequestService;
    @Autowired
    private IUserinfoService userinfoService;
    @Autowired
    private IRealAuthService realAuthService;
    @Autowired
    private IBidRequestAuditHistoryService bidRequestAuditHistoryService;
    @Autowired
    private IUserFileService userFileService;
    @RequestMapping("/bidrequest_publishaudit_list")
    public String publishAuthPage(@ModelAttribute("qo") BidRequestQueryObject qo, Model model){
        qo.setBidRequestState(BidConst.BIDREQUEST_STATE_PUBLISH_PENDING);
        model.addAttribute("pageResult",bidRequestService.queryPage(qo));
        return "bidrequest/publish_audit";
    }
    @RequestMapping("/bidrequest_publishaudit")
    @ResponseBody
    public AjaxResult publishAudit(Long id,int state,String remark){
        AjaxResult result = null;
        try{
            bidRequestService.publisAudit(id,state,remark);
            result = new AjaxResult();
        }catch(Exception e){
            e.printStackTrace();
            result = new AjaxResult(false,e.getMessage());
        }
        return result;
    }
    @RequestMapping("/borrow_info")
    public String borrowInfoPage(Long id,Model model){
        BidRequest bidRequest = bidRequestService.get(id);
        if(bidRequest!=null){
            Userinfo createUser = userinfoService.get(bidRequest.getCreateUser().getId());
            model.addAttribute("bidRequest",bidRequest);
            model.addAttribute("userInfo",createUser);
            model.addAttribute("audits",bidRequestAuditHistoryService.queryListByBidRequestId(bidRequest.getId()));
            model.addAttribute("realAuth",realAuthService.get(createUser.getRealAuthId()));
            model.addAttribute("userFiles",userFileService.queryAuditListByLogininfo(bidRequest.getCreateUser().getId()));//查询审核通过的userinfile的材料
        }
        return "bidrequest/borrow_info";
    }
    @RequestMapping("/bidrequest_audit1_list")
    public String fullAuth1Page(@ModelAttribute("qo") BidRequestQueryObject qo, Model model){
        qo.setBidRequestState(BidConst.BIDREQUEST_STATE_APPROVE_PENDING_1);
        model.addAttribute("pageResult",bidRequestService.queryPage(qo));
        return "bidrequest/audit1";
    }
    @RequestMapping("/bidrequest_audit1")
    @ResponseBody
    public AjaxResult fullAudit1(Long id,int state,String remark){
        AjaxResult result = null;
        try{
            bidRequestService.fullAudit1(id,state,remark);
            result = new AjaxResult();
        }catch(Exception e){
            e.printStackTrace();
            result = new AjaxResult(false,e.getMessage());
        }
        return result;
    }
    @RequestMapping("/bidrequest_audit2_list")
    public String fullAuth2Page(@ModelAttribute("qo") BidRequestQueryObject qo, Model model){
        qo.setBidRequestState(BidConst.BIDREQUEST_STATE_APPROVE_PENDING_2);
        model.addAttribute("pageResult",bidRequestService.queryPage(qo));
        return "bidrequest/audit2";
    }
    @RequestMapping("/bidrequest_audit2")
    @ResponseBody
    public AjaxResult fullAudit2(Long id,int state,String remark){
        AjaxResult result = null;
        try{
            bidRequestService.fullAudit2(id,state,remark);
            result = new AjaxResult();
        }catch(Exception e){
            e.printStackTrace();
            result = new AjaxResult(false,e.getMessage());
        }
        return result;
    }
}
