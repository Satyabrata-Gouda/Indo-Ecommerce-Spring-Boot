package com.ecommerce.indo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddItemReq {

    private Long productId;
    private String size;
    private Integer quantity;
    private Double price;
}
