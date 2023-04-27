//package com.main.neosoft.entity;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import javax.validation.constraints.NotBlank;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//@Entity
//@Table(name = "student")
////@Document(collection = "student")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//
//public class Student {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private String id;
//	@NotBlank(message = "Name is mandatory")
//	private String name;
//	@NotBlank(message = "email is mandatory")
//	private String email;
//	@NotBlank(message = "password is mandatory")
//	private String password;
//	private boolean isProMember;
//
//	private Programme programme;
//
//}
