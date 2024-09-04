/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.project.eshop.business.abstracts;

import com.project.eshop.core.utilities.results.DataResult;
import com.project.eshop.entities.concretes.Product;
import com.project.eshop.entities.dto.BrandDto;
import com.project.eshop.entities.dto.PriceDto;
import com.project.eshop.entities.dto.ProductDto;
import java.util.List;

/**
 *
 * @author Emre Yıldırım
 */
public interface ProductService {
    DataResult<ProductDto> addProduct(ProductDto productDto);
    DataResult<ProductDto> updateProduct(ProductDto productDto);
    DataResult<ProductDto> getProductById(long id);
    DataResult<List<ProductDto>> getAllProducts();
}

