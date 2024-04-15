package com.example.demo.controllers;

import com.example.demo.entities.BaseEntity;
import com.example.demo.services.BaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class BaseController<Entity extends BaseEntity> {

    private final BaseService<Entity> service;

    public BaseController(BaseService<Entity> service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Entity>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entity> get(
            @PathVariable("id") Long id) {
        var entity = service.get(id);
        if (entity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entity);
    }

    @PostMapping
    public ResponseEntity<Entity> create(@RequestBody Entity body) {
        return ResponseEntity.ok(service.create(body));
    }

    @PutMapping
    public ResponseEntity<Entity> update(@RequestBody Entity body)
            throws Exception {
        return ResponseEntity.ok(service.update(body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Deleted");
    }
}
