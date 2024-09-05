package com.project.eshop.dataAccess;

import com.project.eshop.entities.concretes.Favourite;
import com.project.eshop.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavouriteRepository extends JpaRepository<Favourite, Long> {
    Favourite findByUser(User userId);
}
