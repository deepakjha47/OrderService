package com.dpanalytics.OrderService.controller;

import com.dpanalytics.OrderService.model.OrderRequest;
import com.dpanalytics.OrderService.model.OrderResponse;
import com.dpanalytics.OrderService.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Log4j2
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest){
        long orderId = orderService.placeOrder(orderRequest);
        log.info("Order placed");
        return new ResponseEntity<>(orderId, HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable("orderId") long orderId){
        OrderResponse orderResponse = orderService.getOrderDetail(orderId);
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }
}
