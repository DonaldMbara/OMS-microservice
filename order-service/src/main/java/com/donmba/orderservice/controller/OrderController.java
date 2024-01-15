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
import org.springframework.web.reactive.function.client.WebClientException;

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
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
    public CompletableFuture<List<String>> placeOrder(@RequestBody List<OrderRequest> orderRequests) {
        log.info("Placing Order");

        return CompletableFuture.supplyAsync(() -> {
            try {
                return orderService.createOrders(orderRequests);
            } catch (Exception e) {
                log.error("Error placing orders: {}", e.getMessage());
                throw new RuntimeException("Error placing orders", e);
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

