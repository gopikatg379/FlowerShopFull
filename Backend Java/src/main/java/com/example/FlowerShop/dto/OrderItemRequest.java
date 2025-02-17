package com.example.FlowerShop.dto;

public class OrderItemRequest {
    private Integer flowerId;
    private Integer quantity;

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
}
