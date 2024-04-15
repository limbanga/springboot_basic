package com.example.demo.repositories;

import com.example.demo.entities.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<Entity extends BaseEntity>
        extends JpaRepository<Entity,Long> {
}
