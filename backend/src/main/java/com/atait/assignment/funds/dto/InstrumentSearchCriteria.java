package com.atait.assignment.funds.dto;

import com.atait.assignment.funds.entity.InstrumentType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Schema(description = "Search criteria for filtering instruments")
public class InstrumentSearchCriteria {

    @Schema(description = "Type of instrument", example = "FIXED_INCOME")
    private InstrumentType instrumentType;

    @Schema(description = "Name of the instrument", example = "Bond")
    private String name;

    @Schema(description = "Account number of the instrument", example = "TI-600006051")
    private String accountNumber;

    @Schema(description = "Filter instruments created in the last X months (for non-fixed income) or filter by maturityDate for fixed income", example = "3")
    private Integer months;

    @Schema(description = "Interest rate (applicable for fixed income)", example = "3.5")
    private BigDecimal interestRate;

    @Schema(description = "Exact maturity date (applicable for fixed income)", example = "2025-12-31")
    private LocalDate maturityDate;

    @Schema(description = "Status of the instrument", example = "ACTIVE")
    private String status;

}

