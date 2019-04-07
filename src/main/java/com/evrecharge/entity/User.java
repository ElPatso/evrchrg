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
    @OneToMany(mappedBy = "user")
    private List<Request> request;
    @OneToOne
    private CreditCard creditCard;
    @OneToMany(mappedBy = "paymentFrom")
    private List<Payment> sent;
    @OneToMany(mappedBy = "paymentTo")
    private List<Payment> received;
    @OneToOne
    @JoinColumn(name = "user_id")
    private Car car;

}
