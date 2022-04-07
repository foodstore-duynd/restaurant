package com.restarant.backend.service;

import com.restarant.backend.dto.TableOrderDto;
import com.restarant.backend.service.validate.exception.InvalidDataExeception;

import javax.servlet.http.HttpServletRequest;

public interface ITableOrderService extends IServiceAdapter<TableOrderDto> {
    TableOrderDto create(TableOrderDto dto, HttpServletRequest request) throws InvalidDataExeception;
}
