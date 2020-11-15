package com.nuon.goamall.service;

import com.nuon.goamall.model.GridCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GridCategoryService {

    List<GridCategory> getAll();
}
