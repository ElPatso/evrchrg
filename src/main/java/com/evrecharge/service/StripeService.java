package com.evrecharge.service;

import com.evrecharge.dto.PriceDTO;
import com.evrecharge.entity.User;
import com.stripe.exception.*;

public interface StripeService {
    void charge(User target, PriceDTO priceDTO, String token) throws CardException, APIException,
            AuthenticationException, InvalidRequestException, APIConnectionException;
}
