package com.main.neosoft.service.impl;

import com.main.neosoft.constants.ApplicationConstants;
import com.main.neosoft.dto.ApiResponseDto;
import com.main.neosoft.dto.StudentDto;
import com.main.neosoft.dto.SubscriptionDto;
//import com.main.neosoft.entity.Programme;
//import com.main.neosoft.entity.Student;
import com.main.neosoft.entity.Coupon;
import com.main.neosoft.entity.Subscription;
import com.main.neosoft.repository.CouponRepository;
import com.main.neosoft.repository.SubscriptionRepository;
import com.main.neosoft.service.StudentService;
import com.main.neosoft.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private StudentService studentService;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private CouponRepository couponRepository;

    @Override
    public ApiResponseDto processSubscription(SubscriptionDto subscriptionDto) {
        ApiResponseDto apiResponseDto = new ApiResponseDto();

        Double finalCourseprice = 0d;
        Double lowestProgramPrice = 0d;
        Double discountPrice = 0d;
        Double proDiscount = 0d;
        Long programmeCount = 0l;
        boolean couponAutoApplied = false;

        if (Objects.nonNull(subscriptionDto.getProgrammeCategory())) {
            List<String> list = Arrays.asList(subscriptionDto.getProgrammeCategory());
            Long certificaionCount = list.stream().filter(e -> e.equalsIgnoreCase(ApplicationConstants.CERTIFICATION)).count();
            Long degreeCount = list.stream().filter(e -> e.equalsIgnoreCase(ApplicationConstants.DEGREE)).count();
            Long diplomaCount = list.stream().filter(e -> e.equalsIgnoreCase(ApplicationConstants.DIPLOMA)).count();


            Double certificationPrice = certificaionCount * ApplicationConstants.CERTIFICATION_COST;
            Double degreePrice = degreeCount * ApplicationConstants.DEGREE_COST;
            Double diplomaPrice = diplomaCount * ApplicationConstants.DIPLOMA_COST;
            finalCourseprice = (certificationPrice + degreePrice + diplomaPrice);
            subscriptionDto.setAmount(finalCourseprice);
            // Pro-membership
            if (subscriptionDto.isProMembership()){
                subscriptionDto.setProMembership(true);
                proDiscount = (0.01 * diplomaPrice) + (0.02 * certificationPrice) + (0.03 * degreePrice);
                finalCourseprice = (finalCourseprice + ApplicationConstants.PROMEMBERSHIP_FEE) - proDiscount;
                subscriptionDto.setAmount(finalCourseprice);
            }

            programmeCount = (certificaionCount + degreeCount + diplomaCount);

            // Auto Apply Coupon
            if (programmeCount > 4) {
                subscriptionDto.setCouponCode(ApplicationConstants.AUTO_APPLY_COUPON);
                couponAutoApplied = true;

                String certificationCategory = "";
                String diplomaCategory = "";
                String degreeCategory = "";
                for (String category : list) {
                    if (category.equalsIgnoreCase(ApplicationConstants.CERTIFICATION)) {
                        certificationCategory = "certification";
                    }
                    if (category.equalsIgnoreCase(ApplicationConstants.DEGREE)) {
                        degreeCategory = "degree";
                    }
                    if (category.equalsIgnoreCase(ApplicationConstants.DIPLOMA)) {
                        diplomaCategory = "diploma";
                    }
                }

                if (certificationCategory.equals(ApplicationConstants.CERTIFICATION)) {
                    if (degreeCategory.equals(ApplicationConstants.DEGREE)) {
                        if (diplomaCategory.equals(ApplicationConstants.DIPLOMA)) {
                            lowestProgramPrice = minOfThree(ApplicationConstants.CERTIFICATION_COST, ApplicationConstants.DEGREE_COST, ApplicationConstants.DIPLOMA_COST);
                            finalCourseprice = finalCourseprice - lowestProgramPrice;
                            subscriptionDto.setAmount(finalCourseprice);
                        } else {
                            lowestProgramPrice = minOfTwo(ApplicationConstants.CERTIFICATION_COST, ApplicationConstants.DEGREE_COST);
                            finalCourseprice = finalCourseprice - lowestProgramPrice;
                            subscriptionDto.setAmount(finalCourseprice);
                        }
                    } else {
                        finalCourseprice = finalCourseprice - ApplicationConstants.CERTIFICATION_COST;
                        subscriptionDto.setAmount(finalCourseprice);
                    }
                } else if (degreeCategory.equals(ApplicationConstants.DEGREE)) {
                    if (diplomaCategory.equals(ApplicationConstants.DIPLOMA)) {
                        if (certificationCategory.equals(ApplicationConstants.CERTIFICATION)) {
                            lowestProgramPrice = minOfThree(ApplicationConstants.CERTIFICATION_COST, ApplicationConstants.DEGREE_COST, ApplicationConstants.DIPLOMA_COST);
                            finalCourseprice = finalCourseprice - lowestProgramPrice;
                            subscriptionDto.setAmount(finalCourseprice);
                        } else {
                            lowestProgramPrice = minOfTwo(ApplicationConstants.DIPLOMA_COST, ApplicationConstants.DEGREE_COST);
                            finalCourseprice = finalCourseprice - lowestProgramPrice;
                            subscriptionDto.setAmount(finalCourseprice);
                        }
                    } else {
                        finalCourseprice = finalCourseprice - ApplicationConstants.DEGREE_COST;
                        subscriptionDto.setAmount(finalCourseprice);
                    }
                } else if (diplomaCategory.equals(ApplicationConstants.DIPLOMA)) {
                    if (certificationCategory.equals(ApplicationConstants.CERTIFICATION)) {
                        if (degreeCategory.equals(ApplicationConstants.DEGREE)) {
                            lowestProgramPrice = minOfThree(ApplicationConstants.CERTIFICATION_COST, ApplicationConstants.DEGREE_COST, ApplicationConstants.DIPLOMA_COST);
                            finalCourseprice = finalCourseprice - lowestProgramPrice;
                            subscriptionDto.setAmount(finalCourseprice);
                        } else {
                            lowestProgramPrice = minOfTwo(ApplicationConstants.DIPLOMA_COST, ApplicationConstants.CERTIFICATION_COST);
                            finalCourseprice = finalCourseprice - lowestProgramPrice;
                            subscriptionDto.setAmount(finalCourseprice);
                        }

                    } else {
                        finalCourseprice = finalCourseprice - ApplicationConstants.DIPLOMA_COST;
                        subscriptionDto.setAmount(finalCourseprice);
                    }

                } else {
                    finalCourseprice = finalCourseprice - lowestProgramPrice;
                    subscriptionDto.setAmount(finalCourseprice);
                }

            }

            // Apply 5% Coupon
            if (!couponAutoApplied) {
                if (programmeCount >= 2 && finalCourseprice <= 10000) {
                    Optional<Coupon> couponObj = couponRepository.findByCouponCode(subscriptionDto.getCouponCode().toUpperCase());
                    if (couponObj.isPresent()) {
                        Coupon coupon = couponObj.get();
                        discountPrice = coupon.getDiscountValue() * finalCourseprice;
                        finalCourseprice = finalCourseprice - discountPrice;
                        subscriptionDto.setCouponCode(coupon.getCouponCode());
                        subscriptionDto.setAmount(finalCourseprice);
                    }
//                discountPrice = 0.05 * finalCourseprice;
//                finalCourseprice = finalCourseprice - discountPrice;
//                subscriptionDto.setCouponCode("DEAL_G5");
//                subscriptionDto.setAmount(finalCourseprice);
                }
            }

            // Apply 20% Coupon
            if (!couponAutoApplied) {
                if (finalCourseprice >= 10000) {
                    Optional<Coupon> couponObj = couponRepository.findByCouponCode(subscriptionDto.getCouponCode().toUpperCase());
                    if (couponObj.isPresent()) {
                        Coupon coupon = couponObj.get();
                        discountPrice = coupon.getDiscountValue() * finalCourseprice;
                        finalCourseprice = finalCourseprice - discountPrice;
                        subscriptionDto.setCouponCode(coupon.getCouponCode());
                        subscriptionDto.setAmount(finalCourseprice);
                    }
//                subscriptionDto.setCouponCode("DEAL_G20");
//                discountPrice = 0.2 * finalCourseprice;
//                finalCourseprice = finalCourseprice - discountPrice;
//                subscriptionDto.setAmount(finalCourseprice);
                }
            }

            // Enrollment Fee
            if (finalCourseprice <= 6666) {
                finalCourseprice = finalCourseprice + ApplicationConstants.ENROLLMENT_FEE;
                subscriptionDto.setAmount(finalCourseprice);

            }

        }

        Subscription subscription = setSubscription(subscriptionDto);
        subscriptionRepository.save(subscription);
        apiResponseDto.setSubscription(subscription);
        return apiResponseDto;
    }

    public static double minOfThree(double a, double b, double c) {
        return Math.min(Math.min(a, b), c);
    }

    public static double minOfTwo(double a, double b) {
        return (a < b) ? a : b;
    }

    public static Subscription setSubscription (SubscriptionDto subscriptionDto) {
        Subscription subscription = new Subscription();
        subscription.setName(subscriptionDto.getName());
        subscription.setEmail(subscriptionDto.getEmail());
        subscription.setProgrammeCategory(subscriptionDto.getProgrammeCategory());
        subscription.setAmount(subscriptionDto.getAmount());
        subscription.setProMembership(subscriptionDto.isProMembership());
        subscription.setStatus(ApplicationConstants.ACTIVE);
        return subscription;
    }

}
