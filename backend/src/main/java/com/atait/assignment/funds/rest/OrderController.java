package com.atait.assignment.funds.rest;

import com.atait.assignment.funds.api.OrderApi;
import com.atait.assignment.funds.dto.OrderRequest;
import com.atait.assignment.funds.dto.OrderResponse;
import com.atait.assignment.funds.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController implements OrderApi {

    private final OrderService orderService;

    @Override
    public ResponseEntity<OrderResponse> placeOrder(OrderRequest request) {
        OrderResponse response = orderService.placeOrder(request);

        if (!response.isSuccess()) {
            if (response.getErrors().contains("Instrument not found") ||
                    response.getErrors().contains("Validation rule not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(response);
    }
}

