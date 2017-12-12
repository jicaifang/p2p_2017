package com.xmg.p2p.base.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static Date getStartDate(Date data){
        if(data==null) return null;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        return calendar.getTime();
    }

    public static Date getEndDate(Date endDate) {
        if (endDate == null) return endDate;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        return calendar.getTime();
    }

    /**
     * 获取两个之间之间相隔的秒数
     * @param d1
     * @param d2
     * @return
     */
    public static long getBetweenTime(Date d1,Date d2){
        return Math.abs((d1.getTime()-d2.getTime())/1000);
    }
}
