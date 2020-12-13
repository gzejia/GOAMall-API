package com.nuon.goamall.service;

import com.nuon.goamall.model.Sku;

import java.util.List;

public interface SkuService {

    List<Sku> getSkuListByIds(List<Long> ids);
}
