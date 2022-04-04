package com.restarant.backend.service.impl;

import com.restarant.backend.dto.OrderTotalDto;
import com.restarant.backend.dto.TableOrderDto;
import com.restarant.backend.entity.Customer;
import com.restarant.backend.entity.OrderTotal;
import com.restarant.backend.entity.TableOrder;
import com.restarant.backend.entity.Tables;
import com.restarant.backend.repository.OrderTotalRepository;
import com.restarant.backend.repository.TableOrderRepository;
import com.restarant.backend.repository.TablesRepository;
import com.restarant.backend.service.ITableOrderService;
import com.restarant.backend.service.mapper.IConverterDto;
import com.restarant.backend.service.validate.exception.InvalidDataExeception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.UUID;

@Slf4j
@Service
public class TableOrderService implements ITableOrderService {

    private final TableOrderRepository tableOrderRepository;
    private final IConverterDto<TableOrder, TableOrderDto> mapper;
    private final OrderTotalRepository orderTotalRepository;
    private final TablesRepository tablesRepository;


    public TableOrderService(TableOrderRepository tableOrderRepository,
                             @Qualifier("tableOrderMapper") IConverterDto<TableOrder, TableOrderDto> mapper,
                             OrderTotalRepository orderTotalRepository,
                             TablesRepository tablesRepository) {
        this.tableOrderRepository = tableOrderRepository;
        this.mapper = mapper;
        this.orderTotalRepository = orderTotalRepository;
        this.tablesRepository = tablesRepository;
    }

    @Override
    public TableOrderDto create(TableOrderDto dto) throws InvalidDataExeception {
        if(dto.getTableId() == null){
            throw new InvalidDataExeception("id must not null");
        }
        if(tablesRepository.findByIdAndStatusIs(dto.getTableId(), 0L) == null){
            throw new InvalidDataExeception("Table are using!");
        }

        Customer customer = new Customer();
        customer.setId(1L);

        // create Order Total
        OrderTotal orderTotal = orderTotalRepository.getOrderTotalByCustomerId(1L);

        if(orderTotal == null){
            OrderTotal newOrderTotal = new OrderTotal();
            newOrderTotal.setCustomer(customer);
            newOrderTotal.setAmountTotal(new BigDecimal("0"));
            newOrderTotal.setStatus(0);
            orderTotal = orderTotalRepository.save(newOrderTotal);
        }

        if(orderTotal == null){
            return null;
        }

        dto.setOrderTotalId(orderTotal.getId());
        TableOrder tableOrder = mapper.convertToEntity(dto);
        TableOrder result = tableOrderRepository.save(tableOrder);

        Tables tables = new Tables();
        tables.setId(result.getTables().getId());
        tables.setStatus(1L);
        tablesRepository.save(tables);

        return mapper.convertToDto(result);
    }

    @Override
    public TableOrderDto update(Long id, TableOrderDto dto) throws InvalidDataExeception {
        return null;
    }

    @Override
    public TableOrderDto getById(Long id) {
        return mapper.convertToDto(
                tableOrderRepository.findById(id).orElse(null));
    }

    @Override
    public boolean deleteById(Long id) throws InvalidDataExeception {
        if (!tableOrderRepository.existsById(id)) {
            throw new InvalidDataExeception("The orderDetail[id] not found");
        }

        log.info("Someone delete category id-" + id);
        tableOrderRepository.deleteById(id);
        return true;
    }

    @Override
    public Collection<TableOrderDto> getAll() {
        return null;
    }

    @Override
    public Collection<TableOrderDto> getAll(Pageable pageable) {
        return mapper.convertToListDto(
                tableOrderRepository.findAll(pageable).getContent());
    }
}
