package com.restarant.backend.dto;

import lombok.Data;

import java.util.Set;

@Data
public class TableOrderDto {
    private Long id;
    private Long orderTime;
    private Set<OrderDetailsDto> orderDetails;
    private Long tableId;
    private Long orderTotalId;
}
