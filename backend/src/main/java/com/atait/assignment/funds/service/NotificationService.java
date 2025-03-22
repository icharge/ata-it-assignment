package com.atait.assignment.funds.service;

import com.atait.assignment.funds.entity.FundOrder;

public interface NotificationService {
    void notifyAuditing(FundOrder order);
    void notifyFundsPriceContribution(FundOrder order);
}
