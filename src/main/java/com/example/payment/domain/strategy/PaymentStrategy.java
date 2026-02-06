package com.example.payment.domain.strategy;

import com.example.payment.domain.PaymentMethod;
import com.example.payment.entity.Payment;

public interface PaymentStrategy {
    PaymentMethod getType();
    void pay(Payment payment);
}
