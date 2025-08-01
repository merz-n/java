package org.example;

import org.example.store.AppConfig;
import org.example.store.Cart;
import org.example.store.ProductRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        ProductRepository repo = context.getBean(ProductRepository.class);
        System.out.println("Список товаров: ");
        repo.getAllProducts().forEach(System.out ::println);

        Cart cart1 = context.getBean(Cart.class);
        Cart cart2 = context.getBean(Cart.class);

        System.out.println("cart1 hashCode: " + cart1.hashCode());
        System.out.println("cart2 hashCode: " + cart2.hashCode());

        cart1.addProductById(1L);
        cart1.addProductById(2L);
        cart1.addProductById(3L);

        cart2.addProductById(3L);
        cart2.addProductById(4L);

        System.out.println("\n Корзина 1:");
        cart1.showCart();

        System.out.println("\n Корзина 2:");
        cart2.showCart();
        context.close();
    }
}