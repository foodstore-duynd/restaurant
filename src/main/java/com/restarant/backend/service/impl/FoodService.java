package com.restarant.backend.service.impl;

import com.restarant.backend.dto.FoodDto;
import com.restarant.backend.entity.Food;
import com.restarant.backend.repository.CategoryRepository;
import com.restarant.backend.repository.FoodRepository;
import com.restarant.backend.service.IFoodService;
import com.restarant.backend.service.mapper.IConverterDto;
import com.restarant.backend.service.validate.exception.InvalidDataExeception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service("foodService")
public class FoodService implements IFoodService {
    private final FoodRepository foodRepository;
    private final IConverterDto<Food, FoodDto> mapper;
    private final CategoryRepository categoryRepository;


    public FoodService(FoodRepository foodRepository,
                       IConverterDto<Food, FoodDto> mapper,
                       CategoryRepository categoryRepository) {
        this.foodRepository = foodRepository;
        this.mapper = mapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public FoodDto create(FoodDto foodDto) throws InvalidDataExeception {
        if(foodDto == null){
            throw new InvalidDataExeception("food must not be null");
        }

        if(foodDto.getCategory() != null && foodDto.getCategory().getId() != null){
            if(!categoryRepository.existsById(foodDto.getCategory().getId())){
                throw new InvalidDataExeception("category-id not found");
            }
        }

        Food food = mapper.convertToEntity(foodDto);
        food.setCreate(LocalDate.now());

        log.info(String.format("Someone create Food[id-%d]", foodDto.getId()));

        Food result = foodRepository.save(food);
        return mapper.convertToDto(result);
    }

    @Override
    public Set<FoodDto> getAll(Pageable pageable) {
        List<Food> foods = foodRepository.findAll(pageable).getContent();
        return new HashSet<>(mapper.convertToListDto(foods));
    }

    @Override
    public FoodDto getById(Long id) {
        Food food = foodRepository.findById(id).orElse(null);
        return mapper.convertToDto(food);
    }

    @Override
    public boolean deleteById(Long id) throws InvalidDataExeception {
        if (!foodRepository.existsById(id)) {
            throw new InvalidDataExeception("The food[id] not found");
        }

        log.info(String.format("Someone detele Food[id-%d]", id));

        foodRepository.deleteById(id);
        return true;
    }

    @Override
    public Collection<FoodDto> getAll() {
        return getAll(null);
    }

    @Override
    public FoodDto update(Long id, FoodDto foodDto) throws InvalidDataExeception {
        if(foodDto == null){
            throw new InvalidDataExeception("food must not be null");
        }

        if(foodDto.getCategory() != null && foodDto.getCategory().getId() != null){
            if(!categoryRepository.existsById(foodDto.getCategory().getId())){
                throw new InvalidDataExeception("category-id not found");
            }
        }

        if (foodRepository.existsById(id)) {
            foodDto.setId(id);

            log.info(String.format("Someone update Food[id-%d]", foodDto.getId()));
            Food food = foodRepository.save(
                    mapper.convertToEntity(foodDto));
            return mapper.convertToDto(food);
        }
        return null;
    }


}
