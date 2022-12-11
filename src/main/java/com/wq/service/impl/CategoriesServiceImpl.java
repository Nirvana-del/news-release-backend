package com.wq.service.impl;

import com.wq.entity.Category;
import com.wq.entity.CategoryExample;
import com.wq.mapper.CategoryMapper;
import com.wq.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesServiceImpl implements CategoriesService {
    @Autowired
    CategoryMapper categoryMapper;
    @Override
    public List<Category> getCategoryList() {
        CategoryExample categoryExample = new CategoryExample();
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);
        return categoryList;
    }

    @Override
    public Category getCategory(Integer id) {
        Category category = categoryMapper.selectByPrimaryKey(id);
        return category;
    }
    @Override
    public void addCategory(Category category) {
        categoryMapper.insert(category);
    }
    @Override
    public void deleteCategory(Integer id) {
        categoryMapper.deleteByPrimaryKey(id);
    }
    @Override
    public void updateCategory(Category category) {
        categoryMapper.updateByPrimaryKey(category);
    }
}
