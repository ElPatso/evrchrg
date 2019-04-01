package com.evrecharge.service;

import com.evrecharge.dto.ChargePointInfoDTO;

public interface RequestService {
    ChargePointInfoDTO getRentInfo(Long id);
}
