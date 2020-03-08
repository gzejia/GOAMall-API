package com.nuon.goamall.service;

import com.nuon.goamall.model.Spu;
import com.nuon.goamall.repository.SpuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpuServiceImpl implements SpuService {

    @Autowired
    SpuRepository spuRepository;

    @Override
    public Spu getSpu(Long id) {
        return spuRepository.findOneById(id);
    }

    @Override
    public List<Spu> getLatestPaginationSpu() {
        return spuRepository.findAll();
    }
}
