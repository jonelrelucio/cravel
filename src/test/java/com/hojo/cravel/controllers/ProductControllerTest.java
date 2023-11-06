package com.hojo.cravel.controllers;

import com.hojo.cravel.exceptions.ProductNotFoundException;
import com.hojo.cravel.model.Product;
import com.hojo.cravel.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = Product.builder()
                .name("pencil")
                .id(1L)
                .price(20)
                .quantity(3)
                .build();
    }

    @Test
    void addProduct() throws Exception{
        Product inputProduct = Product.builder()
                .name("pencil")
                .id(1L)
                .price(20)
                .quantity(3)
                .build();
        Mockito.when(productService.saveProduct(inputProduct)).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.post("/addProduct")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "id": 1,
                            "name":"pencil",
                            "quantity":3,
                            "price":20
                        }"""))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void findProductById() throws Exception {
        Mockito.when(productService.getProductById(1L)).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.get("/productById/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(product.getName()));
    }
}