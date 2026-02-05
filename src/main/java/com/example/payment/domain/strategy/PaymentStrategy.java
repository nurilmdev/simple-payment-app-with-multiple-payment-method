package com.example.payment.domain.strategy;

import com.example.payment.entity.Payment;

public interface PaymentStrategy {
    String getType();
    void pay(Payment payment);
}
