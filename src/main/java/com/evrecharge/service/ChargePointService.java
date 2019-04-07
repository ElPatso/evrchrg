package com.evrecharge.service;

import com.evrecharge.dto.ChargePointInfoDTO;
import com.evrecharge.dto.PriceDTO;
import com.evrecharge.dto.RentDTO;
import com.stripe.exception.*;

public interface ChargePointService {
    ChargePointInfoDTO getRentInfo(Long id);

    void bookChargePoint(RentDTO rentDTO) throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException;

    PriceDTO calculatePrice(String type, Integer duration);
}
