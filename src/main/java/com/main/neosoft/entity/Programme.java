//package com.main.neosoft.entity;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//@Entity
//@Table(name = "programme")
////@Document(collection = "programme")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class Programme {
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long id;
//	@NotBlank(message = "Title is mandatory")
//	private String title;
//	private String[] category;
//	@NotNull(message = "price is mendatory")
//	private Double price;
//
//}
