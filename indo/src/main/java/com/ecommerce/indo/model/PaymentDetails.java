package com.ecommerce.indo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetails {

    private String paymentMethod;

    private String paymentStatus;

    private String paymentId;

    private String razorPayPaymentLinkId;

    private String razorPayPaymentLinkReferenceId;

    private String razorPayPaymentLinkStatus;

    private String razorPayPaymentId;
}
