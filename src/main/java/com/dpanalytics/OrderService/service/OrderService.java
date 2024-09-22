package com.dpanalytics.OrderService.service;

import com.dpanalytics.OrderService.model.OrderRequest;
import org.springframework.http.ResponseEntity;

public interface OrderService {
    public long placeOrder(OrderRequest orderRequest);
}
