package com.restarant.backend.service;

import com.restarant.backend.dto.FoodDto;
import com.restarant.backend.service.validate.exception.InvalidDataExeception;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface IServiceAdapter <T> {
    T create(T dto) throws InvalidDataExeception;

    T update(Long id, T dto) throws InvalidDataExeception;

    T getById(Long id);

    boolean deleteById(Long id) throws InvalidDataExeception;

    Collection<T> getAll();

    Collection<T> getAll(Pageable pageable);
}
