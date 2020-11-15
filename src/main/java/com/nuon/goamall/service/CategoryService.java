package com.nuon.goamall.service;

import com.nuon.goamall.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface CategoryService {

    Map<Integer, List<Category>> getAll();
}
