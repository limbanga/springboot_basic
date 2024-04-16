package com.example.demo.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product
        extends BaseEntity {

    @NotEmpty(message = "Name is required")
    @Length(min = 3, max = 255, message = "Name must be between 3 and 255 characters")
    private String name;
    @NotEmpty(message = "Description is required")
    @Length(min = 3, max = 255, message = "Description must be between 3 and 255 characters")
    private String description;
    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price must be greater than 0")
    private Double price;
    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity must be greater than 0")
    private Integer quantity;

    private Boolean active = true;

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Order> orders;
}
