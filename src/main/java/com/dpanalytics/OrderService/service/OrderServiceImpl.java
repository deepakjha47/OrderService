package com.dpanalytics.OrderService.service;

import com.dpanalytics.OrderService.entity.Order;
import com.dpanalytics.OrderService.exception.CustomException;
import com.dpanalytics.OrderService.external.client.PaymentService;
import com.dpanalytics.OrderService.external.client.ProductService;
import com.dpanalytics.OrderService.external.request.PaymentRequest;
import com.dpanalytics.OrderService.model.OrderRequest;
import com.dpanalytics.OrderService.model.OrderResponse;
import com.dpanalytics.OrderService.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private PaymentService paymentService;

    @Override
    public long placeOrder(OrderRequest orderRequest) {
        //Order Entity -> save the data with status order created
        //Product Service -> blocks product(reduce the quantity in db)
        //Payment Service -> used for payments(Either success/Fail)
        log.info("Placing Order Request: {}", orderRequest);
        productService.reduceProductQuantity(orderRequest.getProductId(), orderRequest.getQuantity());
        Order order = Order.builder()
                .productId(orderRequest.getProductId())
                .quantity(orderRequest.getQuantity())
                .amount(orderRequest.getTotalAmount())
                .orderStatus("CREATED")
                .orderDate(Instant.now())
                .build();
        order = orderRepository.save(order);

        log.info("Calling Payment service to complete the payment");

        PaymentRequest paymentRequest = PaymentRequest.builder()
                .orderId(order.getId())
                .paymentMode(orderRequest.getPaymentMode())
                .amount(orderRequest.getTotalAmount())
                .referenceNumber("kmmdk")
                .build();

        String orderStatus = null;
        try {
            paymentService.doPayment(paymentRequest);
            log.info("Order placed successfully so changing status to placed");
            orderStatus = "PLACED";
        } catch(Exception e) {
            log.error("Payment failed, changing status to failed");
            orderStatus = "PAYMENT_FAILED";
        }

        order.setOrderStatus(orderStatus);
        orderRepository.save(order);

        log.info("Order placed successfully for id: {}", order.getId());
        return order.getId();
    }

    @Override
    public OrderResponse getOrderDetail(long orderId) {
        log.info("Getting order details for ID: {}", orderId);

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new CustomException("Order not found with id: " + orderId
                , "NOT_FOUND", 404));

        OrderResponse orderResponse = OrderResponse.builder()
                .orderStatus(order.getOrderStatus())
                .amount(order.getAmount())
                .orderDate(order.getOrderDate())
                .orderId(order.getId())
                .build();

        return orderResponse;
    }
}
