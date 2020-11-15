package com.nuon.goamall.service;

import com.nuon.goamall.model.Category;
import com.nuon.goamall.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Map<Integer, List<Category>> getAll() {
        List<Category> roots = categoryRepository.findAllByIsRootOrderByIndexAsc(true);
        List<Category> subs = categoryRepository.findAllByIsRootOrderByIndexAsc(false);

        Map<Integer, List<Category>> map = new HashMap<>();
        map.put(1, roots);
        map.put(2, subs);
        return map;
    }
}
