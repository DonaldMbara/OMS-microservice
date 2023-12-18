package com.donmba.orderservice.controller;


import com.donmba.orderservice.dto.OrderRequest;
import com.donmba.orderservice.dto.OrderResponse;
import com.donmba.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @TimeLimiter(name = "inventory", fallbackMethod = "fallbackMethod")
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @Retry(name = "inventory")
    public CompletableFuture<ResponseEntity<?>> createOrder(@RequestBody List<OrderRequest> orderRequests) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                List<String> orderIds = orderService.createOrders(orderRequests);
                return ResponseEntity.status(HttpStatus.CREATED).body(orderIds);
            } catch (Exception ex) {
                log.error("Exception occurred while creating orders: {}", ex.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Order creation failed");
            }
        });
    }


    public CompletableFuture<ResponseEntity<?>> fallbackMethod(List<OrderRequest> orderRequests, Throwable throwable) {
        return CompletableFuture.supplyAsync(() -> {
            log.error("Fallback method triggered due to error: {}", throwable.getMessage());

            if (throwable instanceof RuntimeException) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Oops! Something went wrong with order creation. Please try again later.");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Unexpected error occurred. Please contact support.");
            }
        });
    }



    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/orderId/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OrderResponse> getOrder(@PathVariable("orderId") int orderId) {
        Optional<OrderResponse> orderResponse = orderService.getOrder(orderId);

        return orderResponse
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/customerId/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getAllOrders(@PathVariable("customerId") int customerId){
        return orderService.getCustomerOrder(customerId);
    }
}

