package org.example.productservice.service;

import org.example.productservice.dto.ProductMapper;
import org.example.productservice.entity.ProductEntity;
import org.example.productservice.generated.model.Product;
import org.example.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    public Product createProduct(Product product) {
        ProductEntity entity = productMapper.toEntity(product);
        ProductEntity savedEntity = productRepository.save(entity);
        return productMapper.toProduct(savedEntity);
    }

    public List<Product> getAllProducts(int limit, int offset) {
        return productRepository.findAll()
                .stream()
                .skip(offset)
                .limit(limit)
                .map(productMapper::toProduct)
                .collect(Collectors.toList());
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toProduct)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    public Product deactivateProduct(Long id) {
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        entity.setActive(false);
        ProductEntity updatedEntity = productRepository.save(entity);

        return productMapper.toProduct(updatedEntity);
    }
}