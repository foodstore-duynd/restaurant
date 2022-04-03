package com.restarant.backend.service.mapper.impl;

import com.restarant.backend.dto.FoodDto;
import com.restarant.backend.entity.Food;
import com.restarant.backend.service.mapper.AbstractDtoMapperAdapter;

public class FoodMapper extends AbstractDtoMapperAdapter<Food, FoodDto> {

    public FoodMapper(Class<Food> classParameter, Class<FoodDto> classDtoParameter) {
        super(classParameter, classDtoParameter);
    }

}
