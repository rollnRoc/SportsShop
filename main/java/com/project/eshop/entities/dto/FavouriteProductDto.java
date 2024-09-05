package com.project.eshop.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavouriteProductDto {
    private Long id;
    private Long productId;
    private Long favouriteId;
    private LocalDateTime favouriteDate;
}
