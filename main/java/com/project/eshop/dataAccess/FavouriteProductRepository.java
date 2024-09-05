package com.project.eshop.dataAccess;

import com.project.eshop.entities.concretes.FavouriteProducts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavouriteProductRepository extends JpaRepository<FavouriteProducts, Long> {
    // Belirli bir favouritelistesine ait ürünleri getir
    List<FavouriteProducts> findByFavouriteId(Long favouriteId);
}
