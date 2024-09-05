/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.eshop.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
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

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<CartProduct> cartProducts;  // Relation with CartProduct entity
}

