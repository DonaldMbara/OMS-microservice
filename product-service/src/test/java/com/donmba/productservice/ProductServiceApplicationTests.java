package com.donmba.productservice;

import com.donmba.productservice.dto.ProductRequest;
import com.donmba.productservice.model.Product;
import com.donmba.productservice.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@Transactional
class ProductServiceApplicationTests {

	@Container
	private static final MySQLContainer mySQLContainer = new MySQLContainer<>("mysql:8.0.30")
			.withDatabaseName("testcontainer")
			.withUsername("root")
			.withPassword("Do1999ld#");

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	@MockBean
	private ProductRepository productRepository;

	@Test
	void shouldCreateProduct() throws Exception {
		ProductRequest productRequest = getProductRequest();
		String productRequestString = objectMapper.writeValueAsString(productRequest);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(productRequestString)
		).andExpect(status().isCreated());

		verify(productRepository, times(1)).save(any(Product.class));
		Assertions.assertEquals(0, productRepository.findAll().size());	}

	private ProductRequest getProductRequest() {
		return ProductRequest.builder()
				.name("Oppo")
				.thumbnail("iVBORw0KGgoAAAANSUhEUgAAA0IAAAI=".getBytes())
				.category_id("1")
				.price(BigDecimal.valueOf(699.99))
				.details("A high-quality smartphone with advanced features.")
				.build();
	}

}
