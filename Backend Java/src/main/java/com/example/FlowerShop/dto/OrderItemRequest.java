package com.example.FlowerShop.dto;

import com.example.FlowerShop.models.OrderItem;
import jakarta.persistence.criteria.CriteriaBuilder;

public class OrderItemRequest {
    private Integer flowerId;
    private Integer quantity;
    private String flowerName;
    private Double price;
    private String color;
    public OrderItemRequest() {}


    public Integer getFlowerId() {
        return flowerId;
    }

    public void setFlowerId(Integer flowerId) {
        this.flowerId = flowerId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getFlowerName() {
        return flowerName;
    }

    public void setFlowerName(String flowerName) {
        this.flowerName = flowerName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public OrderItemRequest(OrderItem orderItem) {
        this.flowerId = orderItem.getFlower().getFlower_id(); // Assuming OrderItem has a Flower entity
        this.quantity = orderItem.getQuantity();
        this.flowerName = orderItem.getFlower().getFlower_name();
        this.price = orderItem.getFlower().getPrice();
        this.color = orderItem.getFlower().getColor();
    }
}
