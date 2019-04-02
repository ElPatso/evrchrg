package com.evrecharge.entity;

import com.evrecharge.entity.common.IdComponent;
import com.evrecharge.entity.enums.ChargeTypeEnum;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "charge_point")
@Data
public class ChargePoint extends IdComponent<ChargePoint> {

    @Column(name = "country")
    private String country;
    @Column(name = "city")
    private String city;
    @Column(name = "post_code")
    private String postCode;
    @Column(name = "address")
    private String address;
    @Column(name = "charge_type")
    @Enumerated(value = EnumType.STRING)
    private ChargeTypeEnum chargeType;
    @Column(name = "access_instructions")
    private String accessInstructions;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
    @OneToMany(mappedBy = "chargePoint")
    private List<Image> imageList;
    @OneToOne(mappedBy = "point")
    private Request request;
}
