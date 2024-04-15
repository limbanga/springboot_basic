package com.example.demo.services;

import com.example.demo.entities.Order;
import com.example.demo.repositories.BaseRepository;
import com.example.demo.repositories.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService
        extends BaseService<Order> {
    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
