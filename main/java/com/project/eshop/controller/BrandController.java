/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.eshop.controller;

import com.project.eshop.business.abstracts.BrandService;
import com.project.eshop.core.utilities.results.DataResult;
import com.project.eshop.entities.concretes.Brand;
import com.project.eshop.entities.dto.BrandDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Emre Yıldırım
 */
@RestController
@RequestMapping("/api/brands")
public class BrandController {

    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping("/add")
    public ResponseEntity<DataResult<BrandDto>> addBrand(@RequestBody BrandDto brandDto) {
        return ResponseEntity.ok(brandService.addBrand(brandDto));
    }

    @PutMapping("/update")
    public ResponseEntity<DataResult<BrandDto>> updateBrand(@RequestBody BrandDto brandDto) {
        return ResponseEntity.ok(brandService.updateBrand(brandDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResult<BrandDto>> getBrandById(@PathVariable long id) {
        return ResponseEntity.ok(brandService.getBrandById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<DataResult<List<BrandDto>>> getAllBrands() {
        return ResponseEntity.ok(brandService.getAllBrands());
    }
}

