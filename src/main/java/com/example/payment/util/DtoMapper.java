package com.example.payment.util;

import com.example.payment.dto.PaymentResponse;
import com.example.payment.entity.Payment;

public class DtoMapper {
    public static PaymentResponse toPaymentResponse(Payment payment){
        return new PaymentResponse(payment.getReferenceNumber(),
                payment.getAmount(),
                payment.getMethod(),
                payment.getStatus().name());
    }
}
