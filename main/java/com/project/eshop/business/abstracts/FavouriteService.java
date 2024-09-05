package com.project.eshop.business.abstracts;

import com.project.eshop.core.utilities.results.DataResult;
import com.project.eshop.core.utilities.results.Result;
import com.project.eshop.entities.concretes.FavouriteProducts;

import java.util.List;

public interface FavouriteService {
    Result addProductToFavourites(Long userId, Long productId);
    DataResult<List<FavouriteProducts>> getFavouriteProductsByUserId(Long userId);
}
