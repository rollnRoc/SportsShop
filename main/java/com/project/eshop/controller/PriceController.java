/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.eshop.controller;

/**
 *
 * @author Emre Yıldırım
 */
import com.project.eshop.business.abstracts.PriceService;
import com.project.eshop.core.utilities.results.DataResult;
import com.project.eshop.entities.dto.PriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.http.ResponseEntity;

/**
 * Price Controller class
 */
@RestController
@RequestMapping("/api/prices")
public class PriceController {

    private final PriceService priceService;

    @Autowired
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<PriceDto>>> getAll() {
        DataResult<List<PriceDto>> result = priceService.getAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<DataResult<PriceDto>> getById(@PathVariable long id) {
        DataResult<PriceDto> result = priceService.getById(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/add")
    public ResponseEntity<DataResult<PriceDto>> add(@RequestBody PriceDto priceDto) {
        DataResult<PriceDto> result = priceService.add(priceDto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DataResult<PriceDto>> update(@PathVariable long id, @RequestBody PriceDto priceDto) {
        DataResult<PriceDto> result = priceService.update(id, priceDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DataResult<String>> delete(@PathVariable long id) {
        DataResult<String> result = priceService.delete(id);
        return ResponseEntity.ok(result);
    }
}

