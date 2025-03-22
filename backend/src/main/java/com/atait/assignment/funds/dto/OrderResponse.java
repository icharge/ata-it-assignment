package com.atait.assignment.funds.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class OrderResponse {

    private boolean success;
    private UUID orderId;
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
