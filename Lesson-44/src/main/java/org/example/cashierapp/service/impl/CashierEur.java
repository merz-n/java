package org.example.cashierapp.service.impl;

import org.example.cashierapp.model.CurrencyCode;
import org.example.cashierapp.model.PaymentResult;
import org.example.cashierapp.service.Cashier;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;


public class CashierEur implements Cashier {
    @Override
  public PaymentResult pay(){
      double min = 1.00;
      double max = 100.00;
      double randomValue = ThreadLocalRandom.current().nextDouble(min, max);
      BigDecimal amount = BigDecimal.valueOf(randomValue).setScale(2, RoundingMode.HALF_UP);
      return new PaymentResult(amount, CurrencyCode.EUR);
  }
}
