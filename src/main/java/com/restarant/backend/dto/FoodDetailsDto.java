package com.restarant.backend.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class FoodDetailsDto {
    private Long id;
    private String foodSize;
    private BigDecimal discount;
    private BigDecimal amount;
    private Long foodId;
    private Set<FoodMediaDto> foodMedias;

}
