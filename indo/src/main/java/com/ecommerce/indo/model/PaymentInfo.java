package com.ecommerce.indo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInfo {

    private String cardHolderName;

    private String cardName;

    private LocalDate expirationDate;

    private String cvv;
}
