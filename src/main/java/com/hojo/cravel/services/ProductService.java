package com.hojo.cravel.services;

import com.hojo.cravel.exceptions.ProductNotFoundException;
import com.hojo.cravel.model.Product;

import java.util.List;

public interface ProductService {

    Product saveProduct(Product product);

    List<Product> saveProducts(List<Product> products);

    List<Product> getProducts();

    Product getProductById(long id) throws ProductNotFoundException;

    Product getProductByName(String name);

    Product updateProduct(Product product);

    String deleteProduct(long id);

}
