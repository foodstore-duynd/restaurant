package com.restarant.backend.web.controller;

import com.restarant.backend.dto.FoodDto;
import com.restarant.backend.dto.FoodMediaDto;
import com.restarant.backend.repository.FoodMediaRepository;
import com.restarant.backend.service.IFoodMediaService;
import com.restarant.backend.service.impl.FoodMediaService;
import com.restarant.backend.service.validate.exception.InvalidDataExeception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;


@Slf4j
@RestController
@RequestMapping("/api")
public class FoodMediaController {

    private final IFoodMediaService foodMediaService;

    public FoodMediaController(IFoodMediaService foodMediaService) {
        this.foodMediaService = foodMediaService;
    }

    @PostMapping("/food-medias")
    public ResponseEntity<?> saveFoodMedia(@RequestBody FoodMediaDto dto){
        log.info("REST request to save foodMedia : {}", dto);
        try {
            FoodMediaDto result = foodMediaService.create(dto);
            return ResponseEntity.ok().body(result);
        } catch (InvalidDataExeception e) {
            log.error("Error when create Food", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/food-medias")
    public ResponseEntity<?> updateFoodMedia(@PathVariable(value = "id", required = false) final Long id, @RequestBody FoodMediaDto dto)
            throws URISyntaxException {
        log.debug("REST request to update Food : {}, {}", id, dto);
        try {
            FoodMediaDto result = foodMediaService.update(id, dto);
            if(result == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("food Id not exists!");
            }
            return ResponseEntity.ok().body(result);
        } catch (InvalidDataExeception e) {
            log.error("Error when update Food", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/food-medias/{id}")
    public ResponseEntity<?> getByFoodId(@PathVariable Long id) {
        return ResponseEntity.ok(foodMediaService.getById(id));
    }

    @DeleteMapping("/food-medias/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            foodMediaService.deleteById(id);
            return ResponseEntity.ok("Delete success");
        } catch (InvalidDataExeception e) {
            log.error("Error when delete foodMedia", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
