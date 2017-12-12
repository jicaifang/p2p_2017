package com.xmg.p2p.base.query;

import com.xmg.p2p.base.util.DateUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by seemygo on 2017/10/30.
 */
@Setter@Getter
public class BaseAuthQueryObject extends QueryObject {
    private int state = -1;
    private Date beginDate;
    private Date endDate;

    public Date getBeginDate() {
        return DateUtils.getStartDate(beginDate);
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return DateUtils.getEndDate(endDate);
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
