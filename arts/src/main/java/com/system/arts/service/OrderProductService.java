package com.system.arts.service;

import java.util.List;
import java.util.Optional;

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
    
    public OrderProduct updateOrderProduct(OrderProduct updatedOrderProduct) {
        Optional<OrderProduct> optionalOrderProduct = orderProductRepository.findById(updatedOrderProduct.getId());
        if (optionalOrderProduct.isPresent()) {
            OrderProduct existingOrderProduct = optionalOrderProduct.get();
            existingOrderProduct.setQuantity(updatedOrderProduct.getQuantity());
            return orderProductRepository.save(existingOrderProduct);
        } else {
            throw new IllegalArgumentException("OrderProduct not found with id " + updatedOrderProduct.getId());
        }
    }

    public void deleteOrderProduct(int id) {
        OrderProduct OrderProduct = orderProductRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("OrderProduct not found with id " + id));
        orderProductRepository.delete(OrderProduct);
    }
}
