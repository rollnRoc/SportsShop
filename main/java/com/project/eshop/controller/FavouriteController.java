package com.project.eshop.controller;

import com.project.eshop.business.abstracts.FavouriteService;
import com.project.eshop.core.utilities.results.DataResult;
import com.project.eshop.core.utilities.results.Result;
import com.project.eshop.entities.concretes.Favourite;

import com.project.eshop.entities.dto.FavouriteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favourites")
public class FavouriteController {

    @Autowired
    private FavouriteService favouriteService;

    @PostMapping("/add")
    public FavouriteDto addProductToFavourite(@RequestParam Long userId, @RequestParam Long productId) {
        return favouriteService.addProductToFavourite(userId, productId);
    }

    @GetMapping("/{userId}")
    public FavouriteDto getFavouriteByUser(@PathVariable Long userId) {
        return favouriteService.getFavouriteByUser(userId);
    }
}

