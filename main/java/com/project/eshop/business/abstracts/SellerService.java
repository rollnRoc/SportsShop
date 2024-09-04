/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.project.eshop.business.abstracts;

import com.project.eshop.core.utilities.results.DataResult;
import com.project.eshop.entities.dto.SellerDto;
import java.util.List;

/**
 *
 * @author Emre Yıldırım
 */
public interface SellerService {
    DataResult<List<SellerDto>> getAll();
    DataResult<SellerDto> getById(long id);
    DataResult<SellerDto> add(SellerDto sellerDto);
    DataResult<SellerDto> update(SellerDto sellerDto);
    DataResult<String> delete(long id);
}
