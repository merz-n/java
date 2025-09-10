package org.example.productservice.dto;

import org.example.productservice.entity.ProductEntity;
import org.example.productservice.generated.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toProduct(ProductEntity entity) {
        Product product = new Product();
        product.setId(entity.getId());
        product.setName(entity.getName());
        product.setPrice(entity.getPrice());
        product.setCurrency(entity.getCurrency());
        product.setDiscount(entity.getDiscount());
        product.setActive(entity.getActive());
        return product;
    }

    public ProductEntity toEntity(Product product) {
        ProductEntity entity = new ProductEntity();
        entity.setName(product.getName());
        entity.setPrice(product.getPrice());
        entity.setCurrency(product.getCurrency());
        entity.setDiscount(product.getDiscount());
        entity.setActive(product.getActive() != null ? product.getActive() : true);
        return entity;
    }
}