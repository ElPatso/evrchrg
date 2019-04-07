package com.evrecharge.service.implementation;

import com.evrecharge.dto.PriceDTO;
import com.evrecharge.entity.Payment;
import com.evrecharge.entity.User;
import com.evrecharge.repository.PaymentRepository;
import com.evrecharge.service.StripeService;
import com.evrecharge.service.UserService;
import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class StripeServiceImpl implements StripeService {
    @Value("${stripe.private}")
    private String secretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    private PaymentRepository paymentRepository;
    private UserService userService;

    public StripeServiceImpl(PaymentRepository paymentRepository,
                             UserService userService) {
        this.paymentRepository = paymentRepository;
        this.userService = userService;
    }

    @Override
    @Transactional
    public void charge(User target, PriceDTO priceDTO, String token) throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {

        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", priceDTO.getPrice().multiply(BigDecimal.valueOf(100)).intValue());
        chargeParams.put("currency", priceDTO.getCurrency());
        chargeParams.put("source", token);
        Payment payment = new Payment();
        payment.setPaymentTo(target);
        payment.setPaymentFrom(userService.getCurrentUser());
        payment.setPaymentId(Charge.create(chargeParams).getId());
        paymentRepository.save(payment);
    }
}

