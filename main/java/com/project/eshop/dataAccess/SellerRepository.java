/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.project.eshop.dataAccess;

import com.project.eshop.entities.concretes.Brand;
import com.project.eshop.entities.concretes.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 *
 * @author Emre Yıldırım
 */
public interface SellerRepository extends JpaRepository<Seller, Long>{

    @Query("SELECT s FROM Seller s WHERE s.businessName = :businessName")
    Optional<Seller> findByBusinessName(@Param("businessName") String businessName);
}
