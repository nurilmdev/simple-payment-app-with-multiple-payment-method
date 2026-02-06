package com.example.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data@AllArgsConstructor@NoArgsConstructor
public class PaymentRequest {
    private String referenceNumber;
    private BigDecimal amount;
    private String method;
}
