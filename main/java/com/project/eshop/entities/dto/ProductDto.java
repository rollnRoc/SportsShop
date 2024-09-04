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

import lombok.*;

/**
 *
 * @author Emre Yıldırım
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ProductDto {
    private long id;
    private String productName;
    private int stock;
    private long priceId;
    private long categoryId; 
    private long brandId;
//    private Set <Comment> comments;
    private long sellerId;
//    private Set <Cart> carts;
//    private Set<Favourite> favourites;
}
//10.17 restart