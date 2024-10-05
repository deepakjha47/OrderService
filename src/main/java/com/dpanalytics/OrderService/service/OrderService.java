package com.dpanalytics.OrderService.service;

import com.dpanalytics.OrderService.model.OrderRequest;
import com.dpanalytics.OrderService.model.OrderResponse;

public interface OrderService {
    public long placeOrder(OrderRequest orderRequest);

    OrderResponse getOrderDetail(long orderId);
}
