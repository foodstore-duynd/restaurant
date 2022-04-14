package com.restarant.backend.dto;

import lombok.Data;

@Data
public class OrderDetailsDto {
    private Long id;
    private Long quantity;
    private FoodDetailsDto foodDetalls;
    private Integer status = 0;
    private Long tableOrderId;
}
