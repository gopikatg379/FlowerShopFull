package com.example.FlowerShop.Repositary;

import com.example.FlowerShop.models.Category;
import com.example.FlowerShop.models.Flower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlowerDao extends JpaRepository<Flower,Integer> {
     List<Flower> findByFlowerNameContainingIgnoreCase(String flowerName);
     Optional<Flower> findByFlowerName(String flowerName);
     Optional<Flower> findByCategory(Category categoryId);
}
