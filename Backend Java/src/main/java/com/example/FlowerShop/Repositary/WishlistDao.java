package com.example.FlowerShop.Repositary;

import com.example.FlowerShop.models.Flower;
import com.example.FlowerShop.models.User;
import com.example.FlowerShop.models.Wishlist;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistDao extends JpaRepository<Wishlist,Integer> {
    List<Wishlist> findByUser(User user);
    boolean existsByUserAndFlower(User user, Flower flower);
    @Transactional
    void deleteByFlower_FlowerId(Integer flowerId);
}
