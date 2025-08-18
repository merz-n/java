package org.example.cashierapp.controller;

import org.example.cashierapp.model.PaymentResult;
import org.example.cashierapp.service.Cashier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private Cashier cashier;

    public PaymentController(Cashier cashier) {
        this.cashier = cashier;
    }

    @PostMapping("/pay")
    public PaymentResult payForYous(){
        return cashier.pay();
    }
}
