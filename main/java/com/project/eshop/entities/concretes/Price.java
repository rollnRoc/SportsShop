/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.eshop.entities.concretes;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *
 * @author Emre Yıldırım
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="prices")
public class Price {
    
     @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
     @Column(name="id")
    private long id;
     @Column(name="price")
    private double price;
     @Column(name="former_price")
    private double formerPrice;
     
     
    @OneToOne
    @JoinColumn(name="product_id", referencedColumnName = "id")
    private Product product;

    @OneToMany(mappedBy = "price", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Discount> discounts;
     
     
}
