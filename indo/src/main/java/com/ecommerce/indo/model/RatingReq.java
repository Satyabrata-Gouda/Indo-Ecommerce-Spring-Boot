package com.ecommerce.indo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingReq {

    private Long productId;

    private Double rating;
}
