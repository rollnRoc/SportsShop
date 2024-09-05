//package com.project.eshop.controller;
//
//import com.project.eshop.business.abstracts.FavouriteProductService;
//import com.project.eshop.core.utilities.results.DataResult;
//import com.project.eshop.entities.concretes.FavouriteProducts;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/favourite-products")
//public class FavouriteProductController {
//
//    @Autowired
//    private FavouriteProductService favouriteProductService;
//
//    @PostMapping("/add")
//    public ResponseEntity<DataResult<FavouriteProducts>> addProductToFavourite(@RequestParam Long userId, @RequestParam Long productId) {
//        DataResult<FavouriteProducts> result = favouriteProductService.addProductToFavourite(userId, productId);
//        return ResponseEntity.ok(result);
//    }
//
//    @GetMapping("/{userId}")
//    public ResponseEntity<DataResult<List<FavouriteProducts>>> getAllFavouriteProducts(@PathVariable Long userId) {
//        DataResult<List<FavouriteProducts>> result = favouriteProductService.getAllFavouriteProducts(userId);
//        return ResponseEntity.ok(result);
//    }
//}
