package org.example.webapp.repository;

import org.example.webapp.entity.Order;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends Repository<Order, Long> {
    List<Order> findByName(String name);
    Optional<Order> findById(Long id);
    Order save(Order order);

    List<Order> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
}
