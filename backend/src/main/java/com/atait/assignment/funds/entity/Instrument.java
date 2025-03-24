package com.atait.assignment.funds.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "instrument")
public class Instrument {

    @Id
    private UUID id;

    private String name;

    private String accountNumber;

    @Enumerated(EnumType.STRING)
    private InstrumentType type;

    @Enumerated(EnumType.STRING)
    private InstrumentStatus status;

    private BigDecimal interestRate;

    @OneToMany(mappedBy = "instrument", cascade = CascadeType.ALL)
    private List<ValidationRule> validationRules;

    private LocalDateTime createdAt;

    private LocalDate maturityDate;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}
