package com.donmba.orderservice.utils;

import com.donmba.orderservice.dto.OrderResponse;
import com.donmba.orderservice.model.Order;

public class OrderMapper {
    public static OrderResponse mapToOrderResponse(Order order){
        return OrderResponse.builder()
                .order_id(order.getOrder_id())
                .product_id(order.getProduct_id())
                .thumbnail(order.getThumbnail())
                .quantity(order.getQuantity())
                .staff_id(order.getStaff_id())
                .order_date(order.getOrder_date())
                .order_delivery(order.getOrder_delivery())
                .status(order.getStatus())
                .build();
    }
}
