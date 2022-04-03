package com.restarant.backend.service;

import com.restarant.backend.dto.OrderTotalDto;
import com.restarant.backend.entity.Customer;
import com.restarant.backend.entity.OrderTotal;

public interface IOrderTotalService extends IServiceAdapter<OrderTotalDto> {
    OrderTotal createToCustomer(Customer customer);
}
