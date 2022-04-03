package com.restarant.backend.service.impl;

import com.restarant.backend.dto.OrderDetailsDto;
import com.restarant.backend.dto.OrderTotalDto;
import com.restarant.backend.entity.Customer;
import com.restarant.backend.entity.OrderDetails;
import com.restarant.backend.entity.OrderTotal;
import com.restarant.backend.repository.*;
import com.restarant.backend.service.IOrderTotalService;
import com.restarant.backend.service.mapper.IConverterDto;
import com.restarant.backend.service.validate.OrderDetailsValidator;
import com.restarant.backend.service.validate.exception.InvalidDataExeception;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
public class OrderTotalService implements IOrderTotalService {

    private final OrderTotalRepository orderTotalRepository;
    private final IConverterDto<OrderTotal, OrderTotalDto> mapper;
    private final CustomerRepository customerRepository;
    private final TableOrderRepository tableOrderRepository;
    private final TablesRepository tablesRepository;

    public OrderTotalService(OrderTotalRepository orderTotalRepository,
                             IConverterDto<OrderTotal, OrderTotalDto> mapper,
                             CustomerRepository customerRepository,
                             TableOrderRepository tableOrderRepository,
                             TablesRepository tablesRepository) {
        this.orderTotalRepository = orderTotalRepository;
        this.mapper = mapper;
        this.customerRepository = customerRepository;
        this.tableOrderRepository = tableOrderRepository;
        this.tablesRepository = tablesRepository;
    }

    @Override
    public OrderTotalDto create(OrderTotalDto dto) throws InvalidDataExeception {
        throw new NotImplementedException();
    }

    @Override
    public OrderTotalDto update(Long id, OrderTotalDto dto) throws InvalidDataExeception {
        return null;
    }

    @Override
    public OrderTotalDto getById(Long id) {
        OrderTotal orderTotal = orderTotalRepository.findById(id).orElse(null);
        return mapper.convertToDto(
                orderTotalRepository.findById(id).orElse(null)
        );
    }

    @Override
    public boolean deleteById(Long id) throws InvalidDataExeception {
        if (!orderTotalRepository.existsById(id)) {
            throw new InvalidDataExeception("The food[id] not found");
        }
        log.info(String.format("Someone detele orderTotal[id-%d]", id));
        orderTotalRepository.deleteById(id);
        return true;
    }

    @Override
    public Collection<OrderTotalDto> getAll() {
        return null;
    }

    @Override
    public Collection<OrderTotalDto> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public OrderTotal createToCustomer(Customer customer) {
        return null;
    }
}
