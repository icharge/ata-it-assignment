package com.atait.assignment.funds.rest;

import com.atait.assignment.funds.api.OrderApi;
import com.atait.assignment.funds.dto.OrderDto;
import com.atait.assignment.funds.dto.OrderRequest;
import com.atait.assignment.funds.dto.OrderResponse;
import com.atait.assignment.funds.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class OrderController implements OrderApi {

    private final OrderService orderService;

    @Override
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @Override
    public OrderDto getOrder(UUID id) {
        return orderService.getOrder(id);
    }

    @Override
    public List<OrderDto> getByInstrument(UUID instrumentId) {
        return List.of();
    }

    @Override
    public ResponseEntity<OrderResponse> placeOrder(OrderRequest request) {
        OrderResponse response = orderService.placeOrder(request);

        return ResponseEntity.ok(response);
    }
}

