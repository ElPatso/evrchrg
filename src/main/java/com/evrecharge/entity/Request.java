package com.evrecharge.entity;

import com.evrecharge.entity.common.IdComponent;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "request")
@Data
public class Request extends IdComponent<Request> {

    @Column(name = "charge_from")
    private LocalDateTime chargeFrom;
    @Column(name = "charge_to")
    private LocalDateTime chargeTo;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne
    @JoinColumn(name = "charge_point_id")
    private ChargePoint point;
}