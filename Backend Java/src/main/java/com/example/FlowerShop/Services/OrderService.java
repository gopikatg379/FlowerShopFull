package com.example.FlowerShop.Services;

import com.example.FlowerShop.Repositary.FlowerDao;
import com.example.FlowerShop.Repositary.OrderDao;
import com.example.FlowerShop.Repositary.UserDao;
import com.example.FlowerShop.dto.OrderItemRequest;
import com.example.FlowerShop.dto.OrderRequest;
import com.example.FlowerShop.models.Flower;
import com.example.FlowerShop.models.Order;
import com.example.FlowerShop.models.OrderItem;
import com.example.FlowerShop.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private FlowerDao flowerDao;
    public ResponseEntity<String> placeOrder(Integer userId, OrderRequest orderRequest) {
        User user = userDao.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = new Order();
        order.setUser(user);
        order.setAddress(orderRequest.getAddress());
        order.setPhone(orderRequest.getPhone());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("Pending");

        List<OrderItem> orderItems = new ArrayList<>();
        double totalAmount = 0.0;

        for (OrderItemRequest itemRequest : orderRequest.getItems()) {
            Flower flower = flowerDao.findById(itemRequest.getFlowerId())
                    .orElseThrow(() -> new RuntimeException("Flower not found"));

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setFlower(flower);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setPrice(flower.getPrice() * itemRequest.getQuantity());

            totalAmount += orderItem.getPrice();
            orderItems.add(orderItem);
        }
        order.setTotal_price(totalAmount);
        order.setOrderItems(orderItems);

        orderDao.save(order);

        return ResponseEntity.ok("Order placed successfully!");
    }


    public ResponseEntity<List<OrderRequest>> viewOrders() {
        List<OrderRequest> orderDTOs = orderDao.findAll().stream()
                .map(order -> new OrderRequest(order))
                .collect(Collectors.toList());

        return new ResponseEntity<>(orderDTOs, HttpStatus.OK);
    }

    public ResponseEntity<String> approveOrder(Integer orderId) {
        Order order=orderDao.findById(orderId).orElseThrow(()->new RuntimeException("Order not found"));
        order.setStatus("Approved");
        orderDao.save(order);
        return new ResponseEntity<>("approved",HttpStatus.OK);
    }


    public ResponseEntity<List<OrderRequest>> viewOrderUser(Integer userId) {
        User user = userDao.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Optional<Order> orders = orderDao.findByUser(user);
        List<OrderRequest> orderRequests = orders.stream()
                .map(OrderRequest::new) // Convert Order to OrderRequest using the constructor
                .collect(Collectors.toList());
        return new ResponseEntity<>(orderRequests, HttpStatus.OK);
    }
}
