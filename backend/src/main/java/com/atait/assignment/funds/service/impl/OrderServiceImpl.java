package com.atait.assignment.funds.service.impl;

import com.atait.assignment.funds.dto.OrderRequest;
import com.atait.assignment.funds.dto.OrderResponse;
import com.atait.assignment.funds.entity.FundOrder;
import com.atait.assignment.funds.entity.Instrument;
import com.atait.assignment.funds.entity.InstrumentType;
import com.atait.assignment.funds.entity.ValidationRule;
import com.atait.assignment.funds.repository.FundOrderRepository;
import com.atait.assignment.funds.repository.InstrumentRepository;
import com.atait.assignment.funds.repository.ValidationRuleRepository;
import com.atait.assignment.funds.service.NotificationService;
import com.atait.assignment.funds.service.OrderService;
import com.atait.assignment.funds.service.PricingService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final InstrumentRepository instrumentRepo;
    private final ValidationRuleRepository ruleRepo;
    private final FundOrderRepository orderRepo;
    private final PricingService pricingService;
    private final NotificationService notificationService;

    @Override
    public OrderResponse placeOrder(OrderRequest request) {
        List<String> errors = new ArrayList<>();

        Optional<Instrument> instrumentOpt = instrumentRepo.findById(request.getInstrumentId());
        if (instrumentOpt.isEmpty()) {
            return OrderResponse.failure("Instrument not found");
        }

        Instrument instrument = instrumentOpt.get();
        ValidationRule rule;
        try {
            rule = ruleRepo.getReferenceById(instrument.getId());
        } catch (EntityNotFoundException e) {
            return OrderResponse.failure("Validation rule not found");
        }

        // Validate formatting
        if (rule.getWholeDigits() != null) {
            int wholeDigits = request.getDealtPrice().precision() - request.getDealtPrice().scale();
            if (wholeDigits > rule.getWholeDigits()) {
                errors.add("Dealt price exceeds allowed whole digits");
            }
        }

        if (rule.getDecimalDigits() != null && request.getDealtPrice().scale() > rule.getDecimalDigits()) {
            errors.add("Dealt price exceeds allowed decimal places");
        }

        // Validate amount
        if (rule.getMaxAmount() != null && request.getAmount().compareTo(rule.getMaxAmount()) > 0) {
            errors.add("Amount exceeds maximum allowed");
        }

        if (rule.getMinAmount() != null && request.getAmount().compareTo(rule.getMinAmount()) < 0) {
            errors.add("Amount below minimum allowed");
        }

        // Fixed income deviation
        if (instrument.getType() == InstrumentType.FIXED_INCOME && rule.getPriceDeviationPercent() != null) {
            BigDecimal midPrice = pricingService.getMidPrice(instrument.getId());
            BigDecimal allowedDeviation = midPrice.multiply(BigDecimal.valueOf(rule.getPriceDeviationPercent() / 100.0));
            BigDecimal lower = midPrice.subtract(allowedDeviation);
            BigDecimal upper = midPrice.add(allowedDeviation);

            if (request.getDealtPrice().compareTo(lower) < 0 || request.getDealtPrice().compareTo(upper) > 0) {
                errors.add("Dealt price is outside allowed deviation of mid price");
            }
        }

        if (!errors.isEmpty()) {
            return OrderResponse.failure(errors);
        }

        // Save order
        FundOrder order = new FundOrder();
        order.setId(UUID.randomUUID());
        order.setInstrument(instrument);
        order.setDealtPrice(request.getDealtPrice());
        order.setAmount(request.getAmount());
        orderRepo.save(order);

        // Notify external services (mocked)
        notificationService.notifyAuditing(order);
        notificationService.notifyFundsPriceContribution(order);

        return OrderResponse.success(order.getId());
    }
}
