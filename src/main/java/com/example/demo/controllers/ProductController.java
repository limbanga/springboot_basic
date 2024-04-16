package com.example.demo.controllers;

import com.example.demo.entities.Product;
import com.example.demo.exceptions.CustomValidationException;
import com.example.demo.services.BaseService;
import com.example.demo.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Override
    public ResponseEntity<Product> create(
            @RequestBody @Valid Product body
    ) {
        return super.create(body);
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Override
    public ResponseEntity<Product> update(
            @RequestBody @Valid Product body
    ) throws Exception {
        return super.update(body);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PatchMapping
    public ResponseEntity<Product> setActive(@RequestBody Product body)
            throws CustomValidationException {
        var product = service.get(body.getId());
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        product.setActive(body.getActive());
        return ResponseEntity.ok(service.update(product));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Override
    public ResponseEntity<String> delete(Long id) {
        return super.delete(id);
    }
}
