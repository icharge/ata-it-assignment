package com.atait.assignment.funds.service.impl;

import com.atait.assignment.funds.dto.InstrumentResponse;
import com.atait.assignment.funds.entity.Instrument;
import com.atait.assignment.funds.entity.InstrumentType;
import com.atait.assignment.funds.mapper.InstrumentMapper;
import com.atait.assignment.funds.repository.InstrumentRepository;
import com.atait.assignment.funds.service.InstrumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstrumentServiceImpl implements InstrumentService {

    private final InstrumentRepository repository;
    private final InstrumentMapper mapper;

    @Override
    public List<InstrumentResponse> getAll() {
        List<Instrument> instrumentList = repository.findAll();

        return mapper.toDtoList(instrumentList);
    }

    @Override
    public List<InstrumentResponse> getByType(InstrumentType type) {
        List<Instrument> instrumentList = repository.findByType(type);

        return mapper.toDtoList(instrumentList);
    }
}
