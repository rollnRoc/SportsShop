package com.project.eshop.controller;

import com.project.eshop.business.abstracts.FavouriteService;
import com.project.eshop.core.utilities.results.DataResult;
import com.project.eshop.core.utilities.results.Result;
import com.project.eshop.entities.concretes.FavouriteProducts;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favourites")
public class FavouriteController {

    private final FavouriteService favouriteService;

    public FavouriteController(FavouriteService favouriteService) {
        this.favouriteService = favouriteService;
    }

    // Kullanıcının favori listesine ürün eklemek için POST isteği
    @PostMapping("/add")
    public Result addProductToFavourites(@RequestParam Long userId, @RequestParam Long productId) {
        return favouriteService.addProductToFavourites(userId, productId);
    }

    // Kullanıcının favori listesindeki ürünleri getir
    @GetMapping("/get")
    public DataResult<List<FavouriteProducts>> getFavouriteProducts(@RequestParam Long userId) {
        return favouriteService.getFavouriteProductsByUserId(userId);
    }
}
