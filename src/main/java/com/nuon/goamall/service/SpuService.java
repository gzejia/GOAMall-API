package com.nuon.goamall.service;

import com.nuon.goamall.model.Spu;

import java.util.List;

public interface SpuService {

    Spu getSpu(Long id);

    List<Spu> getLatestPaginationSpu();
}
