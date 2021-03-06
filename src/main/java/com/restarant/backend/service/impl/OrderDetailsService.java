package com.restarant.backend.service.impl;

import com.restarant.backend.dto.OrderDetailsDto;
import com.restarant.backend.entity.OrderDetails;
import com.restarant.backend.repository.FoodDetallsRepository;
import com.restarant.backend.repository.OrderDetailsRepository;
import com.restarant.backend.repository.TableOrderRepository;
import com.restarant.backend.repository.TablesRepository;
import com.restarant.backend.service.IOrderDetailsService;
import com.restarant.backend.service.mapper.IConverterDto;
import com.restarant.backend.service.validate.OrderDetailsValidator;
import com.restarant.backend.service.validate.exception.InvalidDataExeception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
public class OrderDetailsService implements IOrderDetailsService {

    private final OrderDetailsRepository orderDetailsRepository;
    private final IConverterDto<OrderDetails, OrderDetailsDto> mapper;
    private final TablesRepository tablesRepository;
    private final TableOrderRepository tableOrderRepository;
    private final FoodDetallsRepository foodDetallsRepository;
    private final OrderDetailsValidator orderDetailsValidator = new OrderDetailsValidator();


    public OrderDetailsService(OrderDetailsRepository orderDetailsRepository,
                               TablesRepository tablesRepository,
                               FoodDetallsRepository foodDetallsRepository,
                               TableOrderRepository tableOrderRepository,
                               @Qualifier("orderDetailsMapper") IConverterDto<OrderDetails, OrderDetailsDto> mapper) {
        this.orderDetailsRepository = orderDetailsRepository;
        this.mapper = mapper;
        this.tablesRepository = tablesRepository;
        this.foodDetallsRepository = foodDetallsRepository;
        this.tableOrderRepository = tableOrderRepository;
    }

    @Override
    public OrderDetailsDto create(OrderDetailsDto dto) throws InvalidDataExeception {
        orderDetailsValidator.validate(dto);
        if (!foodDetallsRepository.existsById(dto.getFoodDetalls().getId())) {
            throw new InvalidDataExeception("The FoodDetailsId not found");
        }
        if(!tableOrderRepository.existsById(dto.getTableOrderId())){
            throw new InvalidDataExeception("The TableOrderId not found");
        }

        log.info("Someone create table " + dto);
        OrderDetails result = orderDetailsRepository.save(
                mapper.convertToEntity(dto)
        );
        return mapper.convertToDto(result);
    }

    @Override
    public OrderDetailsDto update(Long id, OrderDetailsDto dto) throws InvalidDataExeception {
        orderDetailsValidator.validate(dto);
        if (!orderDetailsRepository.existsById(id)) {
            throw new InvalidDataExeception("The orderDetail[id] not found");
        }
        if (!foodDetallsRepository.existsById(dto.getFoodDetalls().getId())) {
            throw new InvalidDataExeception("The FoodDetallId not found");
        }

        log.info("Someone edit category id-" + id);
        OrderDetails entity = mapper.convertToEntity(dto);
        entity.setId(id);
        return mapper.convertToDto(
                orderDetailsRepository.save(entity));

    }

    @Override
    public OrderDetailsDto getById(Long id) {
        return mapper.convertToDto(
                orderDetailsRepository.findById(id).orElse(null));
    }

    @Override
    public boolean deleteById(Long id) throws InvalidDataExeception {
        if (!orderDetailsRepository.existsById(id)) {
            throw new InvalidDataExeception("The orderDetail[id] not found");
        }

        log.info("Someone delete category id-" + id);
        orderDetailsRepository.deleteById(id);
        return true;
    }

    @Override
    public Collection<OrderDetailsDto> getAll() {
        return null;
    }

    @Override
    public Collection<OrderDetailsDto> getAll(Pageable pageable) {
        return mapper.convertToListDto(
                orderDetailsRepository.findAll(pageable).getContent()
        );
    }
}
