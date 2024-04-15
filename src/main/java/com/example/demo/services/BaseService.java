package com.example.demo.services;

import com.example.demo.entities.BaseEntity;
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
        return repository.save(entity);
    }

    public Entity update(Entity entity)
            throws Exception {
        var isExist = get(entity.getId()) != null;
        if (!isExist) {
            throw new Exception("Entity not found");
        }
        return repository.save(entity);
    }

    public void delete(Long id) {
        var entityToDelete = get(id);
        if (entityToDelete != null) {
            repository.delete(entityToDelete);
        }
    }
}
