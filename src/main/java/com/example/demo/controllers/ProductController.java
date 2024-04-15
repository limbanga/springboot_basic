package com.example.demo.controllers;

import com.example.demo.entities.Product;
import com.example.demo.services.BaseService;
import com.example.demo.services.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/")
public class ProductController
        extends BaseController<Product> {

    private final ProductService service;

    public ProductController(ProductService service) {
        super(service);
        this.service = service;
    }

}
