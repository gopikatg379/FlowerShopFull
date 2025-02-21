package com.example.FlowerShop.Services;

import com.example.FlowerShop.Repositary.CategoryDao;
import com.example.FlowerShop.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryDao categoryDao;


    public ResponseEntity<String> addCategory(String categoryName) {
        Category category = new Category();
        category.setCategoryName(categoryName);
        categoryDao.save(category);
        return new ResponseEntity<>("Added", HttpStatus.OK);
    }

    public ResponseEntity<List<Category>> getCategory() {
        return new ResponseEntity<>(categoryDao.findAll(),HttpStatus.OK);
    }
}
