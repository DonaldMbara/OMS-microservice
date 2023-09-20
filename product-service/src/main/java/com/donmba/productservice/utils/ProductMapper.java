package com.donmba.productservice.utils;

import com.donmba.productservice.dto.ProductResponse;
import com.donmba.productservice.model.Product;

public class ProductMapper {

    public static ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .thumbnail(product.getThumbnail())
                .category_id(product.getCategory_id())
                .price(product.getPrice())
                .details(product.getDetails())
                .quantity(product.getQuantity())
                .build();
    }
}
