package com.example.payment.entity;

import com.example.payment.domain.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String referenceNumber;
    private BigDecimal amount;
    private String method;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    private LocalDateTime createdAt;
}