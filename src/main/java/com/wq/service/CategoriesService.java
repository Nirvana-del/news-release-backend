package com.wq.service;

import com.wq.entity.Category;

import java.util.List;

public interface CategoriesService {
     List<Category> getCategoryList();

    Category getCategory(Integer id);

    void addCategory(Category category);
     void deleteCategory(Integer id);
     void updateCategory(Category category);
}
