package com.project.eshop.dataAccess;


import com.project.eshop.entities.concretes.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUser_Id(Long userId);  // Fetch Cart by user ID
}
