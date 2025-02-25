package com.example.FlowerShop.Repositary;

import com.example.FlowerShop.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemDao extends JpaRepository<OrderItem,Integer> {

}
