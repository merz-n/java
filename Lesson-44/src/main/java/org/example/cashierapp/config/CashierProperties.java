package org.example.cashierapp.config;


import org.example.cashierapp.model.CurrencyCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

@Validated
@ConfigurationProperties(prefix = "cashier")
public class CashierProperties {
    private CurrencyCode currency = CurrencyCode.EUR;

    public CurrencyCode getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyCode currency) {
        this.currency = Objects.requireNonNull(currency, "Currency must not be null");
    }

}
