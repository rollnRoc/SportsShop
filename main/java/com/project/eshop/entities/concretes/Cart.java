/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.eshop.entities.concretes;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
@Table(name="carts")
public class    Cart {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    
    
    @OneToOne(mappedBy="cart", cascade = CascadeType.ALL)
    private Purchase purchase;
    
    @OneToOne()
    @JoinColumn(name="user_id",nullable = false)
    private User user;
    
    @ManyToMany
    @JoinTable(
            name = "cart_products",
            joinColumns = @JoinColumn(name="cart_id"),
            inverseJoinColumns = @JoinColumn(name="product_id")
    )
    private Set<Product> products;
}
