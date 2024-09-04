/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.project.eshop.business.abstracts;

import com.project.eshop.core.utilities.results.DataResult;
import com.project.eshop.entities.dto.DiscountDto;
import java.util.List;

/**
 *
 * @author Emre Yıldırım
 */
public interface DiscountService {
    DataResult<List<DiscountDto>> getAll();
    DataResult<DiscountDto> getById(long id);
    DataResult<DiscountDto> add(DiscountDto discountDTO);
    DataResult<DiscountDto> update(DiscountDto discountDTO);
    DataResult<String> delete(long id);
}
