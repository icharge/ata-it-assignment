package com.atait.assignment.funds.exception.advise;

import com.atait.assignment.funds.dto.OrderResponse;
import com.atait.assignment.funds.exception.OrderPlacingException;
import com.atait.assignment.funds.exception.OrderValidationException;
import com.atait.assignment.funds.rest.OrderController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice(assignableTypes = OrderController.class)
public class OrderExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<OrderResponse> handleDtoValidation(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .toList();

        return ResponseEntity.badRequest().body(OrderResponse.failure(errors));
    }

    @ExceptionHandler(OrderPlacingException.class)
    public OrderResponse handleGeneric(OrderPlacingException ex) {
        if (ex instanceof OrderValidationException validationEx) {
            return OrderResponse.failure(validationEx.getErrors());
        }

        return OrderResponse.failure(ex.getMessage());
    }

}
