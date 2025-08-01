package org.example.store;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> products = new ArrayList<>();

    @PostConstruct
    public void init() {
            products.add(new Product(1L, "Хлеб", 1.0));
            products.add(new Product(2L, "Молоко", 1.5));
            products.add(new Product(3L, "Кофе", 4.0));
            products.add(new Product(4L, "Сыр", 3.2));
            products.add(new Product(5L, "Яблоко", 0.8));
    }
    public List<Product> getAllProducts(){
        return products;
    }
    public Product getById(Long id){
        return products.stream()
                .filter(p-> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
