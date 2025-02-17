package com.example.FlowerShop.controller;

import com.example.FlowerShop.Repositary.UserDao;
import com.example.FlowerShop.Services.OrderService;
import com.example.FlowerShop.dto.OrderRequest;
import com.example.FlowerShop.models.User;
import com.example.FlowerShop.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDao userDao;

    public Integer getUserIdFromToken(String token) {
        String email = jwtUtil.extractEmail(token); // Extract email from JWT
        User user = userDao.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getUserId(); // Return userId
    }

    @PostMapping("add")
    public ResponseEntity<String> addOrder(@RequestHeader("Authorization")String token, @RequestBody OrderRequest orderRequest){
        String jwt = token.substring(7);
        Integer userId = getUserIdFromToken(jwt);
        return orderService.placeOrder(userId,orderRequest);
    }
}
