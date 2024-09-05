package com.project.eshop.business.abstracts;

import com.project.eshop.core.utilities.results.DataResult;
import com.project.eshop.entities.dto.CartProductDto;

import java.util.List;

public interface CartProductService {
    DataResult<List<CartProductDto>> getProductsByCartId(long cartId);
}

