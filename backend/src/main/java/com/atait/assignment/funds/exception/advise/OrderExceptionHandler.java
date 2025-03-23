package com.atait.assignment.funds.exception.advise;

import com.atait.assignment.funds.dto.OrderResponse;
import com.atait.assignment.funds.exception.OrderPlacingException;
import com.atait.assignment.funds.exception.OrderValidationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrderExceptionHandler {

    @ExceptionHandler(OrderPlacingException.class)
    public OrderResponse handleGeneric(OrderPlacingException ex) {
        if (ex instanceof OrderValidationException validationEx) {
            return OrderResponse.failure(validationEx.getErrors());
        }

        return OrderResponse.failure(ex.getMessage());
    }

}
