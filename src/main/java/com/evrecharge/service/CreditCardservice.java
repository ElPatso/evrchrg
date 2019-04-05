package com.evrecharge.service;

import com.evrecharge.dto.CreditCardDTO;

public interface CreditCardservice {
    CreditCardDTO getByCurrentUser();
}
