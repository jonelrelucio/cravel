package com.hojo.cravel.repository;


import com.hojo.cravel.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>{

    Product findByName(String name);
}
