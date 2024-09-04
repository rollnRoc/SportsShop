/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.project.eshop.business.abstracts;

import com.project.eshop.core.utilities.results.DataResult;
import com.project.eshop.core.utilities.results.Result;
import com.project.eshop.entities.concretes.Category;
import com.project.eshop.entities.dto.CategoryDto;
import java.util.List;

/**
 *
 * @author Emre Yıldırım
 */
public interface CategoryService {
    DataResult<List<Category>> getAll();
    DataResult<Category> getById(long id);
    Result add(CategoryDto categoryDto);
    Result update(CategoryDto categoryDto);
    Result delete(long id);
}
