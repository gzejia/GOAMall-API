package com.nuon.goamall.service;

import com.nuon.goamall.model.Sku;
import com.nuon.goamall.repository.SkuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkuServiceImpl implements SkuService {

    @Autowired
    private SkuRepository skuRepository;

    @Override
    public List<Sku> getSkuListByIds(List<Long> ids) {
        return this.skuRepository.findAllByIdIn(ids);
    }
}
