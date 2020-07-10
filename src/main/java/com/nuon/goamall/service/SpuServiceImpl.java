package com.nuon.goamall.service;

import com.nuon.goamall.model.Spu;
import com.nuon.goamall.repository.SpuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SpuServiceImpl implements SpuService {

    @Autowired
    SpuRepository spuRepository;

    @Override
    public Spu getSpu(Long id) {
        return spuRepository.findOneById(id);
    }

    @Override
    public Page<Spu> getLatestPaginationSpu(Integer pageNum, Integer size) {
        PageRequest page = PageRequest.of(pageNum, size, Sort.by("createTime").descending());
        return spuRepository.findAll(page);
    }

    @Override
    public Page<Spu> getByCategory(Long cid, Boolean isRoot, Integer pageNum, Integer size) {
        PageRequest page = PageRequest.of(pageNum, size);

        if (isRoot) {
            return spuRepository.findByRootCategoryIdOrderByCreateTime(cid, page);
        } else {
            return spuRepository.findByCategoryIdOrderByCreateTime(cid, page);
        }
    }
}
