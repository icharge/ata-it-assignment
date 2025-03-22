package com.atait.assignment.funds.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @Enumerated(EnumType.STRING)
    private InstrumentType type;

    @OneToMany(mappedBy = "instrument", cascade = CascadeType.ALL)
    private List<ValidationRule> validationRules;

}
