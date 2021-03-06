package com.restarant.backend.service.mapper.impl;

import com.restarant.backend.dto.OrderTotalDto;
import com.restarant.backend.entity.OrderTotal;
import com.restarant.backend.entity.Payment;
import com.restarant.backend.service.mapper.AbstractDtoMapperAdapter;

public class OrderTotalMapper extends AbstractDtoMapperAdapter<OrderTotal, OrderTotalDto> {
    public OrderTotalMapper(Class<OrderTotal> classParameter, Class<OrderTotalDto> classDtoParameter) {
        super(classParameter, classDtoParameter);
    }

    @Override
    public OrderTotalDto convertToDto(OrderTotal entity) {
        OrderTotalDto dto = super.convertToDto(entity);
        if(dto == null || entity == null){
            return null;
        }
        dto.setAmountTotal(entity.getAmountTotal());
        dto.setPaymentId(dto.getPaymentId());
        return dto;
    }

    @Override
    public OrderTotal convertToEntity(OrderTotalDto dto) {
        OrderTotal orderTotal = super.convertToEntity(dto);
        if(orderTotal != null && dto != null && dto.getPaymentId() != null){
            Payment payment = new Payment();
            payment.setId(dto.getPaymentId());
            orderTotal.setPayment(payment);
        }
        return orderTotal;
    }
}
