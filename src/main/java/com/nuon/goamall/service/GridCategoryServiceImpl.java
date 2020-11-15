package com.nuon.goamall.service;

import com.nuon.goamall.model.GridCategory;
import com.nuon.goamall.repository.GridCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GridCategoryServiceImpl implements GridCategoryService{

    @Autowired
    GridCategoryRepository gridCategoryRepository;

    @Override
    public List<GridCategory> getAll() {
        return gridCategoryRepository.findAll();
    }
}
