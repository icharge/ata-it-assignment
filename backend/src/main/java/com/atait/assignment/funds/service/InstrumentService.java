package com.atait.assignment.funds.service;

import com.atait.assignment.funds.entity.Instrument;
import com.atait.assignment.funds.entity.InstrumentType;

import java.util.List;

public interface InstrumentService {
    List<Instrument> getAll();

    List<Instrument> getByType(InstrumentType type);
}
