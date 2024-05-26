package com.ecommerce.indo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private AppUser user;

    private String orderId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    private LocalDateTime orderOn;

    private LocalDateTime deliveryOn;

    @OneToOne
    private Address shippingTo;

    @Embedded
    private PaymentDetails paymentDetails;

    private Double totalPrice;

    private Double totalDiscountedPrice;

    private Integer discount;

    private String orderStatus;

    private Integer totalItem;

    private LocalDateTime createdAt;

}
