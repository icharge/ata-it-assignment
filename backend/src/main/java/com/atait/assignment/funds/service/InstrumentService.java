package com.atait.assignment.funds.service;

import com.atait.assignment.funds.dto.InstrumentResponse;
import com.atait.assignment.funds.entity.InstrumentType;

import java.util.List;

public interface InstrumentService {
    List<InstrumentResponse> getAll();

    List<InstrumentResponse> getByType(InstrumentType type);
}
