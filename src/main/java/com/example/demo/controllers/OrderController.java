package com.example.demo.controllers;

import com.example.demo.entities.Order;
import com.example.demo.entities.User;
import com.example.demo.services.OrderService;
import com.example.demo.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders/")
public class OrderController
        extends BaseController<Order> {

    private final OrderService service;
    private final UserService userService;

    public OrderController(OrderService service, UserService userService) {
        super(service);
        this.service = service;
        this.userService = userService;
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<Order> create(
            @AuthenticationPrincipal User user,
            @RequestBody Order body) {
        body.setOwner(user);
        return super.create(body);
    }



    @Override
    public ResponseEntity<Order> update(Order body) throws Exception {
        return super.update(body);
    }
}
