package com.evrecharge.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreditCardDTO {
    private String cardNumber;
    private Integer expMonth;
    private Integer expYear;
    private short cvc;
}
