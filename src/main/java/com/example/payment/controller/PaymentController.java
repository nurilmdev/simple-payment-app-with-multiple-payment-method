package com.example.payment.controller;

import com.example.payment.dto.PaymentRequest;
import com.example.payment.dto.PaymentResponse;
import com.example.payment.dto.Response;
import com.example.payment.entity.Payment;
import com.example.payment.service.PaymentService;
import com.example.payment.util.DtoMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Response<PaymentResponse>> create(@Valid @RequestBody PaymentRequest payment) {
        return ResponseEntity.ok(
                new Response<>("Berhasil Membuat Transaksi",
                        service.create(payment))
        );
    }
}