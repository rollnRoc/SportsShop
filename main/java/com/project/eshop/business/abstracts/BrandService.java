/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.project.eshop.business.abstracts;

import com.project.eshop.core.utilities.results.DataResult;
import com.project.eshop.entities.concretes.Brand;
import com.project.eshop.entities.dto.BrandDto;
import java.util.List;

/**
 *
 * @author Emre Yıldırım
 */
public interface BrandService {
    DataResult<BrandDto> addBrand(BrandDto brandDto);
    DataResult<BrandDto> updateBrand(BrandDto brandDto);
    DataResult<BrandDto> getBrandById(long id);
    DataResult<List<BrandDto>> getAllBrands();
}

