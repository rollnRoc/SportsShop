/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.eshop.controller;

import com.project.eshop.business.abstracts.CategoryService;
import com.project.eshop.core.utilities.results.DataResult;
import com.project.eshop.core.utilities.results.Result;
import com.project.eshop.entities.concretes.Category;
import com.project.eshop.entities.dto.CategoryDto;
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
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/getall")
    public DataResult<List<Category>> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("/getbyid")
    public DataResult<Category> getById(@RequestParam long id) {
        return categoryService.getById(id);
    }

    @PostMapping("/add")
    public Result add(@RequestBody CategoryDto categoryDto) {
        return categoryService.add(categoryDto);
    }

    @PutMapping("/update")
    public Result update(@RequestBody CategoryDto categoryDto) {
        return categoryService.update(categoryDto);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam long id) {
        return categoryService.delete(id);
    }
}
