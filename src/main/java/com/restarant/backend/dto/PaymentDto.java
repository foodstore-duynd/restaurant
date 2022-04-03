package com.restarant.backend.dto;

import java.math.BigDecimal;

public class PaymentDto {
    private Long id;
    private BigDecimal deposit;
    private Long status;
    private OrderTotalDto orderTotal;
}
