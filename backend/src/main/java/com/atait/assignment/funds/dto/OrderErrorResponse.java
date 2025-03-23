package com.atait.assignment.funds.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "Order error response payload")
public class OrderErrorResponse {
    @Schema(description = "Whether the request was successful", example = "false")
    private boolean success;

    @Schema(description = "List of validation or business errors", example = "[\"Amount exceeds maximum allowed\"]")
    private List<String> errors;

    public static OrderErrorResponse failure(String error) {
        return failure(List.of(error));
    }

    public static OrderErrorResponse failure(List<String> errors) {
        OrderErrorResponse response = new OrderErrorResponse();
        response.setSuccess(false);
        response.setErrors(errors);
        return response;
    }
}
