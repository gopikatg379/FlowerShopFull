package com.example.FlowerShop.Repositary;

import com.example.FlowerShop.models.Order;
import com.example.FlowerShop.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderDao extends JpaRepository<Order,Integer> {
    Optional<Order>findByUser(User user);
    @Modifying
    @Transactional
    @Query("DELETE FROM OrderItem o WHERE o.flower.id = :flowerId")
    void deleteByFlowerId(@Param("flowerId") Integer flowerId);

}
