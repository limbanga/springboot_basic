package com.example.demo.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product
        extends BaseEntity {

    private String name;
    private String description;
    private Double price;
    private Integer quantity;
}
