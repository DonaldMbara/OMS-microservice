package com.donmba.productservice.service;

import com.donmba.productservice.dto.ProductRequest;
import com.donmba.productservice.dto.ProductResponse;
import com.donmba.productservice.model.Product;
import com.donmba.productservice.repository.ProductRepository;
import com.donmba.productservice.utils.ProductMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<ProductResponse> getProduct(int id){
        Optional<Product> product = Optional.ofNullable(productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product does not exist with id: " + id)));

        return product.map(ProductMapper::mapToProductResponse);
    }

    public void updateProduct(int id, Product product){

        Product updateProduct = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product does not exist with id: "+ id));

        updateProduct.setName(product.getName());
        updateProduct.setThumbnail(product.getThumbnail());
        updateProduct.setCategory_id(product.getCategory_id());
        updateProduct.setPrice(product.getPrice());
        updateProduct.setDetails(product.getDetails());
        updateProduct.setQuantity(product.getQuantity());

        productRepository.save(updateProduct);
        log.info("Product {} is saved", product.getId());
    }
}
