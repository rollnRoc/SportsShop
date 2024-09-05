/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.eshop.entities.concretes;

import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Emre Yıldırım
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "favourite_products")
public class FavouriteProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "favourite_id")
    private Favourite favourite;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "favourite_date")
    private LocalDateTime favouriteDate;
}
