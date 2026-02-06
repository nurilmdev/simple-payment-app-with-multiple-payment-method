package com.example.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {
    private String referenceNumber;
    private BigDecimal amount;
    private String method;
    private String status;
}
