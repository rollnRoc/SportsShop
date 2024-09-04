/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.eshop.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
     
     
     
     
}
