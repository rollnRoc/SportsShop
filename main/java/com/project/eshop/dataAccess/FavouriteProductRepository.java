package com.project.eshop.dataAccess;

import com.project.eshop.entities.concretes.FavouriteProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface FavouriteProductRepository extends JpaRepository<FavouriteProducts, Long> {

}
