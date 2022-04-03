package com.restarant.backend.service.mapper.impl;

import com.restarant.backend.dto.OrderDetailsDto;
import com.restarant.backend.entity.OrderDetails;
import com.restarant.backend.entity.TableOrder;
import com.restarant.backend.service.mapper.AbstractDtoMapperAdapter;

public class OrderDetailsMapper extends AbstractDtoMapperAdapter<OrderDetails, OrderDetailsDto> {
    public OrderDetailsMapper(Class<OrderDetails> classParameter, Class<OrderDetailsDto> classDtoParameter) {
        super(classParameter, classDtoParameter);
    }

    @Override
    public OrderDetailsDto convertToDto(OrderDetails entity) {
        OrderDetailsDto dto = super.convertToDto(entity);
        if(dto != null){
            dto.setTableOrderId(entity.getTableOrder().getId());
        }
        return dto;
    }

    @Override
    public OrderDetails convertToEntity(OrderDetailsDto dto) {
        OrderDetails orderDetails = super.convertToEntity(dto);
        if(orderDetails != null && dto != null && dto.getTableOrderId() != null){
            TableOrder tableOrder = new TableOrder();
            tableOrder.setId(dto.getTableOrderId());
            orderDetails.setTableOrder(tableOrder);
        }
        return orderDetails;
    }
}
