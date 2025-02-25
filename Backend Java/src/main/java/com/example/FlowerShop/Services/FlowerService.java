package com.example.FlowerShop.Services;

import com.example.FlowerShop.Repositary.*;
import com.example.FlowerShop.models.Category;
import com.example.FlowerShop.models.Flower;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FlowerService {
    @Autowired
    FlowerDao flowerDao;
    @Autowired
    CategoryDao categoryDao;
    @Autowired
    OrderDao orderDao;
    @Autowired
    CartItemDao cartItemDao;
    @Autowired
    WishlistDao wishlistDao;
    private static final String UPLOAD_DIR = "uploads/";

    public ResponseEntity<String> addFlower(String flowerName, String color, Double price, String description, MultipartFile image,Integer categoryId) throws IOException {
            if (image.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty.");
            }
            Optional<Category> category = categoryDao.findById(categoryId);
            if(!category.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found.");
            }
            Category category1 = category.get();
            String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.createDirectories(path.getParent()); // Ensure directory exists
            Files.write(path, image.getBytes());
            Flower flower = new Flower();
            flower.setFlower_name(flowerName);
            flower.setColor(color);
            flower.setPrice(price);
            flower.setDescription(description);
            flower.setImage(fileName);
            flower.setCategory(category1);

            flowerDao.save(flower);
            return new ResponseEntity<>("success",HttpStatus.OK);
    }

    public ResponseEntity<List<Flower>> getFlower() {
        return new ResponseEntity<>(flowerDao.findAll(),HttpStatus.OK);
    }

    public ResponseEntity<Optional<Flower>> getOneFlower(Integer flower_id) {
        return new ResponseEntity<>(flowerDao.findById(flower_id),HttpStatus.OK);
    }

    public ResponseEntity<List<Flower>> searchFlower(String flower) {
        return new ResponseEntity<>(flowerDao.findByFlowerNameContainingIgnoreCase(flower),HttpStatus.OK);
    }

    public Map<String, Integer> getFlowerStatistics() {
        List<Flower> flowers = flowerDao.findAll();
        Map<String, Integer> statistics = new HashMap<>();


        for (Flower flower : flowers) {
            String flowerName = flower.getFlower_name();
            statistics.put(flowerName, statistics.getOrDefault(flowerName, 0) + 1);
        }

        return statistics;
    }

    @Transactional
    public ResponseEntity<String> deleteFlower(Integer flowerId) {
        wishlistDao.deleteByFlower_FlowerId(flowerId);
        cartItemDao.deleteByFlower_FlowerId(flowerId);
        orderDao.deleteByFlowerId(flowerId);
        flowerDao.deleteById(flowerId);
        return new ResponseEntity<>("deleted",HttpStatus.OK);
    }


    public ResponseEntity<String> updateFlower(Integer flower_id,String flowerName, double price, String color, String description, MultipartFile image,Integer categoryId) throws IOException {
        Optional<Flower> optionalFlower = flowerDao.findById(flower_id);
        Optional<Category> category = categoryDao.findById(categoryId);
        if(!category.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found.");
        }
        Category category1 = category.get();

        String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
        Path path = Paths.get(UPLOAD_DIR + fileName);
        Files.createDirectories(path.getParent()); // Ensure directory exists
        Files.write(path, image.getBytes());

        Flower flower = optionalFlower.get();
        flower.setFlower_name(flowerName);
        flower.setColor(color);
        flower.setPrice(price);
        flower.setDescription(description);
        flower.setImage(fileName);
        flower.setCategory(category1);
        flowerDao.save(flower);
        return ResponseEntity.ok("Flower updated successfully!");
    }

    public Map<String, Integer> getFlowerStatisticsByCategory() {
        List<Flower> flowers = flowerDao.findAll();
        Map<String, Integer> statistics = new HashMap<>();

        for (Flower flower : flowers) {
            String categoryName = flower.getCategory().getCategoryName(); // Assuming Flower has a Category field
            statistics.put(categoryName, statistics.getOrDefault(categoryName, 0) + 1);
        }

        return statistics;
    }
}
