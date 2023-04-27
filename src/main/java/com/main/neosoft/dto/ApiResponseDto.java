package com.main.neosoft.dto;

//import com.main.neosoft.entity.Coupon;
//import com.main.neosoft.entity.Programme;
//import com.main.neosoft.entity.Student;
import com.main.neosoft.entity.Subscription;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseDto {

//    private Student student;
//
//    private Programme programme;
//
//    private Coupon coupon;

    private Subscription subscription;
}
