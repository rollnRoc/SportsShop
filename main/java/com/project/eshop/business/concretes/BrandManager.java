/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.eshop.business.concretes;

import com.project.eshop.business.abstracts.BrandService;
import com.project.eshop.core.utilities.results.DataResult;
import com.project.eshop.core.utilities.results.ErrorDataResult;
import com.project.eshop.core.utilities.results.SuccessDataResult;
import com.project.eshop.dataAccess.BrandRepository;
import com.project.eshop.dataAccess.ProductRepository;
import com.project.eshop.entities.concretes.Brand;
import com.project.eshop.entities.dto.BrandDto;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Emre Yıldırım
 */
@Service
public class BrandManager implements BrandService {

    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BrandManager(BrandRepository brandRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DataResult<BrandDto> addBrand(BrandDto brandDto) {
        try {
            Brand brand = modelMapper.map(brandDto, Brand.class);
            Brand savedBrand = brandRepository.save(brand);
            BrandDto savedBrandDto = modelMapper.map(savedBrand, BrandDto.class);
            return new SuccessDataResult<>(savedBrandDto, "Brand added successfully");
        } catch (Exception e) {
            return new ErrorDataResult<>("Brand could not be added");
        }
    }

    @Override
    public DataResult<BrandDto> updateBrand(BrandDto brandDto) {
        try {
            Brand brand = brandRepository.findById(brandDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Brand not found"));

            brand.setBrandName(brandDto.getBrandName());
            Brand updatedBrand = brandRepository.save(brand);
            BrandDto updatedBrandDto = modelMapper.map(updatedBrand, BrandDto.class);

            return new SuccessDataResult<>(updatedBrandDto, "Brand updated successfully");
        } catch (EntityNotFoundException e) {
            return new ErrorDataResult<>("Brand not found");
        } catch (Exception e) {
            return new ErrorDataResult<>("Brand could not be updated");
        }
    }

    @Override
    public DataResult<BrandDto> getBrandById(long id) {
        try {
            Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Brand not found"));

            BrandDto brandDto = modelMapper.map(brand, BrandDto.class);
            return new SuccessDataResult<>(brandDto, "Brand found successfully");
        } catch (EntityNotFoundException e) {
            return new ErrorDataResult<>("Brand not found");
        } catch (Exception e) {
            return new ErrorDataResult<>("Error occurred while retrieving the brand");
        }
    }

    @Override
    public DataResult<List<BrandDto>> getAllBrands() {
        try {
            List<Brand> brands = brandRepository.findAll();
            List<BrandDto> brandDtos = brands.stream()
                .map(brand -> modelMapper.map(brand, BrandDto.class))
                .collect(Collectors.toList());

            return new SuccessDataResult<>(brandDtos, "All brands retrieved successfully");
        } catch (Exception e) {
            return new ErrorDataResult<>("Error occurred while retrieving brands");
        }
    }
}
