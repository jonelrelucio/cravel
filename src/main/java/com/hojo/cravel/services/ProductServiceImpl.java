package com.hojo.cravel.services;

import com.hojo.cravel.controllers.ProductController;
import com.hojo.cravel.exceptions.ProductNotFoundException;
import com.hojo.cravel.model.Product;
import com.hojo.cravel.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);


    @Override
    public Product saveProduct(Product product) {
        Optional<Product> existingProduct = productRepository.findById(product.getId());
        if (existingProduct.isPresent()) {
            LOGGER.info("Product already exists in the database");
            return null;
        }
        return productRepository.save(product);
    }

    @Override
    public List<Product> saveProducts(List<Product> products) {
        return productRepository.saveAll(products);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(long id) throws ProductNotFoundException {

        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()){
            throw new ProductNotFoundException("Product doesn't exist");
        }

        return product.get();
    }

    @Override
    public Product getProductByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public String deleteProduct(long id) {
        productRepository.deleteById(id);
        return "Product Removed " + id;
    }

    @Override
    public Product updateProduct(Product product) {
        Product existingProduct = productRepository.findById(product.getId()).orElse(null);
        existingProduct.setName(product.getName());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setPrice(product.getPrice());
        return productRepository.save(existingProduct);
    }
}
