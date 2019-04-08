package com.evrecharge.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddressDTO {
    private BigDecimal lat;
    private BigDecimal lng;
    private String title;
}
