package com.donmba.orderservice.controller;


import com.donmba.orderservice.dto.OrderRequest;
import com.donmba.orderservice.dto.OrderResponse;
import com.donmba.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
    public CompletableFuture<String> createOrder(@RequestBody OrderRequest orderRequest){
        orderService.createOrder(orderRequest);
        return CompletableFuture.supplyAsync(() -> orderService.createOrder(orderRequest));
    }

    public CompletableFuture<String> fallbackMethod(OrderRequest orderRequest, RuntimeException runtimeException){
        return  CompletableFuture.supplyAsync(() -> "Oops! Something went wrong, please be order after some time!");
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

