/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.project.eshop.business.abstracts;

import com.project.eshop.core.utilities.results.DataResult;
import com.project.eshop.entities.dto.PriceDto;
import java.util.List;

/**
 * Price Service interface
 */
public interface PriceService {
    DataResult<List<PriceDto>> getAll();
    DataResult<PriceDto> getById(long id);
    DataResult<PriceDto> add(PriceDto priceDTO);
    DataResult<PriceDto> update(long id, PriceDto priceDTO);
    DataResult<String> delete(long id);
}
