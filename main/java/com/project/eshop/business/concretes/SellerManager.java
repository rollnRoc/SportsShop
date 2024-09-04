/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.eshop.business.concretes;

import com.project.eshop.business.abstracts.SellerService;
import com.project.eshop.core.utilities.results.DataResult;
import com.project.eshop.core.utilities.results.ErrorDataResult;
import com.project.eshop.core.utilities.results.SuccessDataResult;
import com.project.eshop.dataAccess.SellerRepository;
import com.project.eshop.entities.concretes.Seller;
import com.project.eshop.entities.dto.SellerDto;
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
public class SellerManager implements SellerService {

    private final SellerRepository sellerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SellerManager(SellerRepository sellerRepository, ModelMapper modelMapper) {
        this.sellerRepository = sellerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DataResult<List<SellerDto>> getAll() {
        List<Seller> sellers = sellerRepository.findAll();
        List<SellerDto> sellerDTOs = sellers.stream()
                .map(seller -> modelMapper.map(seller, SellerDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(sellerDTOs, "Sellers retrieved successfully");
    }

    @Override
    public DataResult<SellerDto> getById(long id) {
        Seller seller = sellerRepository.findById(id).orElse(null);
        if (seller == null) {
            return new ErrorDataResult<>("Seller not found");
        }
        SellerDto sellerDTO = modelMapper.map(seller, SellerDto.class);
        return new SuccessDataResult<>(sellerDTO, "Seller retrieved successfully");
    }

    @Override
    public DataResult<SellerDto> add(SellerDto sellerDTO) {
        Seller seller = modelMapper.map(sellerDTO, Seller.class);
        Seller savedSeller = sellerRepository.save(seller);
        SellerDto savedSellerDTO = modelMapper.map(savedSeller, SellerDto.class);
        return new SuccessDataResult<>(savedSellerDTO, "Seller added successfully");
    }

    @Override
    public DataResult<SellerDto> update(SellerDto sellerDTO) {
        Seller seller = sellerRepository.findById(sellerDTO.getId()).orElse(null);
//        public DataResult<DiscountDto> update(DiscountDto discountDTO) {
//        Discount discount = discountRepository.findById(discountDTO.getId()).orElse(null);
        if (seller == null) {
            return new ErrorDataResult<>("Seller not found");
        }
        seller.setBusinessName(sellerDTO.getBusinessName());
        seller.setRating(sellerDTO.getRating());
        Seller updatedSeller = sellerRepository.save(seller);
        SellerDto updatedSellerDTO = modelMapper.map(updatedSeller, SellerDto.class);
        return new SuccessDataResult<>(updatedSellerDTO, "Seller updated successfully");
    }

    @Override
    public DataResult<String> delete(long id) {
        sellerRepository.deleteById(id);
        return new SuccessDataResult<>("Seller deleted successfully");
    }
}
