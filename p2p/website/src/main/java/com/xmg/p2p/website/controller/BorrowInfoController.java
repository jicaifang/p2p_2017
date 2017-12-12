package com.xmg.p2p.website.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.xmg.p2p.base.domain.Userinfo;
import com.xmg.p2p.base.service.IAccountService;
import com.xmg.p2p.base.service.IRealAuthService;
import com.xmg.p2p.base.service.IUserFileService;
import com.xmg.p2p.base.service.IUserinfoService;
import com.xmg.p2p.base.util.AjaxResult;
import com.xmg.p2p.base.util.BidConst;
import com.xmg.p2p.base.util.UserContext;
import com.xmg.p2p.business.domain.BidRequest;
import com.xmg.p2p.business.service.IBidRequestService;
import com.xmg.p2p.website.util.RequiredLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

/**
 * Created by seemygo on 2017/10/30.
 */
@Controller
public class BorrowInfoController {
    @Autowired
    private IBidRequestService bidRequestService;
    @Autowired
    private IUserinfoService userinfoService;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IUserFileService userFileService;
    @Autowired
    private IRealAuthService realAuthService;
    @RequestMapping("/borrowInfo")
    @RequiredLogin
    public String borrowInfoPage(Model model){
        //1.需要满足借款条件
        Userinfo userinfo = userinfoService.getCurrent();
        if(bidRequestService.canApplyBorrow(userinfo)){
            if(userinfo.getHasBidRequestProcess()){
                return "borrow_apply_result";
            }else{
                model.addAttribute("account",accountService.getCurrent());
                model.addAttribute("minBidRequestAmount", BidConst.SMALLEST_BIDREQUEST_AMOUNT);
                model.addAttribute("minBidAmount",BidConst.SMALLEST_BID_AMOUNT);
                return "borrow_apply";
            }
        }else{
            return "redirect:/borrowIndex";
        }
    }
    @RequestMapping("/borrow_apply")
    @RequiredLogin
    public String apply(BidRequest bidRequest){
        try {
            bidRequestService.apply(bidRequest);
        }catch(Exception e){
            e.printStackTrace();
        }
        return "redirect:/borrowInfo";
    }
    @RequestMapping("/borrow_info")
    public String borrowInfo(Long id, Model model){
        BidRequest bidRequest = bidRequestService.get(id);
        if(bidRequest!=null){
            Userinfo createUser = userinfoService.get(bidRequest.getCreateUser().getId());
            model.addAttribute("bidRequest",bidRequest);
            model.addAttribute("userInfo",createUser);
            model.addAttribute("realAuth",realAuthService.get(createUser.getRealAuthId()));
            model.addAttribute("userFiles",userFileService.queryAuditListByLogininfo(bidRequest.getCreateUser().getId()));//查询审核通过的userinfile的材料
        }
        //判断是否有登录
        if(UserContext.getCurrent()!=null){
            //判断当前登录用户是否是借款人
            if(bidRequest.getCreateUser().getId().equals(UserContext.getCurrent().getId())){
                //如果是 self=true
                model.addAttribute("self",true);
            }else{
                //如果不是 self=false ,account
                model.addAttribute("self",false);
                model.addAttribute("account",accountService.getCurrent());
            }
        }else{
            //如果没登录
            //self=false
            model.addAttribute("self",false);
        }
        return "borrow_info";
    }
    @RequestMapping("/borrow_bid")
    @ResponseBody
    public AjaxResult bid(Long bidRequestId, BigDecimal amount){
        AjaxResult result = null;
        try{
            this.bidRequestService.bid(bidRequestId,amount);
            result = new AjaxResult();
        }catch(Exception e){
            e.printStackTrace();
            result = new AjaxResult(false,e.getMessage());
        }
        return result;
    }
}
