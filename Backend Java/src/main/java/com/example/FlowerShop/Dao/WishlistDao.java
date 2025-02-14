package com.example.FlowerShop.Dao;

import com.example.FlowerShop.models.Flower;
import com.example.FlowerShop.models.User;
import com.example.FlowerShop.models.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistDao extends JpaRepository<Wishlist,Integer> {
    List<Wishlist> findByUser(User user);
    boolean existsByUserAndFlower(User user, Flower flower);
}
