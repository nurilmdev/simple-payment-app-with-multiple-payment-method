package com.example.payment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data@AllArgsConstructor@NoArgsConstructor
public class PaymentRequest {
    @NotBlank
    private String referenceNumber;
    @Positive
    private BigDecimal amount;
    @NotBlank
    private String method;
}
