package com.donmba.productservice.service;

import com.donmba.productservice.dto.ProductRequest;
import com.donmba.productservice.dto.ProductResponse;
import com.donmba.productservice.model.Product;
import com.donmba.productservice.repository.ProductRepository;
import com.donmba.productservice.utils.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .thumbnail(productRequest.getThumbnail())
                .category_id(productRequest.getCategory_id())
                .price(productRequest.getPrice())
                .details(productRequest.getDetails())
                .quantity(productRequest.getQuantity())
                .build();

        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts(){
       List<Product> products = productRepository.findAll();

       return products.stream()
               .map(ProductMapper::mapToProductResponse)
               .toList();

    }
}
