package com.example.FlowerShop.Repositary;

import com.example.FlowerShop.models.Cart;
import com.example.FlowerShop.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartDao extends JpaRepository<Cart,Integer> {
    Optional<Cart> findByUser(User user);
}
