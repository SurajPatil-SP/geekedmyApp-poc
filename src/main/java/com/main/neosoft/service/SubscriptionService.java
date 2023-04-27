package com.main.neosoft.service;

import com.main.neosoft.dto.ApiResponseDto;
import com.main.neosoft.dto.SubscriptionDto;

public interface SubscriptionService {

    public ApiResponseDto processSubscription (SubscriptionDto subscriptionDto);
}
