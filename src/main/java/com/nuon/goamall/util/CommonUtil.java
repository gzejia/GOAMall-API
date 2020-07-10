package com.nuon.goamall.util;

import com.nuon.goamall.bo.PageCounter;

public class CommonUtil {

    /**
     * @param start 获取数据起始位置
     * @param count 获取数据总量
     * @return 返回获取数据分页对象
     */
    public static PageCounter convert2PageParam(Integer start, Integer count) {
        return PageCounter.builder().page(start / count).count(count).build();
    }
}
