package com.example.demo.controllers;

import com.example.demo.entities.Order;
import com.example.demo.services.BaseService;
import com.example.demo.services.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders/")
public class OrderController
        extends BaseController<Order> {

    private final OrderService service;

    public OrderController(OrderService service) {
        super(service);
        this.service = service;
    }
}
