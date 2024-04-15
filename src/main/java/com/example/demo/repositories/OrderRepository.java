package com.example.demo.repositories;

import com.example.demo.entities.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository
        extends BaseRepository<Order> {
}
