package com.nuon.goamall.api.v1;

import com.nuon.goamall.exception.NotFoundException;
import com.nuon.goamall.model.Category;
import com.nuon.goamall.model.GridCategory;
import com.nuon.goamall.service.CategoryService;
import com.nuon.goamall.service.GridCategoryService;
import com.nuon.goamall.vo.CategoriesAllVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
@Validated
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    GridCategoryService gridCategoryService;

    @GetMapping("/all")
    public CategoriesAllVO getAll() {
        Map<Integer, List<Category>> map = categoryService.getAll();
        return new CategoriesAllVO(map);
    }

    @GetMapping("/grid/all")
    public List<GridCategory> getGridCategoryList() {
        List<GridCategory> list = gridCategoryService.getAll();
        if (list.isEmpty()) {
            throw new NotFoundException(30009);
        }
        return list;
    }
}
