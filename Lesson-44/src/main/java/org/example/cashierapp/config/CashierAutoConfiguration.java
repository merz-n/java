package org.example.cashierapp.config;


import org.example.cashierapp.service.Cashier;
import org.example.cashierapp.service.impl.CashierEur;
import org.example.cashierapp.service.impl.CashierUsd;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableConfigurationProperties(CashierProperties.class)
public class CashierAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(Cashier.class)
    @ConditionalOnProperty(prefix = "cashier", name = "currency", havingValue = "EUR", matchIfMissing = true)
    public Cashier eurCashier(){
        return new CashierEur();
    }

    @Bean
    @ConditionalOnMissingBean(Cashier.class)
    @ConditionalOnProperty(prefix = "cashier", name = "currency", havingValue = "USD", matchIfMissing = true)
    public Cashier usdCashier(){
        return new CashierUsd();
    }
}
