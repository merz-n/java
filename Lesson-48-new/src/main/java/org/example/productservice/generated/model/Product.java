package org.example.productservice.generated.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public class Product {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("discount")
    private BigDecimal discount;

    @JsonProperty("active")
    private Boolean active = true;

    public Product id(Long id) {
        this.id = id;
        return this;
    }

    @Schema(description = "")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product name(String name) {
        this.name = name;
        return this;
    }

    @Schema(required = true, description = "")
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product price(BigDecimal price) {
        this.price = price;
        return this;
    }

    @Schema(required = true, description = "")
    @NotNull
    @Valid
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Product currency(String currency) {
        this.currency = currency;
        return this;
    }

    @Schema(required = true, description = "")
    @NotNull
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Product discount(BigDecimal discount) {
        this.discount = discount;
        return this;
    }

    @Schema(description = "")
    @Valid
    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Product active(Boolean active) {
        this.active = active;
        return this;
    }

    @Schema(description = "")
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(price, product.price) &&
                Objects.equals(currency, product.currency) &&
                Objects.equals(discount, product.discount) &&
                Objects.equals(active, product.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, currency, discount, active);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                ", discount=" + discount +
                ", active=" + active +
                '}';
    }
}