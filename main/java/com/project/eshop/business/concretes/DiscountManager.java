/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.eshop.business.concretes;

import com.project.eshop.business.abstracts.DiscountService;
import com.project.eshop.core.utilities.results.DataResult;
import com.project.eshop.core.utilities.results.ErrorDataResult;
import com.project.eshop.core.utilities.results.SuccessDataResult;
import com.project.eshop.dataAccess.DiscountRepository;
import com.project.eshop.entities.concretes.Discount;
import com.project.eshop.entities.dto.DiscountDto;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Emre Yıldırım
 */
@Service
public class DiscountManager implements DiscountService {

    private final DiscountRepository discountRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public DiscountManager(DiscountRepository discountRepository, ModelMapper modelMapper) {
        this.discountRepository = discountRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DataResult<List<DiscountDto>> getAll() {
        List<Discount> discounts = discountRepository.findAll();
        List<DiscountDto> discountDTOs = discounts.stream()
                .map(discount -> modelMapper.map(discount, DiscountDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(discountDTOs, "Discounts retrieved successfully");
    }

    @Override
    public DataResult<DiscountDto> getById(long id) {
        Discount discount = discountRepository.findById(id).orElse(null);
        if (discount == null) {
            return new ErrorDataResult<>("Discount not found");
        }
        DiscountDto discountDTO = modelMapper.map(discount, DiscountDto.class);
        return new SuccessDataResult<>(discountDTO, "Discount retrieved successfully");
    }

    @Override
    public DataResult<DiscountDto> add(DiscountDto discountDTO) {
        Discount discount = modelMapper.map(discountDTO, Discount.class);
        Discount savedDiscount = discountRepository.save(discount);
        DiscountDto savedDiscountDTO = modelMapper.map(savedDiscount, DiscountDto.class);
        return new SuccessDataResult<>(savedDiscountDTO, "Discount added successfully");
    }

    @Override
    public DataResult<DiscountDto> update(DiscountDto discountDTO) {
        Discount discount = discountRepository.findById(discountDTO.getId()).orElse(null);
        if (discount == null) {
            return new ErrorDataResult<>("Discount not found");
        }
        discount.setDiscountRate(discountDTO.getDiscountRate());
        discount.setDiscountedPrice(discountDTO.getDiscountedPrice());
        Discount updatedDiscount = discountRepository.save(discount);
        DiscountDto updatedDiscountDTO = modelMapper.map(updatedDiscount, DiscountDto.class);
        return new SuccessDataResult<>(updatedDiscountDTO, "Discount updated successfully");
    }

    @Override
    public DataResult<String> delete(long id) {
        discountRepository.deleteById(id);
        return new SuccessDataResult<>("Discount deleted successfully");
    }
}
