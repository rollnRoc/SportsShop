package com.project.eshop.dataAccess;

import com.project.eshop.entities.concretes.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {
    CartProduct findByCart_IdAndProduct_Id(Long cartId, Long productId);  // Fetch CartProduct by cart and product IDs
    List<CartProduct> findByCart_Id(Long cartId);  // Fetch all CartProducts by cart ID
}
