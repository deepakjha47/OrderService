package com.dpanalytics.OrderService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDetails {
    private long productId;
    private String productName;
    private long price;
    private long quantity;
}
