package com.example.FlowerShop.controller;

import com.example.FlowerShop.Services.FlowerService;
import com.example.FlowerShop.models.Flower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("flowerShop")
public class FlowerController {
    @Autowired
    FlowerService flowerService;

    @PostMapping("add/flower")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> addFlower(
            @RequestParam("flower_name") String flowerName,
            @RequestParam("color") String color,
            @RequestParam("price") Double price,
            @RequestParam("description") String description,
            @RequestParam("image") MultipartFile image,
            @RequestParam("category_id") Integer categoryId) throws IOException {
        if (image.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No image file uploaded.");
        }
        return flowerService.addFlower(flowerName, color, price, description, image,categoryId);
    }

    @GetMapping("get/flowers")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Flower>> getFlowers(){
        return flowerService.getFlower();
    }

    @GetMapping("get/one/{flower_id}")
    public ResponseEntity<Optional<Flower>> getOneFlower(@PathVariable Integer flower_id){
        return flowerService.getOneFlower(flower_id);
    }

    @GetMapping("search")
    public ResponseEntity<List<Flower>> searchFlower(@RequestParam String flower){
        return flowerService.searchFlower(flower);
    }

    @GetMapping("statistics")
    public ResponseEntity<Map<String, Integer>> getFlowerStatisticsByCategory() {
        Map<String, Integer> categoryStatistics = flowerService.getFlowerStatisticsByCategory();
        return ResponseEntity.ok(categoryStatistics);
    }


    @DeleteMapping("delete/{flower_id}")
    public ResponseEntity<String> deleteFlower(@PathVariable Integer flower_id){
        return flowerService.deleteFlower(flower_id);
    }

    @PutMapping("update/{flower_id}")
    public ResponseEntity<String> updateFlower(@PathVariable Integer flower_id,
                                               @RequestParam("flower_name") String flowerName,
                                               @RequestParam("price") double price,
                                               @RequestParam("color") String color,
                                               @RequestParam("description") String description,
                                               @RequestParam("image") MultipartFile image,
                                               @RequestParam("category_id") Integer categoryId) throws IOException {
        return flowerService.updateFlower(flower_id,flowerName,price,color,description,image,categoryId);
    }
}
