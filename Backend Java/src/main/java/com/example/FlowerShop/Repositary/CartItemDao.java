package com.example.FlowerShop.Repositary;

import com.example.FlowerShop.models.Cart;
import com.example.FlowerShop.models.CartItems;
import com.example.FlowerShop.models.Flower;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartItemDao extends JpaRepository<CartItems,Integer> {
    Optional<CartItems> findByCartAndFlower(Cart cart, Flower flower);
    @Transactional
    void deleteByFlower_FlowerId(Integer flowerId);
}
