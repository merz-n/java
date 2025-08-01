package org.example.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class Cart {
    private final ProductRepository productRepository;
    private List<Product> items = new ArrayList<>();

    @Autowired
    public Cart(ProductRepository productRepository){
        this.productRepository= productRepository;
    }
    public  void addProductById(Long id){
        Product product = productRepository.getById(id);
        if(product != null){
            items.add(product);
            System.out.println("Продукт добавлен " + product);
        } else {
            System.out.println("Товар не найден. id = " + id);
        }
    }
    public void showCart(){
        System.out.println("Товары в корзине: ");
        if (items.isEmpty()){
            System.out.println("Корзина пуста ");
        }else{
            items.forEach(System.out ::println);
            System.out.println("сумма товаров в корзине: " + getTotalPrice());
        }
    }
    public double getTotalPrice() {
        return items.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }
}
