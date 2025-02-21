package com.example.FlowerShop.dto;

import com.example.FlowerShop.models.Order;

import java.util.List;
import java.util.stream.Collectors;

public class OrderRequest {
    private Integer orderId;
    private Integer userId;
    private String address;
    private Long phone;
    private String userName;
    private String status;
    private List<OrderItemRequest> items;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public List<OrderItemRequest> getItems() {
        return items;
    }

    public void setItems(List<OrderItemRequest> items) {
        this.items = items;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OrderRequest() {}
    public OrderRequest(Order order) {
        this.userId = order.getUser().getUserId(); // Assuming Order has a User entity
        this.address = order.getAddress();
        this.phone = order.getPhone();
        this.userName = order.getUser().getName();
        this.orderId = order.getOrder_id();
        this.status = order.getStatus();
        // Convert Order items to DTO list
        this.items = order.getOrderItems().stream()
                .map(OrderItemRequest::new) // Assuming OrderItemRequest has a constructor
                .collect(Collectors.toList());
    }
}
