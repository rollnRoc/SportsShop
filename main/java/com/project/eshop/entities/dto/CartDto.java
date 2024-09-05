package com.project.eshop.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
    private long id;
    private long userId;
    private List<CartProductDto> products;
}

