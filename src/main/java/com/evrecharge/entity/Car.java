package com.evrecharge.entity;

import com.evrecharge.entity.common.IdComponent;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "car")
@Data
public class Car extends IdComponent<Car> {
    private String car;
    private Integer year;
    private String model;

    @OneToOne(mappedBy = "car")
    private User user;
}
