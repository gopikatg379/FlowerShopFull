package com.example.FlowerShop.Services;

import com.example.FlowerShop.Dao.FlowerDao;
import com.example.FlowerShop.Dao.UserDao;
import com.example.FlowerShop.Dao.WishlistDao;
import com.example.FlowerShop.models.Flower;
import com.example.FlowerShop.models.User;
import com.example.FlowerShop.models.Wishlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class WishlistService {
    @Autowired
    private WishlistDao wishlistDao;

    @Autowired
    private FlowerDao flowerDao;

    @Autowired
    private UserDao userDao;

    public ResponseEntity<String> addWishlist(Integer userId, Integer flowerId) {
        Optional<User> user = userDao.findById(userId);
        if(user.isEmpty()){
            return ResponseEntity.badRequest().body("User not found");
        }
        Optional<Flower> flower = flowerDao.findById(flowerId);
        if(flower.isEmpty()){
            return ResponseEntity.badRequest().body("Flower not found");
        }
        if(wishlistDao.existsByUserAndFlower(user.get(),flower.get())){
            return ResponseEntity.badRequest().body("Flower already in wishlist");
        }
        Wishlist wishlist = new Wishlist();
        wishlist.setFlower(flower.get());
        wishlist.setUser(user.get());
        wishlistDao.save(wishlist);
        return ResponseEntity.ok("Flower added to wishlist");
    }

    public ResponseEntity<List<Wishlist>> viewWishlist(Integer userId) {
        User user = userDao.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<Wishlist> wishlist = wishlistDao.findByUser(user);
        return ResponseEntity.ok(wishlist);
    }

    public ResponseEntity<String> deleteWishlist(Integer wishlistId) {
        wishlistDao.deleteById(wishlistId);
        return new ResponseEntity<>("deleted",HttpStatus.OK);
    }
}
