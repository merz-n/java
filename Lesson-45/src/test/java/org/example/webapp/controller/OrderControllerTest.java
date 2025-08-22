package org.example.webapp.controller;

import org.example.webapp.entity.Order;
import org.example.webapp.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrderService orderService;
    @Test
    void getById_found() throws Exception {
        Order o = new Order(1L,"Iphone 16pro","1250","EUR");
        when(orderService.findById(1L)).thenReturn(Optional.of(o));

        mockMvc.perform(get("/orders/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Iphone 16pro"))
                .andExpect(jsonPath("$.amount").value("1250"))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }
    @Test
    void getById_notFound() throws Exception{
        when(orderService.findById(999L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/orders/999"))
                .andExpect(status().isNotFound());
    }
    @Test
    void getByName_found() throws Exception{
        List<Order> list = List.of(
                new Order(1L,"Iphone 16pro","1250","EUR"),
                new Order(2L,"Iphone 16pro","1550","EUR")
                );

        when(orderService.findByName("Iphone 16pro")).thenReturn(list);

        mockMvc.perform(get("/orders/search").param("name", "Iphone 16pro"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Iphone 16pro"))
                .andExpect(jsonPath("$[0].currency").value("EUR"))
                .andExpect(jsonPath("$[1].id").value(2));
    }
    @Test
    void getByName_notFound() throws Exception{
        when(orderService.findByName("Nothing")).thenReturn(List.of());
        mockMvc.perform(get("/orders/search").param("name", "Nothing"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(0));
    }
}
