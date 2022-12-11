package com.wq.controller;

import com.wq.entity.Category;
import com.wq.service.impl.CategoriesServiceImpl;
import com.wq.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "*")
public class CategoryController {
    @Autowired
    CategoriesServiceImpl categoriesServiceImpl;

    @GetMapping
    public Result getCategoryList(){
        try {
            List<Category> categoryList = categoriesServiceImpl.getCategoryList();
            Map<String, List<Category>> map = new HashMap<>();
            map.put("categoryList", categoryList);
            System.out.println(map);
            return Result.success(map);
        }catch (Exception e){
            return Result.error(null);
        }
    }

    @PostMapping
    public Result addCategory(Category category){
        Map<String, String> map = new HashMap<>();
        try {
            System.out.println(category);
            categoriesServiceImpl.addCategory(category);
            map.put("status", "success");
            System.out.println(map);
            return Result.success(map);
        }catch (Exception e){
            map.put("status", "error");
            System.out.println(map);
            return Result.error(map);
        }
    }

    @DeleteMapping("/{id}")
    public Result deleteCategory(@PathVariable Integer id){
        System.out.println(id);
        Map<String, String> map = new HashMap<>();
        try {
            categoriesServiceImpl.deleteCategory(id);
            map.put("status", "success");
            System.out.println(map);
            return Result.success(map);
        }catch (Exception e){
            map.put("status", "error");
            System.out.println(map);
            return Result.error(map);
        }
    }

    @PatchMapping("/{id}")
    public Result updateCategory(@PathVariable Integer id, Category category){
        System.out.println(id);
        System.out.println(category);
        Map<String, String> map = new HashMap<>();
        try {
            categoriesServiceImpl.updateCategory(category);
            map.put("status", "success");
            System.out.println(map);
            return Result.success(map);
        }catch (Exception e){
            System.out.println(e);
            map.put("status", "error");
            System.out.println(map);
            return Result.error(map);
        }
    }

//    public R test(){
//        return R.ok().message("").data("")
//    }
}
