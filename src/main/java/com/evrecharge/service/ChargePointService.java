package com.evrecharge.service;

import com.evrecharge.dto.ChargePointInfoDTO;
import com.evrecharge.dto.PriceDTO;
import com.evrecharge.entity.Request;

public interface ChargePointService {
    ChargePointInfoDTO getRentInfo(Long id);

    void bookChargePoint(ChargePointInfoDTO requestDTO);

    PriceDTO calculatePrice(String type, Integer duration);
}
