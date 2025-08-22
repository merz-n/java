package org.example.webapp.repository;


import org.example.webapp.entity.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;

    private Order o1;
    private Order o2;


    @BeforeEach
    public void setUp(){
        o1 = orderRepository.save(new Order("Iphone 16pro", "1250","EUR"));
        o2 = orderRepository.save(new Order("Iphone 17pro", "1250","EUR"));
    }

    @Test
    public void findById_found(){
        Optional<Order> found = orderRepository.findById(o1.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Iphone 16pro");
        assertThat(found.get().getCurrency()).isEqualTo("EUR");

    }
    @Test
    public void findById_notFound(){
        Optional<Order> found = orderRepository.findById(-1L);
        assertThat(found).isEmpty();
    }
    @Test
    public void findByName_found(){
        List<Order> found = orderRepository.findByName("Iphone 16pro");
        assertThat(found).isNotEmpty();
        assertThat(found.get(0).getId()).isEqualTo(o1.getId());

    }
    @Test
    public void findByName_notFound(){
        List<Order> found = orderRepository.findByName("Not Name");
        assertThat(found).isEmpty();

    }

}
