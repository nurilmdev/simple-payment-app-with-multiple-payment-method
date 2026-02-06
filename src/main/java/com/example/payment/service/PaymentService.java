package com.example.payment.service;

import com.example.payment.domain.PaymentMethod;
import com.example.payment.domain.factory.PaymentStrategyFactory;
import com.example.payment.domain.strategy.PaymentStrategy;
import com.example.payment.dto.PaymentRequest;
import com.example.payment.dto.PaymentResponse;
import com.example.payment.entity.Payment;
import com.example.payment.repository.PaymentRepository;
import com.example.payment.util.DtoMapper;
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

    public PaymentResponse create(PaymentRequest request) {
        PaymentStrategy strategy = paymentStrategyFactory.getStrategy(PaymentMethod.valueOf(request.getMethod()));
        log.info("Payment method: {}", strategy.getType());
        Payment payment = new Payment();
        payment.setReferenceNumber(request.getReferenceNumber());
        payment.setAmount(request.getAmount());
        strategy.pay(payment);
        payment.setCreatedAt(LocalDateTime.now());
        log.info("Payment created with status {} at {}", payment.getStatus(), payment.getCreatedAt());
        return DtoMapper.toPaymentResponse(repository.save(payment));
    }
}