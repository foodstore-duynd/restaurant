package com.restarant.backend.service.impl;

import com.restarant.backend.entity.Customer;
import com.restarant.backend.repository.CustomerRepository;
import com.restarant.backend.service.ICustomerService;
import com.restarant.backend.dto.CustomerDto;
import com.restarant.backend.service.validate.CustomerValidator;
import com.restarant.backend.service.validate.exception.InvalidDataExeception;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerValidator customerValidate;
    private final ModelMapper modelMapper;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        customerValidate = new CustomerValidator();
        modelMapper = new ModelMapper();
    }

    @Override
    public Customer save(CustomerDto customerDto) throws InvalidDataExeception {
        Customer customer = modelMapper.map(customerDto, Customer.class);
        customerValidate.validate(customer);
        return customerRepository.save(customer);
    }

    @Override
    public Set<Customer> getAll() {
        return new LinkedHashSet<>(customerRepository.findAll());
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.getById(id);
    }

    @Override
    public void detele(Long id) throws InvalidDataExeception {
        if (!customerRepository.existsById(id)) {
            throw new InvalidDataExeception("customer-id not exists");
        }
        customerRepository.deleteById(id);
    }

    @Override
    public boolean isExistById(Long id) {
        return customerRepository.existsById(id);
    }

}
