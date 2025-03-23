package com.atait.assignment.funds.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
@Schema(description = "Order response payload")
public class OrderResponse {

    @Schema(description = "Whether the request was successful", example = "true")
    private boolean success;

    @Schema(description = "UUID of the created order, present if successful", example = "789e4567-e89b-12d3-a456-426614174111")
    private UUID orderId;

    public static OrderResponse success(UUID orderId) {
        OrderResponse response = new OrderResponse();
        response.setSuccess(true);
        response.setOrderId(orderId);
        return response;
    }

}
