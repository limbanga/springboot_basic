package com.example.demo.controllers;

import com.example.demo.entities.Product;
import com.example.demo.services.BaseService;
import com.example.demo.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products/")
public class ProductController
        extends BaseController<Product> {

    private final ProductService service;

    public ProductController(ProductService service) {
        super(service);
        this.service = service;
    }

    @PostMapping
    @Override
    public ResponseEntity<Product> create(
            @RequestBody @Valid Product body
    ) {
        return super.create(body);
    }

    @PutMapping
    @Override
    public ResponseEntity<Product> update(
            @RequestBody @Valid Product body
    ) throws Exception {
        return super.update(body);
    }
}
