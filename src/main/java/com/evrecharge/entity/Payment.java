package com.evrecharge.entity;

import com.evrecharge.entity.common.IdComponent;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "payment")
@Data
public class Payment extends IdComponent<Payment> {
    @ManyToOne
    @JoinColumn(name = "payment_from")
    private User paymentFrom;

    @ManyToOne
    @JoinColumn(name = "payment_to")
    private User paymentTo;

    @Column(name = "payment_id")
    private String paymentId;
}
