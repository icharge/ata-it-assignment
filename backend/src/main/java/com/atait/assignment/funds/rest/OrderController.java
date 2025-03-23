package com.atait.assignment.funds.rest;

import com.atait.assignment.funds.dto.OrderRequest;
import com.atait.assignment.funds.dto.OrderResponse;
import com.atait.assignment.funds.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Tag(name = "Orders", description = "API for placing fund orders")
public class OrderController {
    private final OrderService orderService;

    @Operation(summary = "Place a fund order", description = "Validates and places an order for Mutual Fund, Index Fund, or Fixed Income instrument")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Order placed successfully"),
            @ApiResponse(responseCode = "400", description = "Validation failed"),
            @ApiResponse(responseCode = "404", description = "Instrument not found")
    })
    @PostMapping
    public ResponseEntity<OrderResponse> placeOrder(@Valid @RequestBody OrderRequest request) {
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

