package com.example.FlowerShop.controller;

import com.example.FlowerShop.Services.CategoryService;
import com.example.FlowerShop.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("add")
    public ResponseEntity<String> addCategory(@RequestParam("categoryName") String categoryName){
        return categoryService.addCategory(categoryName);
    }

    @GetMapping("get")
    public ResponseEntity<List<Category>> getCategory(){
        return categoryService.getCategory();
    }
}
