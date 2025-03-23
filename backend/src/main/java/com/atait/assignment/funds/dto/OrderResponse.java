package com.atait.assignment.funds.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Schema(description = "Order response payload")
public class OrderResponse {

    @Schema(description = "Whether the request was successful", example = "true")
    private boolean success;

    @Schema(description = "UUID of the created order, present if successful", example = "789e4567-e89b-12d3-a456-426614174111")
    private UUID orderId;

    @Schema(description = "List of validation or business errors", example = "[\"Amount exceeds maximum allowed\"]")
    private List<String> errors;

    public static OrderResponse success(UUID orderId) {
        OrderResponse response = new OrderResponse();
        response.setSuccess(true);
        response.setOrderId(orderId);
        return response;
    }

    public static OrderResponse failure(String error) {
        return failure(List.of(error));
    }

    public static OrderResponse failure(List<String> errors) {
        OrderResponse response = new OrderResponse();
        response.setSuccess(false);
        response.setErrors(errors);
        return response;
    }

}
