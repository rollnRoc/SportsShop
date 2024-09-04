/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.eshop.controller;

import com.project.eshop.business.abstracts.DiscountService;
import com.project.eshop.core.utilities.results.DataResult;
import com.project.eshop.core.utilities.results.Result;
import com.project.eshop.entities.concretes.Category;
import com.project.eshop.entities.concretes.Discount;
import com.project.eshop.entities.dto.CategoryDto;
import com.project.eshop.entities.dto.DiscountDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Emre Yıldırım
 */
@RestController
@RequestMapping("/api/discounts")
public class DiscountController {

    private final DiscountService discountService;

    @Autowired
    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping("/getall")
    public DataResult<List<DiscountDto>> getAll() {
        return discountService.getAll();
    }

    @GetMapping("/getbyid")
    public DataResult<DiscountDto> getById(@RequestParam long id) {
        return discountService.getById(id);
    }

    @PostMapping("/add")
    public Result add(@RequestBody DiscountDto discountDto) {
        return discountService.add(discountDto);
    }

    @PutMapping("/update")
    public Result update(@RequestBody DiscountDto discountDto) {
        return discountService.update(discountDto);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam long id) {
        return discountService.delete(id);
    }
}
