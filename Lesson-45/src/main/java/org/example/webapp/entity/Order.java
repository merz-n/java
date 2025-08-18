package org.example.webapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 255, nullable = false)
    private String name;
    @Column(length = 255)
    private String amount;
    @Column(length = 3)
    private String currency;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Order() {
    }

    public Order(Long id, String name, String amount, String currency) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.currency = currency;
    }

    public Order(String name, String amount, String currency) {
        this.name = name;
        this.amount = amount;
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount='" + amount + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}
