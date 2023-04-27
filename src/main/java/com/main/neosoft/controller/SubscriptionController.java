package com.main.neosoft.controller;

import com.main.neosoft.dto.ApiResponseDto;
import com.main.neosoft.dto.SubscriptionDto;
import com.main.neosoft.exception.ExceptionResponse;
import com.main.neosoft.service.SubscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Slf4j
@RequestMapping("/subscription")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping("/subscribe")
    public ResponseEntity<?> subscribe(@RequestBody SubscriptionDto subscriptionDTO) throws Exception {
        log.info("Executing subscribe...");
        log.info("Request for subscription: {}", subscriptionDTO);

        if (subscriptionDTO.getName() != null && subscriptionDTO.getProgrammeCategory() != null) {
            ApiResponseDto apiResponseDto = subscriptionService.processSubscription(subscriptionDTO);
            log.info("Response for Subscription: {}", apiResponseDto);
            return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
        }

        return new ResponseEntity<>(new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request", new Date()),
                HttpStatus.BAD_REQUEST);
    }

}
