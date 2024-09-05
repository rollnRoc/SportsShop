package com.project.eshop.business.abstracts;

import com.project.eshop.core.utilities.results.DataResult;
import com.project.eshop.core.utilities.results.Result;
import com.project.eshop.entities.dto.CartDto;
import com.project.eshop.entities.dto.CartProductDto;
import com.project.eshop.entities.dto.ProductDto;

import java.util.List;

public interface CartService {
    DataResult<CartDto> getCartByUserId(Long userId);
    Result addProductToCart(Long cartId, CartProductDto cartProductDto);
    Result removeProductFromCart(Long cartId, Long productId);
    DataResult<List<CartProductDto>> getAllProductsInCart(Long cartId);
}
