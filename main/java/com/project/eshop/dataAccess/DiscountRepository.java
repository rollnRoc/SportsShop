/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.project.eshop.dataAccess;

import com.project.eshop.entities.concretes.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Emre Yıldırım
 */
public interface DiscountRepository extends JpaRepository<Discount,Long> {
    
}
