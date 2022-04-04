package com.restarant.backend.service;

import com.restarant.backend.dto.TableDto;
import com.restarant.backend.model.Pages;
import org.springframework.data.domain.Pageable;

public interface ITableService extends IServiceAdapter<TableDto>{
    Pages getPage(Pageable pageable);
}
