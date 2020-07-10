package com.nuon.goamall.service;

import com.nuon.goamall.model.Spu;
import org.springframework.data.domain.Page;

public interface SpuService {

    Spu getSpu(Long id);

    Page<Spu> getLatestPaginationSpu(Integer pageNum, Integer size);

    Page<Spu> getByCategory(Long cid, Boolean isRoot, Integer pageNum, Integer size);
}
