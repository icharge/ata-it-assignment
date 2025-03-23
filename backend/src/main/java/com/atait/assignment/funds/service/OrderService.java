package com.atait.assignment.funds.service;

import com.atait.assignment.funds.dto.OrderDto;
import com.atait.assignment.funds.dto.OrderRequest;
import com.atait.assignment.funds.dto.OrderResponse;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    List<OrderDto> getAllOrders();

    OrderDto getOrder(UUID id);

    OrderResponse placeOrder(OrderRequest request);
}
