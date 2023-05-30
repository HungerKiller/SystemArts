package com.system.arts.dto;

import java.sql.Timestamp;
import java.util.List;

import com.system.arts.entity.OrderProduct;
import com.system.arts.entity.OrderStatus;
import com.system.arts.entity.User;

import lombok.Data;

@Data
public class OrderDto {

    private int id;
    private Timestamp createdAt;
    private User user;
    private OrderStatus orderStatus;
	private List<OrderProduct> orderProducts;

    public OrderDto() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }
}
