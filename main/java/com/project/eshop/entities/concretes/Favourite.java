/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.eshop.entities.concretes;

import java.util.ArrayList;
import java.util.List;
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
@Table(name = "favourites")
public class Favourite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // Assuming there is a User entity

    @ManyToMany
    @JoinTable(
            name = "favourite_products",
            joinColumns = @JoinColumn(name = "favourite_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products;
}
