package com.atait.assignment.funds.dto;

import com.atait.assignment.funds.entity.InstrumentType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Schema(description = "Order request payload")
public class OrderRequest {

    @NotNull(message = "Instrument type is required")
    @Schema(description = "Type of instrument", example = "MUTUAL_FUND", requiredMode = Schema.RequiredMode.REQUIRED)
    private InstrumentType instrumentType;

    @NotNull(message = "Instrument ID is required")
    @Schema(description = "UUID of the instrument", example = "a1b2c3d4-0001-0000-0000-000000000001", requiredMode = Schema.RequiredMode.REQUIRED)
    private UUID instrumentId;

    @NotNull(message = "Dealt price is required")
    @DecimalMin(value = "0.01", message = "Dealt price must be greater than 0")
    @Schema(description = "Price at which the customer wants to buy/sell", example = "102.55", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal dealtPrice;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    @Schema(description = "Amount of units to buy/sell", example = "5000", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal amount;

}
