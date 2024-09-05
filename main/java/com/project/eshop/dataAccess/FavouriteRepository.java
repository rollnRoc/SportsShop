package com.project.eshop.dataAccess;

import com.project.eshop.entities.concretes.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteRepository extends JpaRepository<Favourite, Long> {
    Favourite findByUserId(Long userId);
}
