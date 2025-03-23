package com.atait.assignment.funds.service.impl;

import com.atait.assignment.funds.service.PricingService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class PricingServiceImpl implements PricingService {
    @Override
    public BigDecimal getMidPrice(UUID instrumentId) {
        // Mocking with random price between 95 and 105
        double price = ThreadLocalRandom.current().nextDouble(95.0, 105.0);
        return BigDecimal.valueOf(price).setScale(2, RoundingMode.HALF_UP);
    }
}
