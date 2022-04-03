package com.restarant.backend.service.impl;

import com.restarant.backend.dto.CategoryDto;
import com.restarant.backend.dto.TableDto;
import com.restarant.backend.entity.Category;
import com.restarant.backend.entity.Tables;
import com.restarant.backend.repository.TablesRepository;
import com.restarant.backend.service.ITableService;
import com.restarant.backend.service.mapper.impl.TableMapper;
import com.restarant.backend.service.validate.exception.InvalidDataExeception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Slf4j
@Service
public class TableService implements ITableService {

    private final TableMapper tableMapper;
    private final TablesRepository tablesRepository;

    public TableService(TableMapper tableMapper, TablesRepository tablesRepository) {
        this.tableMapper = tableMapper;
        this.tablesRepository = tablesRepository;
    }

    @Override
    public TableDto create(TableDto dto) {
        log.info("Someone create table " + dto);

        Tables result = tablesRepository.save(
                tableMapper.convertToEntity(dto)
        );

        return tableMapper.convertToDto(result);
    }

    @Override
    public TableDto update(Long id, TableDto dto) {
        if (tablesRepository.existsById(id)) {

            log.info("Someone edit table id-" + id);
            Tables tables = tableMapper.convertToEntity(dto);
            tables.setId(id);
            return tableMapper.convertToDto(
                    tablesRepository.save(tables)
            );
        }
        return null;
    }

    @Override
    public TableDto getById(Long id) {
        return tableMapper.convertToDto(
                tablesRepository.findById(id).orElse(null)
        );
    }

    @Override
    public boolean deleteById(Long id) throws InvalidDataExeception {
        if (!tablesRepository.existsById(id)) {
            throw new InvalidDataExeception("The foodMedia[id] not found");
        }
        if(tablesRepository.findByIdAndStatusIs(id, 0) == null){
            throw new InvalidDataExeception("Table are using!");
        }
        log.info("Someone delete table id-" + id);
        tablesRepository.deleteById(id);
        return true;
    }

    @Override
    public Collection<TableDto> getAll() {
        return tableMapper.convertToListDto(
                tablesRepository.findAll()
        );
    }

    @Override
    public Collection<TableDto> getAll(Pageable pageable) {
        return tableMapper.convertToListDto(
                tablesRepository.findAll(pageable).getContent()
        );
    }
}
