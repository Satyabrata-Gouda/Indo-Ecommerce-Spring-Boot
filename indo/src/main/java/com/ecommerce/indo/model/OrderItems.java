package com.ecommerce.indo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @JsonIgnore
    @ManyToOne
    private OrderItem order;

    @ManyToOne
    private Product product;

    private String size;

    private Integer quantity;

    private Double price;

    private Double discountedPrice;

    private Long userId;

    private LocalDateTime deliveryOn;
}
