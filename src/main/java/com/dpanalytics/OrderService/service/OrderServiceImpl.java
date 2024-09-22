package com.dpanalytics.OrderService.service;

import com.dpanalytics.OrderService.entity.Order;
import com.dpanalytics.OrderService.model.OrderRequest;
import com.dpanalytics.OrderService.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public long placeOrder(OrderRequest orderRequest) {
        //Order Entity -> save the data with status order created
        //Product Service -> blocks product(reduce the quantity in db)
        //Payment Service -> used for payments(Either success/Fail)
        log.info("Placing Order Request: {}", orderRequest);
        Order order = Order.builder()
                .productId(orderRequest.getProductId())
                .quantity(orderRequest.getQuantity())
                .amount(orderRequest.getTotalAmount())
                .orderStatus("CREATED")
                .orderDate(Instant.now())
                .build();
        order = orderRepository.save(order);
        log.info("Order placed successfully for id: {}", order.getId());
        return order.getId();
    }
}
