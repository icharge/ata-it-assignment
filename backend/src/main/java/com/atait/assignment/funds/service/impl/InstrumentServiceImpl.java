package com.atait.assignment.funds.service.impl;

import com.atait.assignment.funds.entity.Instrument;
import com.atait.assignment.funds.entity.InstrumentType;
import com.atait.assignment.funds.repository.InstrumentRepository;
import com.atait.assignment.funds.service.InstrumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstrumentServiceImpl implements InstrumentService {

    private final InstrumentRepository repository;

    @Override
    public List<Instrument> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Instrument> getByType(InstrumentType type) {
        return repository.findByType(type);
    }
}
