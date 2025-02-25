package com.example.FlowerShop.controller;

import com.example.FlowerShop.Services.CategoryService;
import com.example.FlowerShop.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("add")
    public ResponseEntity<String> addCategory(@RequestBody Category category){
        return categoryService.addCategory(category.getCategoryName(),category.getDescription());
    }

    @GetMapping("get")
    public ResponseEntity<List<Category>> getCategory(){
        return categoryService.getCategory();
    }

    @GetMapping("get/one/{categoryId}")
    public ResponseEntity<Optional<Category>> getOneCategory(@PathVariable Integer categoryId){
        return categoryService.getOneCategory(categoryId);
    }

    @PutMapping("update/{categoryId}")
    public ResponseEntity<String> updateCategory(@PathVariable("categoryId") Integer categoryId,@RequestBody Category category){
        return categoryService.updateCategory(categoryId,category.getCategoryName(),category.getDescription());
    }

    @DeleteMapping("delete/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Integer categoryId){
        return categoryService.deleteCategory(categoryId);
    }
}
