package com.example.demo.controllers;

import com.example.demo.entities.Order;
import com.example.demo.entities.User;
import com.example.demo.exceptions.CustomValidationException;
import com.example.demo.services.OrderService;
import com.example.demo.services.ProductService;
import com.example.demo.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.attribute.UserPrincipal;

@RestController
@RequestMapping("/orders/")
public class OrderController
        extends BaseController<Order> {

    private final OrderService service;
    private final UserService userService;

    private final ProductService productService;

    public OrderController(OrderService service, UserService userService, ProductService productService) {
        super(service);
        this.service = service;
        this.userService = userService;
        this.productService = productService;
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<Order> create(
            @AuthenticationPrincipal User user,
            @RequestBody Order body) throws CustomValidationException {
        // set the owner of the order to the authenticated user
        body.setOwner(user);
        // set product
        var product = productService.get(body.getProduct().getId());
        if (product == null) {
            throw new CustomValidationException("product.id", "Product not found");
        }
        body.setProduct(product);
        //
        var total = product.getPrice() * body.getQuantity();
        body.setTotal(total);
        body.setStatus("pending");

        return super.create(body);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public ResponseEntity<Order> update(Order body)
            throws Exception {
        return super.update(body);
    }
}
