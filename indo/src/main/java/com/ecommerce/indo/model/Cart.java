package com.ecommerce.indo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id",nullable = false)
    private AppUser user;


    @OneToMany(mappedBy = "cart" ,cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<CartItem> cartItems;


    private Double totalPrice;

    private Integer totalItem;

    private Double totalDiscountedPrice;

    private Double discount;



}
