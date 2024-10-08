package com.dpanalytics.OrderService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private long orderId;
    private Instant orderDate;
    private String orderStatus;
    private long amount;

    /**
     * invoking product details using rest template instead of feign client
     */
    private ProductDetails productDetails;

    private PaymentResponse paymentResponse;
}
