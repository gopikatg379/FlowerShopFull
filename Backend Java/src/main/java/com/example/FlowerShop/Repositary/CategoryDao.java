package com.example.FlowerShop.Repositary;

import com.example.FlowerShop.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDao extends JpaRepository<Category,Integer> {
}
