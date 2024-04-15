package com.example.demo.entities;

import com.example.demo.services.ProductService;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    @ManyToOne
    private User owner;

    @ManyToOne
    private Product product;

    private Double total;
    private String status;
}
