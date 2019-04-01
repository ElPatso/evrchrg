package com.evrecharge.entity;

import com.evrecharge.entity.common.IdComponent;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "image")
@Data
public class Image extends IdComponent<Image> {
    private String url;
    @ManyToOne
    private ChargePoint chargePoint;
}
