package com.evrecharge.service;

import com.evrecharge.dto.ChargePointInfoDTO;
import com.evrecharge.dto.PriceDTO;
import com.evrecharge.dto.RentDTO;

public interface ChargePointService {
    ChargePointInfoDTO getRentInfo(Long id);

    void bookChargePoint(RentDTO rentDTO);

    PriceDTO calculatePrice(String type, Integer duration);
}
