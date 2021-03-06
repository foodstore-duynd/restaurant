package com.restarant.backend.service;

import com.restarant.backend.dto.FoodDto;
import com.restarant.backend.entity.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface IFoodService extends IServiceAdapter<FoodDto> {

    Set<FoodDto> getAll(Pageable pageable);

}
