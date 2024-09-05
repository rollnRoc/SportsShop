package com.project.eshop.business.concretes;

import com.project.eshop.business.abstracts.FavouriteService;
import com.project.eshop.core.utilities.mapping.ModelMapperService;
import com.project.eshop.core.utilities.results.*;

import com.project.eshop.dataAccess.FavouriteRepository;
import com.project.eshop.dataAccess.ProductRepository;
import com.project.eshop.dataAccess.UserRepository;
import com.project.eshop.entities.concretes.Favourite;

import com.project.eshop.entities.concretes.Product;
import com.project.eshop.entities.concretes.User;
import com.project.eshop.entities.dto.FavouriteDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import org.springframework.context.annotation.Lazy;

@Service
public class FavouriteManager implements FavouriteService {

    
    private FavouriteRepository favouriteRepository;

    
    private ProductRepository productRepository;

    
    private UserRepository userRepository; // Ensure this is injected

    
    private ModelMapper modelMapper;
    
       
    @Autowired
    public FavouriteManager(@Lazy FavouriteRepository favouriteRepository,@Lazy ProductRepository productRepository,@Lazy UserRepository userRepository,@Lazy ModelMapper modelMapper ){
        this.favouriteRepository = favouriteRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public FavouriteDto addProductToFavourite(Long userId, Long productId) {
        // Ensure the product exists
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Ensure the user exists
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Find existing favourite for the user or create a new one
        Favourite favourite = favouriteRepository.findByUser(user);
        if (favourite == null) {
            favourite = new Favourite();
            favourite.setUser(user);
            favourite.setProducts(new HashSet<>()); // Initialize products set
        }

        // Add product to favourite if not already present
        favourite.getProducts().add(product);

        // Save the favourite
        favourite = favouriteRepository.save(favourite);

        // Map to DTO and return
        return modelMapper.map(favourite, FavouriteDto.class);
    }

    @Override
    public FavouriteDto getFavouriteByUser(Long userId) {
        // Ensure the user exists
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Find the favourite by user
        Favourite favourite = favouriteRepository.findByUser(user);
        if (favourite == null) {
            throw new EntityNotFoundException("No favourites found for user");
        }

        // Map to DTO and return
        return modelMapper.map(favourite, FavouriteDto.class);
    }
}

