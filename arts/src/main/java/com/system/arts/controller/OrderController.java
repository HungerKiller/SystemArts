package com.system.arts.controller;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.system.arts.dto.OrderDto;
import com.system.arts.entity.Order;
import com.system.arts.service.OrderService;

@RestController
@CrossOrigin
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
	private ModelMapper modelMapper;
    
    @GetMapping("/")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> orders = orderService.getAllOrders().stream()
                .map(post -> modelMapper.map(post, OrderDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getResourceById(@PathVariable int id) {
        Order order = orderService.getOrderById(id);
        OrderDto orderDto = modelMapper.map(order, OrderDto.class);
        return ResponseEntity.ok(orderDto);
    }

    @GetMapping("/byUser/{userId}")
    public ResponseEntity<List<OrderDto>> getOrdersByUserId(@PathVariable int userId) {
        List<OrderDto> orders = orderService.getOrdersByUserId(userId).stream()
                .map(post -> modelMapper.map(post, OrderDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(orders);
    }

    @PostMapping("/")
    public ResponseEntity<Order> createOrder(@RequestBody Order Order) {
        Order createdOrder = orderService.createOrder(Order);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable int id, @RequestBody Order updatedOrder) {
        updatedOrder.setId(id);
        Order savedOrder = orderService.updateOrder(updatedOrder);
        return new ResponseEntity<>(savedOrder, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
