package com.example.demo.services;

import com.example.demo.entities.Product;
import com.example.demo.repositories.BaseRepository;
import com.example.demo.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends BaseService<Product> {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        super(repository);
        this.repository = repository;
    }


}
