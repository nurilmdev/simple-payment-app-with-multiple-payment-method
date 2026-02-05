package com.example.payment.controller;

import com.example.payment.entity.Payment;
import com.example.payment.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping
    public Payment create(@RequestBody Payment payment) {
        return service.create(payment);
    }
}