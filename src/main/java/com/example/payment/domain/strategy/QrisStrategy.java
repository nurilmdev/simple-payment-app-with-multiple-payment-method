package com.example.payment.domain.strategy;

import com.example.payment.domain.PaymentMethod;
import com.example.payment.domain.PaymentStatus;
import com.example.payment.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class QrisStrategy implements PaymentStrategy{
    @Override
    public String getType() {
        return PaymentMethod.QRIS.name();
    }

    @Override
    public void pay(Payment payment) {
        log.info("generate QR Code");
        payment.setStatus(PaymentStatus.PENDING);
    }
}
