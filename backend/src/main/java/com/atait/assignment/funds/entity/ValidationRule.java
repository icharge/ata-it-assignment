package com.atait.assignment.funds.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "validation_rule")
public class ValidationRule {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "instrument_id", nullable = false)
    private Instrument instrument;

    private Integer wholeDigits;

    private Integer decimalDigits;

    private BigDecimal maxAmount;

    private BigDecimal minAmount;

    private Float priceDeviationPercent;

}
