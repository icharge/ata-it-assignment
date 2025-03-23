package com.atait.assignment.funds.mapper;

import com.atait.assignment.funds.dto.InstrumentResponse;
import com.atait.assignment.funds.entity.Instrument;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InstrumentMapper {

    InstrumentResponse toDto(Instrument instrument);

    Instrument toEntity(InstrumentResponse dto);

    List<InstrumentResponse> toDtoList(List<Instrument> instruments);

}
