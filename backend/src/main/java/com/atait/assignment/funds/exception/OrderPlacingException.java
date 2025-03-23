package com.atait.assignment.funds.exception;

public abstract class OrderPlacingException extends RuntimeException {
    public OrderPlacingException(String message) {
        super(message);
    }
}
