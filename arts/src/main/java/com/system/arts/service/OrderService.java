package com.system.arts.service;

import com.system.arts.entity.Order;
import com.system.arts.entity.OrderStatus;
import com.system.arts.entity.User;
import com.system.arts.repository.OrderRepository;
import com.system.arts.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

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

    public Order getCartOrderByUserId(int userId) {
        List<Order> orders = this.getOrdersByUserId(userId);
        Order cartOrder = orders.stream()
                .filter(order -> order.getIsCart())
                .findFirst()
                .orElse(null);

        if (cartOrder == null) {
            Optional<User> optionalUser = this.userRepository.findById(userId);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                Order order = new Order(user, OrderStatus.ACTIVE);
                order.setIsCart(true);
                return this.createOrder(order);
            }
        }

        return cartOrder;
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(Order updatedOrder) {
        Optional<Order> optionalOrder = orderRepository.findById(updatedOrder.getId());
        if (optionalOrder.isPresent()) {
            Order existingOrder = optionalOrder.get();
            existingOrder.setOrderStatus(updatedOrder.getOrderStatus());
            existingOrder.setIsCart(updatedOrder.getIsCart());
            existingOrder.setOrderProducts(updatedOrder.getOrderProducts());
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
