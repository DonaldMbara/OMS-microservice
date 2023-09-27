package com.donmba.productservice.controller;

import com.donmba.productservice.dto.ProductRequest;
import com.donmba.productservice.dto.ProductResponse;
import com.donmba.productservice.model.Product;
import com.donmba.productservice.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor

public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest){
        productService.createProduct(productRequest);
    }

    @GetMapping("/{ProductId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductResponse> getProduct(@PathVariable("ProductId") int productId) {
        Optional<ProductResponse> productResponse = productService.getProduct(productId);

        return productResponse
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        return productService.getAllProducts();
    }

    @PutMapping("/{ProductId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@PathVariable("ProductId") int productId, @RequestBody Product product) {
        productService.updateProduct(productId,product);
    }
}
