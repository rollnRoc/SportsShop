package com.project.eshop.controller;

import com.project.eshop.business.abstracts.CartProductService;
import com.project.eshop.core.utilities.results.DataResult;
import com.project.eshop.entities.dto.CartProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cartProducts")
public class CartProductController {

    private final CartProductService cartProductService;

    @Autowired
    public CartProductController(CartProductService cartProductService) {
        this.cartProductService = cartProductService;
    }

    @GetMapping("/{cartId}")
    public DataResult<List<CartProductDto>> getCartProducts(@PathVariable long cartId) {
        return cartProductService.getProductsByCartId(cartId);
    }
}
