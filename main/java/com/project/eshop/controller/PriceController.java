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

/**
 * Price Controller class
 */
@RestController
@RequestMapping("/api/prices")
public class PriceController {

    private PriceService priceService;

    @Autowired
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/getall")
    public DataResult<List<PriceDto>> getAll() {
        return this.priceService.getAll();
    }

    @PostMapping("/add")
    public DataResult<PriceDto> addPrice(@RequestBody PriceDto priceDto) {
        return this.priceService.addPrice(priceDto);
    }
}
//@PostMapping("/add")
//    @ResponseStatus(HttpStatus.CREATED)
//    public DataResult<EmployerDto> addEmployer(@RequestBody EmployerDto employerDto){
//        return employerService.addEmployer(employerDto);
//    }
