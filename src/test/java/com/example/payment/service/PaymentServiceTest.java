package com.example.payment.service;

import com.example.payment.domain.PaymentStatus;
import com.example.payment.dto.PaymentRequest;
import com.example.payment.dto.PaymentResponse;
import com.example.payment.entity.Payment;
import com.example.payment.domain.PaymentMethod;
import com.example.payment.repository.PaymentRepository;
import com.example.payment.domain.strategy.PaymentStrategy;
import com.example.payment.domain.factory.PaymentStrategyFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class PaymentServiceTest {

    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private PaymentRepository repository;

    @Mock
    private PaymentStrategyFactory paymentStrategyFactory;

    @Mock
    private PaymentStrategy paymentStrategy;

    @Captor
    private ArgumentCaptor<Payment> paymentCaptor;

    @Test
    void create_shouldCreatePaymentSuccessfully() {
        // ===== GIVEN =====
        PaymentRequest request = new PaymentRequest();
        request.setReferenceNumber("REF-001");
        request.setAmount(BigDecimal.valueOf(100000));
        request.setMethod("BANK_TRANSFER");

        when(paymentStrategyFactory.getStrategy(PaymentMethod.BANK_TRANSFER))
                .thenReturn(paymentStrategy);

        when(paymentStrategy.getType())
                .thenReturn(PaymentMethod.BANK_TRANSFER);

        doAnswer(inv->{
          Payment p = inv.getArgument(0);
          p.setStatus(PaymentStatus.PENDING);
          return null;
        }).when(paymentStrategy).pay(any(Payment.class));

        // simulate repo save return value
        when(repository.save(any(Payment.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // ===== WHEN =====
        PaymentResponse response = paymentService.create(request);

        // ===== THEN =====

        // verify strategy dipanggil
        verify(paymentStrategyFactory)
                .getStrategy(PaymentMethod.BANK_TRANSFER);

        verify(paymentStrategy)
                .pay(any(Payment.class));

        // verify entity yg disimpan ke DB
        verify(repository).save(paymentCaptor.capture());

        Payment savedPayment = paymentCaptor.getValue();

        assertThat(savedPayment.getReferenceNumber()).isEqualTo("REF-001");
        assertThat(savedPayment.getAmount()).isEqualByComparingTo("100000");
        assertThat(savedPayment.getCreatedAt()).isNotNull();

        // verify response
        assertThat(response).isNotNull();
        assertThat(response.getReferenceNumber()).isEqualTo("REF-001");
        assertThat(response.getAmount()).isEqualByComparingTo("100000");
    }
}
