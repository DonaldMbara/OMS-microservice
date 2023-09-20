package com.donmba.orderservice.service;

import com.donmba.orderservice.dto.OrderRequest;
import com.donmba.orderservice.dto.OrderResponse;
import com.donmba.orderservice.model.Order;
import com.donmba.orderservice.repository.OrderRepository;
import com.donmba.orderservice.utils.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    public void createOrder(OrderRequest orderRequest){
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
        log.info("Order {} is saved", order.getOrder_id());

    }

    public List<OrderResponse> getAllOrders(){
        List<Order> orders = orderRepository.findAll();

        return orders.stream()
                .map(OrderMapper::mapToOrderResponse)
                .toList();
    }

    public Optional<OrderResponse> getOrder(String id){
        Optional<Order> order = orderRepository.findById(id);

        return order.map(OrderMapper::mapToOrderResponse);
    }

}
