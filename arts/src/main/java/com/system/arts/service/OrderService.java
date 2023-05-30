package com.system.arts.service;

import com.system.arts.entity.Order;
import com.system.arts.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(int id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with id " + id));
    }

    public List<Order> getOrdersByUserId(int userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(Order updatedOrder) {
        Optional<Order> optionalOrder = orderRepository.findById(updatedOrder.getId());
        if (optionalOrder.isPresent()) {
            Order existingOrder = optionalOrder.get();
            existingOrder.setOrderStatus(updatedOrder.getOrderStatus());
            return orderRepository.save(existingOrder);
        } else {
            throw new IllegalArgumentException("Order not found with id " + updatedOrder.getId());
        }
    }

    public void deleteOrder(int id) {
        Order Order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with id " + id));
        orderRepository.delete(Order);
    }
}
