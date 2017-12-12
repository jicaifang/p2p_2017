package com.xmg.p2p.website.controller;

import com.xmg.p2p.base.util.BidConst;
import com.xmg.p2p.business.query.BidRequestQueryObject;
import com.xmg.p2p.business.service.IBidRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by seemygo on 2017/10/31.
 */
@Controller
public class MainController {
    @Autowired
    private IBidRequestService bidRequestService;
    @RequestMapping("/index")
    public String mainPage(BidRequestQueryObject qo,Model model){
        qo.setStates(new int[]{BidConst.BIDREQUEST_STATE_BIDDING,BidConst.BIDREQUEST_STATE_PAYING_BACK,BidConst.BIDREQUEST_STATE_COMPLETE_PAY_BACK});
        qo.setPageSize(5);
        qo.setCurrentPage(1);
        model.addAttribute("bidRequests",bidRequestService.listIndexData(qo));
        return "main";
    }
    @RequestMapping("/invest")
    public String invest(){
        return "invest";
    }
    @RequestMapping("/invest_list")
    public String investList(BidRequestQueryObject qo,Model model){
        qo.setStates(new int[]{BidConst.BIDREQUEST_STATE_BIDDING,BidConst.BIDREQUEST_STATE_PAYING_BACK,BidConst.BIDREQUEST_STATE_COMPLETE_PAY_BACK});
        qo.setOrderByType(" br.bidRequestState ASC");
        model.addAttribute("pageResult",bidRequestService.queryPage(qo));
        return "invest_list";
    }
}
