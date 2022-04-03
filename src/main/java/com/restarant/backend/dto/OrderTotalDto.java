package com.restarant.backend.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class OrderTotalDto {
    private Long id;
    private Long voucher;
    private BigDecimal amountTotal;
    private Set<TableOrderDto> tableOrders;
    private Long paymentId;
    private CustomerDto customer;
    private StaffDto staff;
}
