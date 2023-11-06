package com.hojo.cravel.repository;

import com.hojo.cravel.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        Product product = Product.builder()
                .name("pencil")
                .id(0L)
                .price(20)
                .quantity(2)
                .build();

        entityManager.persist(product);
    }

    @Test
    public void whenFindById_thenReturnProduct(){
        Product product = productRepository.findById(0L).get();
        assertEquals(product.getName(), "pencil");
    }

}