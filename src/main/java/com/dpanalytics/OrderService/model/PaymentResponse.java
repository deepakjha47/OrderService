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
public class PaymentResponse {
    private long paymentId;
    private long orderId;
    private String status;
    private PaymentMode paymentMode;
    private long amount;
    private Instant paymentDate;

}
