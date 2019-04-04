package com.evrecharge.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PriceDTO {
    private BigDecimal price;
    private String currencySymbol;

    public PriceDTO(BigDecimal price, String currencySymbol) {
        this.price = price;
        this.currencySymbol = currencySymbol;
    }
}
