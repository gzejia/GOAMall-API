package com.nuon.goamall.bo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PageCounter {
    /**
     * 页码
     */
    private int page;
    /**
     * 单页数据量
     */
    private int count;
}
