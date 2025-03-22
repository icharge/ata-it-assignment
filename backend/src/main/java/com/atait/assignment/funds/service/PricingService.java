package com.atait.assignment.funds.service;

import java.math.BigDecimal;
import java.util.UUID;

public interface PricingService {
    BigDecimal getMidPrice(UUID instrumentId);
}
