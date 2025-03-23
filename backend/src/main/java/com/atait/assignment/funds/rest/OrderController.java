package com.atait.assignment.funds.rest;

import com.atait.assignment.funds.api.OrderApi;
import com.atait.assignment.funds.dto.OrderRequest;
import com.atait.assignment.funds.dto.OrderResponse;
import com.atait.assignment.funds.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController implements OrderApi {

    private final OrderService orderService;

    @Override
    public ResponseEntity<OrderResponse> placeOrder(OrderRequest request) {
        OrderResponse response = orderService.placeOrder(request);

        return ResponseEntity.ok(response);
    }
}

