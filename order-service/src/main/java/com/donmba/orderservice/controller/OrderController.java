package com.donmba.orderservice.controller;


import com.donmba.orderservice.dto.OrderRequest;
import com.donmba.orderservice.dto.OrderResponse;
import com.donmba.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody OrderRequest orderRequest){
        orderService.createOrder(orderRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OrderResponse> getOrder(@PathVariable("orderId") int orderId) {
        Optional<OrderResponse> orderResponse = orderService.getOrder(orderId);

        return orderResponse
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}

