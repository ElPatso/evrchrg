package com.evrecharge.entity;

import com.evrecharge.entity.common.IdComponent;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "feedback")
@Data
public class Feedback extends IdComponent<Feedback> {

    @OneToOne
    @JoinColumn(name = "used_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "charge_id")
    private ChargePoint chargePoint;

    @Column(name = "rate")
    private short rate;

    @Column(name = "description")
    private String description;
}
