package org.example.cashierapp.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class PaymentResult {
    private BigDecimal amount;
    private CurrencyCode currency;

    public PaymentResult() {
    }

    public PaymentResult(BigDecimal amount, CurrencyCode currency) {
        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        // Гарантируем 2 знака после запятой
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
        this.currency = Objects.requireNonNull(currency, "Currency must not be null");
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public CurrencyCode getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "PaymentResult{" +
                "amount=" + amount +
                ", currency=" + currency +
                '}';
    }
}
