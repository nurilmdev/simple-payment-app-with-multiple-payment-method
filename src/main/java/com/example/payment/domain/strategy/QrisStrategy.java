package com.example.payment.domain.strategy;

import com.example.payment.domain.PaymentMethod;
import com.example.payment.domain.PaymentStatus;
import com.example.payment.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class QrisStrategy implements PaymentStrategy{
    @Override
    public PaymentMethod getType() {
        return PaymentMethod.QRIS;
    }

    @Override
    public void pay(Payment payment) {
        log.info("Generate QR Code");
        payment.setStatus(PaymentStatus.PENDING);
    }
}
