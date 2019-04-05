package com.evrecharge.entity;

import com.evrecharge.entity.common.IdComponent;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
@Data
public class User extends IdComponent<User> {
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @OneToMany(mappedBy = "owner")
    private List<ChargePoint> chargePointList;
    @OneToOne(mappedBy = "user")
    private Request request;
    @OneToOne
    private CreditCard creditCard;

}
