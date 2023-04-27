package com.main.neosoft.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Entity
@Table(name = "coupons")
//@Document(collection = "coupon")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coupon implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Integer id;

	private String couponCode;
	private String status;
	private boolean redeemed;

	private String discountBy;
	private Double discountValue;

}
