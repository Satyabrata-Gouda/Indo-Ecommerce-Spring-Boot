package com.ecommerce.indo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductReq {

    private String title;

    private String description;

    private Double price;

    private Double discontedPrice;

    private Integer discountPercent;

    private Integer quantity;

    private String brand;

    private String color;

    private Set<Size> size;

    private String imgUrl;

    private String topLevelCategory;

    private String secondLevelCategory;

    private String thirdLevelCategory;
}
