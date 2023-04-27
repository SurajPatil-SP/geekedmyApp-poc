package com.main.neosoft.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionDto implements Serializable {

    private static final long serialVersionUID = 1L;

//    private Student student;
//
//    private Programme programme;
//
//    private Coupon coupon;

    private String name;
    private String email;

    private String couponCode;
    private String[] programmeCategory;

    private boolean proMembership;

    private Double amount;

}
