package com.atait.assignment.funds.mapper;

import com.atait.assignment.funds.dto.OrderDto;
import com.atait.assignment.funds.entity.FundOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "instrument.id", target = "instrumentId")
    @Mapping(source = "instrument.name", target = "instrumentName")
    @Mapping(source = "instrument.type", target = "instrumentType")
    OrderDto toDto(FundOrder entity);

    List<OrderDto> toDtoList(List<FundOrder> entities);
}
