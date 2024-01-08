package com.donmba.orderservice.service;

import com.donmba.orderservice.dto.OrderRequest;
import com.donmba.orderservice.dto.OrderResponse;
import com.donmba.orderservice.event.OrderPlacedEvent;
import com.donmba.orderservice.model.Order;
import com.donmba.orderservice.repository.OrderRepository;
import com.donmba.orderservice.utils.OrderMapper;
import com.donmba.orderservice.utils.OrderNumberGenerator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public List<String> createOrders(List<OrderRequest> orderRequests) {
        List<String> orderNumbers = new ArrayList<>();

        orderRequests.forEach(orderRequest -> {
            int productId = orderRequest.getProduct_id();

            Boolean isStockAvailable = webClientBuilder.build()
                    .get()
                    .uri("http://inventory-service/api/inventory/check-stock",
                            uriBuilder -> uriBuilder.queryParam("productId", productId).build())
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();


            if (isStockAvailable != null && isStockAvailable) {
                Order order = Order.builder()
                        .order_number(OrderNumberGenerator.generateOrderNumber())
                        .product_id(orderRequest.getProduct_id())
                        .thumbnail(orderRequest.getThumbnail())
                        .quantity(orderRequest.getQuantity())
                        .customer_id(orderRequest.getCustomer_id())
                        .order_date(orderRequest.getOrder_date())
                        .order_delivery(orderRequest.getOrder_delivery())
                        .status(orderRequest.getStatus())
                        .build();

                orderRepository.save(order);
                kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(order.getOrder_number()));
                log.info("Order placed with order number {} is saved", order.getOrder_number());
                orderNumbers.add(order.getOrder_number());
            } else {
                throw new IllegalArgumentException("Stock is not available for the following product");
            }

        });
        return orderNumbers;
    }


    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream()
                .map(OrderMapper::mapToOrderResponse)
                .toList();
    }

    public Optional<OrderResponse> getOrder(int id) {
        Optional<Order> order = Optional.ofNullable(orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order does not exist with id: " + id)));

        return order.map(OrderMapper::mapToOrderResponse);
    }

    public List<OrderResponse> getCustomerOrder(int customerId) {
        List<Order> orders = orderRepository.findByCustomerId(customerId);

        return orders.stream()
                .map(OrderMapper::mapToOrderResponse)
                .toList();
    }

}
