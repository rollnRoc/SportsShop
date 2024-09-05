package com.project.eshop.business.abstracts;

import com.project.eshop.core.utilities.results.DataResult;
import com.project.eshop.core.utilities.results.Result;
import com.project.eshop.entities.concretes.Favourite;
import com.project.eshop.entities.dto.FavouriteDto;


import java.util.List;

public interface FavouriteService {
    FavouriteDto addProductToFavourite(Long userId, Long productId);
    FavouriteDto getFavouriteByUser(Long userId);
}
