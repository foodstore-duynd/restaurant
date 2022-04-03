package com.restarant.backend.web.controller;

import com.restarant.backend.dto.OrderDetailsDto;
import com.restarant.backend.dto.TableOrderDto;
import com.restarant.backend.entity.TableOrder;
import com.restarant.backend.repository.TableOrderRepository;
import com.restarant.backend.service.ITableOrderService;
import com.restarant.backend.service.impl.TableOrderService;
import com.restarant.backend.service.validate.exception.InvalidDataExeception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class TableOrderController {

    private final Logger log = LoggerFactory.getLogger(TableOrderController.class);

    private static final String ENTITY_NAME = "tableOrder";

    private final ITableOrderService tableOrderService;

    public TableOrderController(ITableOrderService tableOrderService) {
        this.tableOrderService = tableOrderService;
    }

    /**
     * {@code POST  /table-orders} : Create a new tableOrder.
     *
     * @param tableOrder the tableOrder to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tableOrder, or with status {@code 400 (Bad Request)} if the tableOrder has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/table-orders")
    public ResponseEntity<?> createTableOrder(@RequestBody TableOrderDto dto)  {
        log.debug("REST request to save TableOrder : {}", dto);
        try {
            TableOrderDto result = tableOrderService.create(dto);
            return ResponseEntity.ok().body(result);
        } catch (InvalidDataExeception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * {@code PUT  /table-orders/:id} : Updates an existing tableOrder.
     *
     * @param id the id of the tableOrder to save.
     * @param tableOrder the tableOrder to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tableOrder,
     * or with status {@code 400 (Bad Request)} if the tableOrder is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tableOrder couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
//    @PutMapping("/table-orders/{id}")
//    public ResponseEntity<?> updateTableOrder(
//        @PathVariable(value = "id", required = false) final Long id,
//        @RequestBody TableOrder tableOrder
//    ) throws URISyntaxException {
//        log.debug("REST request to update TableOrder : {}, {}", id, tableOrder);
//
//        if (!tableOrderRepository.existsById(id)) {
//            return ResponseEntity.badRequest().body("entity not found");
//        }
//
//        TableOrder result = tableOrderRepository.save(tableOrder);
//        return ResponseEntity.ok().body(result);
//    }
//
//    /**
//     * {@code GET  /table-orders} : get all the tableOrders.
//     *
//     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tableOrders in body.
//     */
//    @GetMapping("/table-orders")
//    public List<TableOrder> getAllTableOrders() {
//        log.debug("REST request to get all TableOrders");
//        return tableOrderRepository.findAll();
//    }
//
//    /**
//     * {@code GET  /table-orders/:id} : get the "id" tableOrder.
//     *
//     * @param id the id of the tableOrder to retrieve.
//     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tableOrder, or with status {@code 404 (Not Found)}.
//     */
    @GetMapping("/table-orders/{id}")
    public ResponseEntity<?> getTableOrder(@PathVariable Long id) {
        log.debug("REST request to get TableOrder : {}", id);
        TableOrderDto result = tableOrderService.getById(id);
        if(result == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok(result);
    }
//
//    /**
//     * {@code DELETE  /table-orders/:id} : delete the "id" tableOrder.
//     *
//     * @param id the id of the tableOrder to delete.
//     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
//     */
    @DeleteMapping("/table-orders/{id}")
    public ResponseEntity<?> deleteTableOrder(@PathVariable Long id) {
        log.debug("REST request to delete tableOrder : {}", id);
        try {
            if(tableOrderService.deleteById(id)){
                return ResponseEntity.ok().body("Delete success!");
            }
        } catch (InvalidDataExeception e) {
            log.error("Error when ddelete table", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
