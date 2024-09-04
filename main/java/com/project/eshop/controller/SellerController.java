/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.eshop.controller;

import com.project.eshop.business.abstracts.SellerService;
import com.project.eshop.core.utilities.results.DataResult;
import com.project.eshop.core.utilities.results.Result;
import com.project.eshop.entities.dto.SellerDto;
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
@RequestMapping("/api/sellers")
public class SellerController {

    private final SellerService sellerService;

    @Autowired
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping("/getall")
    public DataResult<List<SellerDto>> getAll() {
        return sellerService.getAll();
    }

    @GetMapping("/getbyid")
    public DataResult<SellerDto> getById(@RequestParam long id) {
        return sellerService.getById(id);
    }

    @PostMapping("/add")
    public Result add(@RequestBody SellerDto sellerDto) {
        return sellerService.add(sellerDto);
    }

    @PutMapping("/update")
    public Result update(@RequestBody SellerDto sellerDto) {
        return sellerService.update(sellerDto);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam long id) {
        return sellerService.delete(id);
    }
}
