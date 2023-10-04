package com.donmba.orderservice.utils;

import com.donmba.orderservice.dto.OrderResponse;
import com.donmba.orderservice.model.Order;

public class OrderMapper {
    public static OrderResponse mapToOrderResponse(Order order){
        return OrderResponse.builder()
                .order_id(order.getOrder_id())
                .order_number(order.getOrder_number())
                .product_id(order.getProduct_id())
                .thumbnail(order.getThumbnail())
                .quantity(order.getQuantity())
                .customer_id(order.getCustomer_id())
                .order_date(order.getOrder_date())
                .order_delivery(order.getOrder_delivery())
                .status(order.getStatus())
                .build();
    }
}
