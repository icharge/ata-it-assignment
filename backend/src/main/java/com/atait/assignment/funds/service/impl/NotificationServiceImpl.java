package com.atait.assignment.funds.service.impl;

import com.atait.assignment.funds.entity.FundOrder;
import com.atait.assignment.funds.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {
    @Override
    public void notifyAuditing(FundOrder order) {
        log.info("[Auditing] Order ID: {}, Amount: {}", order.getId(), order.getAmount());
    }

    @Override
    public void notifyFundsPriceContribution(FundOrder order) {
        log.info("[FundsPriceContribution] Order ID: {}, Price: {}", order.getId(), order.getDealtPrice());
    }
}
