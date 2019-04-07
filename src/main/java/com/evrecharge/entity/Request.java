package com.evrecharge.entity;

import com.evrecharge.entity.common.IdComponent;
import com.evrecharge.entity.enums.RequestStatusEnum;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "request")
@Data
public class Request extends IdComponent<Request> {

    @Column(name = "charge_from")
    private LocalDateTime chargeFrom;
    @Column(name = "charge_to")
    private LocalDateTime chargeTo;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "charge_point_id")
    private ChargePoint point;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private RequestStatusEnum status;
    @Column(name = "charged")
    private BigDecimal charged;
}