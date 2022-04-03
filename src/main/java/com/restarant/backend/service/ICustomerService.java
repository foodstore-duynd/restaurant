package com.restarant.backend.service;

import com.restarant.backend.entity.Customer;
import com.restarant.backend.dto.CustomerDto;
import com.restarant.backend.service.validate.exception.InvalidDataExeception;

import java.util.Set;

public interface ICustomerService {
    Customer save(CustomerDto customerDto) throws InvalidDataExeception;

    Set<Customer> getAll();

    Customer getCustomerById(Long id);

    void detele(Long id) throws InvalidDataExeception;

    boolean isExistById(Long id);
}
