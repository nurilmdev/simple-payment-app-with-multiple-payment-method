package com.example.payment.service;

import com.example.payment.domain.PaymentMethod;
import com.example.payment.domain.factory.PaymentStrategyFactory;
import com.example.payment.domain.strategy.PaymentStrategy;
import com.example.payment.entity.Payment;
import com.example.payment.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class PaymentService {
    private final PaymentRepository repository;
    private final PaymentStrategyFactory paymentStrategyFactory;

    public PaymentService(PaymentRepository repository, PaymentStrategyFactory paymentStrategyFactory) {
        this.repository = repository;
        this.paymentStrategyFactory = paymentStrategyFactory;
    }

    public Payment create(Payment payment) {
        PaymentStrategy strategy = paymentStrategyFactory.getStrategy(PaymentMethod.valueOf(payment.getMethod()));
        log.info("Payment method: {}", strategy.getType());
        strategy.pay(payment);
        payment.setCreatedAt(LocalDateTime.now());
        log.info("Payment created with status {} at {}", payment.getStatus(), payment.getCreatedAt());
        return repository.save(payment);
    }
}