package com.donmba.productservice.controller;

import com.donmba.productservice.dto.ProductRequest;
import com.donmba.productservice.dto.ProductResponse;
import com.donmba.productservice.model.Product;
import com.donmba.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor

public class ProductController {

    private final ProductService productService;

    @PostMapping("/api/auth/product")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createProduct(@RequestBody ProductRequest productRequest) {
        try {
            productService.createProduct(productRequest);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/api/product/{ProductId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductResponse> getProduct(@PathVariable("ProductId") int productId) {

        try {
            Optional<ProductResponse> productResponse = productService.getProduct(productId);

            return productResponse
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

        }

    }
    @GetMapping("/api/product/category/{CategoryId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductResponse>> getProductByCategoryId(@PathVariable("CategoryId") int categoryId) {
        try {
            List<ProductResponse> products = productService.getProductByCategoryId(categoryId);
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/api/product/priceRange")
    public ResponseEntity<List<ProductResponse>> filterByPriceRange(
            @RequestParam("minPrice") double minPrice,
            @RequestParam("maxPrice") double maxPrice) {
        try {
            List<ProductResponse> filteredProducts = productService.findByPriceRange(minPrice, maxPrice);
            return ResponseEntity.ok(filteredProducts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/api/product/name/name")
    public ResponseEntity<List<ProductResponse>> filterByName(@RequestParam("name") String name) {
        try {
            List<ProductResponse> filteredProducts = productService.findByName(name);
            return ResponseEntity.ok(filteredProducts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/api/product")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        try {
            List<ProductResponse> products = productService.getAllProducts();
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/api/auth/product/{ProductId}")
    public ResponseEntity<Void> updateProduct(@PathVariable("ProductId") int productId, @RequestBody Product product) {
        try {
            productService.updateProduct(productId, product);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
