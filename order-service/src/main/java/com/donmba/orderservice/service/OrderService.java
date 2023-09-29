package com.donmba.orderservice.service;

import com.donmba.orderservice.dto.OrderRequest;
import com.donmba.orderservice.dto.OrderResponse;
import com.donmba.orderservice.model.Order;
import com.donmba.orderservice.repository.OrderRepository;
import com.donmba.orderservice.utils.OrderMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    public void createOrder(OrderRequest orderRequest){

        int productId = orderRequest.getProduct_id();
        int quantity = orderRequest.getQuantity();

        Boolean isStockAvailable = webClientBuilder.build()
                .get()
                .uri("http://localhost:8087/api/inventory/check-stock/{productId}", productId)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if (isStockAvailable != null && isStockAvailable) {
            Order order = Order.builder()
                    .product_id(orderRequest.getProduct_id())
                    .thumbnail(orderRequest.getThumbnail())
                    .quantity(orderRequest.getQuantity())
                    .staff_id(orderRequest.getStaff_id())
                    .order_date(orderRequest.getOrder_date())
                    .order_delivery(orderRequest.getOrder_delivery())
                    .status(orderRequest.getStatus())
                    .build();

            orderRepository.save(order);
            log.info("Order placed with order number {} is saved", order.getOrder_id());

            webClientBuilder.build()
                    .put()
                    .uri("http://localhost:8087/api/inventory/update/{productId}", productId)
                    .bodyValue(quantity)
                    .exchange()
                    .block();
            log.info("Inventory quantity has been updated successfully");

        } else {
            throw  new IllegalArgumentException("Stock is not available for the following product");
        }


    }

    public List<OrderResponse> getAllOrders(){
        List<Order> orders = orderRepository.findAll();

        return orders.stream()
                .map(OrderMapper::mapToOrderResponse)
                .toList();
    }

    public Optional<OrderResponse> getOrder(int id){
        Optional<Order> order = Optional.ofNullable(orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order does not exist with id: " + id)));

        return order.map(OrderMapper::mapToOrderResponse);
    }

}
