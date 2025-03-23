package com.atait.assignment.funds.api;

import com.atait.assignment.funds.dto.OrderDto;
import com.atait.assignment.funds.dto.OrderRequest;
import com.atait.assignment.funds.dto.OrderResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Orders", description = "API for placing fund orders")
@RequestMapping("/api/orders")
public interface OrderApi {

    @GetMapping
    List<OrderDto> getAllOrders();

    @GetMapping("/{id}")
    OrderDto getOrder(@PathVariable UUID id);

    @GetMapping("/instrument/{instrumentId}")
    List<OrderDto> getByInstrument(@PathVariable UUID instrumentId);

    @Operation(summary = "Place a fund order", description = "Validates and places an order for Mutual Fund, Index Fund, or Fixed Income instrument")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Order placed successfully"),
            @ApiResponse(responseCode = "400", description = "Validation failed"),
            @ApiResponse(responseCode = "404", description = "Instrument not found")
    })
    @PostMapping
    ResponseEntity<OrderResponse> placeOrder(@Valid @RequestBody OrderRequest request);

}
