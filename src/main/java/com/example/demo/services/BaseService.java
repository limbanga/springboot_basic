package com.example.demo.services;

import com.example.demo.entities.BaseEntity;
import com.example.demo.exceptions.CustomValidationException;
import com.example.demo.repositories.BaseRepository;

import java.util.List;

public abstract class BaseService<Entity extends BaseEntity> {
    private final BaseRepository<Entity> repository;

    public BaseService(BaseRepository<Entity> repository) {
        this.repository = repository;
    }

    public List<Entity> getAll() {
        return repository.findAll();
    }

    public Entity get(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Entity create(Entity entity) {
        entity.setId(null);
        return repository.save(entity);
    }

    public Entity update(Entity entity)
            throws CustomValidationException {
        var entityInBD = get(entity.getId());
        if (entityInBD == null) {
            throw new CustomValidationException("id", "Entity not found");
        }
        entity.setCreatedAt(entityInBD.getCreatedAt());

        return repository.save(entity);
    }

    public void delete(Long id) {
        var entityToDelete = get(id);
        if (entityToDelete != null) {
            repository.delete(entityToDelete);
        }
    }
}
