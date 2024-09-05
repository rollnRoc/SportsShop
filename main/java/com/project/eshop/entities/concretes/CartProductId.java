package com.project.eshop.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartProductId implements Serializable {
    private long cart;
    private long product;
}
