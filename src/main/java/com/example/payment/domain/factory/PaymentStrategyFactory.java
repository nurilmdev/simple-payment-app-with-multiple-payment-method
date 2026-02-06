package com.example.payment.domain.factory;

import com.example.payment.domain.PaymentMethod;
import com.example.payment.domain.strategy.PaymentStrategy;
import com.example.payment.exception.PaymentNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class PaymentStrategyFactory {
    private final Map<PaymentMethod, PaymentStrategy> strategies = new HashMap<>();

    public PaymentStrategyFactory(List<PaymentStrategy> strategyList) {
        strategyList.forEach(s -> strategies.put(s.getType(),s));
    }
    public PaymentStrategy getStrategy(PaymentMethod paymentMethod){
        PaymentStrategy strategy = strategies.get(paymentMethod);
        if(strategy == null){
            throw new PaymentNotFoundException(String.format("Strategy method [%s] doesn't supported", paymentMethod));
        }
        return strategy;
    }
}
