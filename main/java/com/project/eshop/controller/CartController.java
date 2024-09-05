package com.project.eshop.controller;

import com.project.eshop.business.abstracts.CartService;
import com.project.eshop.core.utilities.results.DataResult;
import com.project.eshop.core.utilities.results.Result;
import com.project.eshop.entities.dto.CartDto;
import com.project.eshop.entities.dto.CartProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/getByUserId")
    public DataResult<CartDto> getCartByUserId(@RequestParam Long userId) {
        return cartService.getCartByUserId(userId);
    }

    @PostMapping("/addProduct")
    public Result addProductToCart(@RequestParam Long cartId, @RequestBody CartProductDto cartProductDto) {
        return cartService.addProductToCart(cartId, cartProductDto);
    }

    @DeleteMapping("/removeProduct")
    public Result removeProductFromCart(@RequestParam Long cartId, @RequestParam Long productId) {
        return cartService.removeProductFromCart(cartId, productId);
    }

    @GetMapping("/getAllProducts")
    public DataResult<List<CartProductDto>> getAllProductsInCart(@RequestParam Long cartId) {
        return cartService.getAllProductsInCart(cartId);
    }
}
