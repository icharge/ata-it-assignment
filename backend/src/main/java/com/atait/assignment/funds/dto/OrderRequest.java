package com.atait.assignment.funds.dto;

import com.atait.assignment.funds.entity.InstrumentType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class OrderRequest {

    @NotNull(message = "Instrument type is required")
    private InstrumentType instrumentType;

    @NotNull(message = "Instrument ID is required")
    private UUID instrumentId;

    @NotNull(message = "Dealt price is required")
    @DecimalMin(value = "0.01", inclusive = true, message = "Dealt price must be greater than 0")
    private BigDecimal dealtPrice;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", inclusive = true, message = "Amount must be greater than 0")
    private BigDecimal amount;

}
