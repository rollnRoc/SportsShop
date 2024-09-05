package com.project.eshop.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavouriteDto {

    private Long id;
    private Long userId;
    private Set<Long> productIds;
}
