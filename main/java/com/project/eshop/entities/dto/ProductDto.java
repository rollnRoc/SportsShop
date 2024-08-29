/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.eshop.entities.dto;

import com.project.eshop.entities.concretes.Brand;
import com.project.eshop.entities.concretes.Cart;
import com.project.eshop.entities.concretes.Category;
import com.project.eshop.entities.concretes.Comment;
import com.project.eshop.entities.concretes.Favourite;
import com.project.eshop.entities.concretes.Price;
import com.project.eshop.entities.concretes.Seller;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Emre Yıldırım
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {
    private long id;
    private String productName;
    private int stock;
    private double price;
    private String categoryName;
    private String brandName;    
//    private Set <Comment> comments;
    private String businessName;
//    private Set <Cart> carts;
//    private Set<Favourite> favourites;
}
