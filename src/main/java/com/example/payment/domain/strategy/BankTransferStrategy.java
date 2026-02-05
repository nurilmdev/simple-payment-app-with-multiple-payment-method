package com.example.payment.domain.strategy;

import com.example.payment.domain.PaymentMethod;
import com.example.payment.domain.PaymentStatus;
import com.example.payment.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BankTransferStrategy implements PaymentStrategy{
    @Override
    public String getType() {
        return PaymentMethod.BANK_TRANSFER.name();
    }

    @Override
    public void pay(Payment payment) {
        log.info("generate VA number for Bank Transfer");
        payment.setStatus(PaymentStatus.PENDING);
    }
}
