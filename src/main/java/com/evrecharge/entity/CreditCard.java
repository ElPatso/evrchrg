package com.evrecharge.entity;

import com.evrecharge.entity.common.IdComponent;
import com.evrecharge.entity.enums.CardTypeEnum;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "credit_card")
@Data
public class CreditCard extends IdComponent<CreditCard> {
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "expire_month")
    private Integer expireMonth;
    @Column(name = "expire_year")
    private Integer expireYear;
    @Column(name = "cvc")
    private short cvc;
    @Column(name = "card_type")
    @Enumerated(value = EnumType.STRING)
    private CardTypeEnum cardType;
    @OneToOne
    @JoinColumn(name = "owner")
    private User owner;
}
