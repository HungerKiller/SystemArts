package com.system.arts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.arts.entity.OrderProduct;
import com.system.arts.repository.OrderProductRepository;

@Service
public class OrderProductService {

    @Autowired
    private OrderProductRepository orderProductRepository;

    public List<OrderProduct> getAllOrderProducts() {
        return orderProductRepository.findAll();
    }

    public List<OrderProduct> getOrderProductsByOrderId(int orderId) {
        return orderProductRepository.findByOrderId(orderId);
    }

    public OrderProduct createOrderProduct(OrderProduct OrderProduct) {
        return orderProductRepository.save(OrderProduct);
    }
    
    public void deleteOrderProduct(int id) {
        OrderProduct OrderProduct = orderProductRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("OrderProduct not found with id " + id));
        orderProductRepository.delete(OrderProduct);
    }
}
