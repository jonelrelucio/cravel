package com.hojo.cravel.services;

import com.hojo.cravel.model.Product;
import com.hojo.cravel.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        Product product = Product.builder()
                .id(1L)
                .name("testName")
                .quantity(10)
                .price(20)
                .build();

        Mockito.when(productRepository.findByName("testName")).thenReturn(product);
    }

    @Test
    @DisplayName("Get Data based on Valid Product Name")
    //@Disabled TODO
    public void whenValidProductName_thenProductIsFound() {
        String productName = "testName";
        Product found = productService.getProductByName(productName);
        assertEquals(productName, found.getName());
    }
}