package org.example.webapp.controller;

import org.example.webapp.entity.Order;
import org.example.webapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getById(@PathVariable Long id){
        return ResponseEntity.of(orderService.findById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Order>> getByName(@RequestParam String name){
        List<Order> orders = orderService.findByName(name);
        return ResponseEntity.ok(orders);
    }

}
