package com.main.neosoft.repository;

import com.main.neosoft.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {

    Optional<Coupon> findByCouponCode(String couponCode);

    List<Coupon> findByDiscountGreaterThanEqual(Double discount);

}
