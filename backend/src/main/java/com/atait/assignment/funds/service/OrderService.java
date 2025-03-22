package com.atait.assignment.funds.service;

import com.atait.assignment.funds.dto.OrderRequest;
import com.atait.assignment.funds.dto.OrderResponse;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest request);
}
