package com.nuon.goamall.util;

import com.nuon.goamall.bo.PageCounter;

import java.util.Calendar;
import java.util.Date;

public class CommonUtil {

    public static PageCounter convertToPageParameter(Integer start, Integer count) {
        int pageNum = start / count;

        PageCounter pageCounter = PageCounter.builder()
                .page(pageNum)
                .count(count)
                .build();
        return pageCounter;
    }

    /**
     * @param start 获取数据起始位置
     * @param count 获取数据总量
     * @return 返回获取数据分页对象
     */
    public static PageCounter convert2PageParam(Integer start, Integer count) {
        return PageCounter.builder().page(start / count).count(count).build();
    }

    /**
     * @param date  比较日期
     * @param start 有效开始日期
     * @param end   有效结束日期
     * @return 判断指定日期是否在有效期内
     */
    public static Boolean isInTimeLine(Date date, Date start, Date end) {
        Long time = date.getTime();
        Long startTime = start.getTime();
        Long endTime = end.getTime();
        if (time > startTime && time < endTime) {
            return true;
        }
        return false;
    }

    /**
     * @param expiredTime 时间戳
     * @return 判断指定时间是否超过当前时间
     */
    public static Boolean isOutOfDate(Date expiredTime) {
        Long now = Calendar.getInstance().getTimeInMillis();
        Long expiredTimeStamp = expiredTime.getTime();
        if (now > expiredTimeStamp) {
            return true;
        }
        return false;
    }

    /**
     * @param calendar 日期时间
     * @param seconds  追加秒数
     * @return 在指定日期后延长一定时间
     */
    public static Calendar addSomeSeconds(Calendar calendar, int seconds) {
        calendar.add(Calendar.SECOND, seconds);
        return calendar;
    }
}
