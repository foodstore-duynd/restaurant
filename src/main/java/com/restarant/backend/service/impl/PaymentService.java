package com.restarant.backend.service.impl;

import com.restarant.backend.dto.PaymentDto;
import com.restarant.backend.entity.Payment;
import com.restarant.backend.repository.PaymentRepository;
import com.restarant.backend.repository.TablesRepository;
import com.restarant.backend.service.IPaymentService;
import com.restarant.backend.service.mapper.IConverterDto;
import com.restarant.backend.service.validate.exception.InvalidDataExeception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
public class PaymentService implements IPaymentService {

    private final PaymentRepository paymentRepository;
    private final TablesRepository tablesRepository;
    private final IConverterDto<Payment, PaymentDto> mapper;

    public PaymentService(PaymentRepository paymentRepository,
                          TablesRepository tablesRepository,
                          IConverterDto<Payment, PaymentDto> mapper) {
        this.paymentRepository = paymentRepository;
        this.tablesRepository = tablesRepository;
        this.mapper = mapper;
    }

    @Override
    public PaymentDto create(PaymentDto dto) throws InvalidDataExeception {
        return null;
    }

    @Override
    public PaymentDto update(Long id, PaymentDto dto) throws InvalidDataExeception {
        return null;
    }

    @Override
    public PaymentDto getById(Long id) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) throws InvalidDataExeception {
        return false;
    }

    @Override
    public Collection<PaymentDto> getAll() {
        return null;
    }

    @Override
    public Collection<PaymentDto> getAll(Pageable pageable) {
        return null;
    }
}
