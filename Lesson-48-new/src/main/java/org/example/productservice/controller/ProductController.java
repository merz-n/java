package org.example.productservice.controller;

import org.example.productservice.generated.api.ProductsApi;
import org.example.productservice.generated.model.Product;
import org.example.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController implements ProductsApi {

    @Autowired
    private ProductService productService;

    @Override
    public ResponseEntity<Product> createProduct(Product product) {
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.status(201).body(createdProduct);
    }

    @Override
    public ResponseEntity<List<Product>> getAllProducts(Integer limit, Integer offset) {
        List<Product> products = productService.getAllProducts(
                limit != null ? limit : 25,
                offset != null ? offset : 0
        );
        return ResponseEntity.ok(products);
    }

    @Override
    public ResponseEntity<Product> getProductById(Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @Override
    public ResponseEntity<Product> deactivateProduct(Long id) {
        try {
            Product deactivatedProduct = productService.deactivateProduct(id);
            return ResponseEntity.ok(deactivatedProduct);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("not found")) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.internalServerError().build();
        }
    }
}