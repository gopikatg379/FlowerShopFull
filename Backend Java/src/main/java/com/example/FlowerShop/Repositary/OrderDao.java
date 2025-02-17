package com.example.FlowerShop.Repositary;

import com.example.FlowerShop.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends JpaRepository<Order,Integer> {
}
