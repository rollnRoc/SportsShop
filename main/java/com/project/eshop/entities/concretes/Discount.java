/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.eshop.entities.concretes;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Emre Yıldırım
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="discounts")
public class Discount {
    
     @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
     @Column(name="id")
    private long id;
     @Column(name="discount_rate")
    private double discountRate;
     @Column(name="discounted_price")
    private double discountedPrice;


    // Relationship with Product
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    // Relationship with Price
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "price_id")
    private Price price;
}
