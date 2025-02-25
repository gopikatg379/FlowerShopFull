package com.example.FlowerShop.Services;

import com.example.FlowerShop.Repositary.CategoryDao;
import com.example.FlowerShop.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.w3c.dom.Text;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryDao categoryDao;


    public ResponseEntity<String> addCategory(String categoryName,String description) {
        Category category = new Category();
        category.setCategoryName(categoryName);
        category.setDescription(description);
        categoryDao.save(category);

        return new ResponseEntity<>("Added", HttpStatus.OK);
    }

    public ResponseEntity<List<Category>> getCategory() {
        return new ResponseEntity<>(categoryDao.findAll(),HttpStatus.OK);
    }

    public ResponseEntity<String> updateCategory(Integer categoryId,String categoryName, String description) {
        Optional<Category> optionalCategory = categoryDao.findById(categoryId);
        Category category = optionalCategory.get();
        category.setCategoryName(categoryName);
        category.setDescription(description);
        categoryDao.save(category);
        return ResponseEntity.ok("updated");
    }

    public ResponseEntity<Optional<Category>> getOneCategory(Integer categoryId) {
        return ResponseEntity.ok(categoryDao.findById(categoryId));
    }

    public ResponseEntity<String> deleteCategory(Integer categoryId) {
        categoryDao.deleteById(categoryId);
        return ResponseEntity.ok("deleted");
    }
}
