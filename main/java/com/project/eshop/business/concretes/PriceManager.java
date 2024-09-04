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
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;

/**
 * Price Manager class
 */
@Service
public class PriceManager implements PriceService {

    private final PriceRepository priceRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

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
        return new SuccessDataResult<>(priceDtos, "All prices retrieved successfully");
    }

    @Override
    public DataResult<PriceDto> getById(long id) {
        Optional<Price> priceOptional = priceRepository.findById(id);
        if (priceOptional.isPresent()) {
            PriceDto priceDto = modelMapper.map(priceOptional.get(), PriceDto.class);
            return new SuccessDataResult<>(priceDto, "Price retrieved successfully");
        } else {
            return new ErrorDataResult<>("Price not found");
        }
    }

    @Override
    public DataResult<PriceDto> add(PriceDto priceDto) {
        Product product = productRepository.findById(priceDto.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        
        Price price = modelMapper.map(priceDto, Price.class);
        price.setProduct(product);
        Price savedPrice = priceRepository.save(price);
        
        PriceDto savedPriceDto = modelMapper.map(savedPrice, PriceDto.class);
        return new SuccessDataResult<>(savedPriceDto, "Price added successfully");
    }

    @Override
    public DataResult<PriceDto> update(long id, PriceDto priceDto) {
        Optional<Price> priceOptional = priceRepository.findById(id);
        if (priceOptional.isPresent()) {
            Price priceToUpdate = priceOptional.get();
            priceToUpdate.setPrice(priceDto.getPrice());
            priceToUpdate.setFormerPrice(priceDto.getFormerPrice());

            Price updatedPrice = priceRepository.save(priceToUpdate);
            PriceDto updatedPriceDto = modelMapper.map(updatedPrice, PriceDto.class);
            return new SuccessDataResult<>(updatedPriceDto, "Price updated successfully");
        } else {
            return new ErrorDataResult<>("Price not found");
        }
    }

    @Override
    public DataResult<String> delete(long id) {
        Optional<Price> priceOptional = priceRepository.findById(id);
        if (priceOptional.isPresent()) {
            priceRepository.deleteById(id);
            return new SuccessDataResult<>("Price deleted successfully");
        } else {
            return new ErrorDataResult<>("Price not found");
        }
    }
}
