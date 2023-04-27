package com.main.neosoft.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Entity
//@Table(name = "subscription")
//@Document(collection = "subscription")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int subscriptionId;

//    private String studentId;

    private String status;

    private Double amount;

//    @OneToOne
//    private Student student;
//
//    @OneToOne
//    private Programme programme;
//
//    @OneToOne
//    private Coupon coupon;

    private String name;
    private String email;

    private String couponCode;
    private String[] programmeCategory;

    private boolean proMembership;





}
