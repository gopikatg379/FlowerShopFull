package com.example.FlowerShop.controller;

import com.example.FlowerShop.Dao.UserDao;
import com.example.FlowerShop.Services.WishlistService;
import com.example.FlowerShop.models.User;
import com.example.FlowerShop.models.Wishlist;
import com.example.FlowerShop.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("wishlist")
public class WishlistController {
    @Autowired
    private UserDao userDao;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private WishlistService wishlistService;

    public Integer getUserIdFromToken(String token) {
        String email = jwtUtil.extractEmail(token); // Extract email from JWT
        User user = userDao.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getUserId(); // Return userId
    }

    @PostMapping("add")
    public ResponseEntity<String> addWishlist(@RequestHeader("Authorization") String token, @RequestParam Integer flowerId){
        String jwt = token.substring(7);
        Integer userId = getUserIdFromToken(jwt);
        return wishlistService.addWishlist(userId,flowerId);
    }

    @GetMapping("view")
    public ResponseEntity<List<Wishlist>> viewWishlist(@RequestHeader("Authorization")String token){
        String jwt = token.substring(7);
        Integer userId = getUserIdFromToken(jwt);
        return wishlistService.viewWishlist(userId);
    }
}
