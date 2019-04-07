package com.evrecharge.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PriceDTO {
    private BigDecimal price;
    private String currencySymbol;
    private String currency;

    public PriceDTO(BigDecimal price, String currencySymbol, String currency) {
        this.price = price;
        this.currencySymbol = currencySymbol;
        this.currency = currency;
    }
}
