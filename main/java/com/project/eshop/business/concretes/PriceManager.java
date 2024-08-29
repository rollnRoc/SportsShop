/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.eshop.business.concretes;

import com.project.eshop.business.abstracts.PriceService;
import com.project.eshop.core.utilities.results.DataResult;
import com.project.eshop.core.utilities.results.ErrorDataResult;
import com.project.eshop.core.utilities.results.SuccessDataResult;
import com.project.eshop.dataAccess.PriceRepository;
import com.project.eshop.dataAccess.ProductRepository;
import com.project.eshop.entities.concretes.Price;
import com.project.eshop.entities.concretes.Product;
import com.project.eshop.entities.dto.PriceDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Price Manager class
 */
@Service
public class PriceManager implements PriceService {

    private PriceRepository priceRepository;
    private ProductRepository productRepository;
    private ModelMapper modelMapper;

    @Autowired
    public PriceManager(PriceRepository priceRepository, ProductRepository productRepository, ModelMapper modelMapper) {
        this.priceRepository = priceRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DataResult<List<PriceDto>> getAll() {
        List<Price> prices = priceRepository.findAll();
        List<PriceDto> priceDtos = prices.stream()
                .map(price -> modelMapper.map(price, PriceDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(priceDtos, "Prices retrieved successfully");
    }

    @Override
    public DataResult<PriceDto> addPrice(PriceDto priceDto) {
        // Directly fetch the Product
        Product product = productRepository.findById(priceDto.getProductId()).orElse(null);

        // Check if the Product is null
        if (product == null) {
            // Return an error result if the product does not exist
            return new ErrorDataResult<>("Product with ID " + priceDto.getProductId() + " does not exist");
        }

        // Map PriceDto to Price entity and set the found product
        Price price = modelMapper.map(priceDto, Price.class);
        price.setProduct(product);  // Link the Product entity to the Price

        // Save the Price entity
        Price savedPrice = priceRepository.save(price);

        // Map back to PriceDto
        PriceDto savedPriceDto = modelMapper.map(savedPrice, PriceDto.class);
        return new SuccessDataResult<>(savedPriceDto, "Price saved successfully");
    }
}
