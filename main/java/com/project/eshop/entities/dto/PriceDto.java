/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.eshop.entities.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.eshop.entities.concretes.Product;
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
public class PriceDto {
    @JsonIgnore
    private long id;
    private double price;
    private double formerPrice;
    @JsonIgnore
    private long productId;
}
