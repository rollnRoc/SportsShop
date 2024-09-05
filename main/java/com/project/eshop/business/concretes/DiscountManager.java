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
import com.project.eshop.dataAccess.PriceRepository;
import com.project.eshop.dataAccess.ProductRepository;
import com.project.eshop.entities.concretes.Discount;
import com.project.eshop.entities.concretes.Price;
import com.project.eshop.entities.concretes.Product;
import com.project.eshop.entities.dto.DiscountDto;
import java.util.List;
import java.util.Optional;
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
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public DiscountManager(DiscountRepository discountRepository, ProductRepository productRepository, ModelMapper modelMapper) {
        this.discountRepository = discountRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DataResult<List<DiscountDto>> getAll() {
        List<Discount> discounts = discountRepository.findAll();
        List<DiscountDto> discountDtos = discounts.stream()
                .map(discount -> modelMapper.map(discount, DiscountDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(discountDtos, "Discounts retrieved successfully");
    }

    @Override
    public DataResult<DiscountDto> getById(long id) {
        Optional<Discount> discountOptional = discountRepository.findById(id);
        if (discountOptional.isEmpty()) {
            return new ErrorDataResult<>("Discount not found");
        }
        DiscountDto discountDto = modelMapper.map(discountOptional.get(), DiscountDto.class);
        return new SuccessDataResult<>(discountDto, "Discount retrieved successfully");
    }

    @Override
    public DataResult<DiscountDto> add(DiscountDto discountDto) {
        Discount discount = modelMapper.map(discountDto, Discount.class);

        // İndirimi belirli bir ürün ve fiyatla ilişkilendirme
        Optional<Product> productOptional = productRepository.findById(discountDto.getProductId());
        if (productOptional.isEmpty()) {
            return new ErrorDataResult<>("Product not found for discount");
        }
        Product product = productOptional.get();

        discount.setProduct(product);

        // İndirimli fiyatı hesaplama
        Price price = product.getPrice();
        double discountedPrice = price.getPrice() * (1 - discount.getDiscountRate() / 100);
        discount.setDiscountedPrice(discountedPrice);

        Discount savedDiscount = discountRepository.save(discount);
        DiscountDto savedDiscountDto = modelMapper.map(savedDiscount, DiscountDto.class);
        return new SuccessDataResult<>(savedDiscountDto, "Discount added successfully");
    }

    @Override
    public DataResult<DiscountDto> update(DiscountDto discountDto) {
        Optional<Discount> discountOptional = discountRepository.findById(discountDto.getId());
        if (discountOptional.isEmpty()) {
            return new ErrorDataResult<>("Discount not found");
        }

        Discount discount = discountOptional.get();

        // İlgili ürünü ve indirimi güncelle
        Optional<Product> productOptional = productRepository.findById(discountDto.getProductId());
        if (productOptional.isEmpty()) {
            return new ErrorDataResult<>("Product not found for discount");
        }
        Product product = productOptional.get();

        discount.setDiscountRate(discountDto.getDiscountRate());
        discount.setProduct(product);

        // İndirimli fiyatı yeniden hesapla
        Price price = product.getPrice();
        double discountedPrice = price.getPrice() * (1 - discount.getDiscountRate() / 100);
        discount.setDiscountedPrice(discountedPrice);

        Discount updatedDiscount = discountRepository.save(discount);
        DiscountDto updatedDiscountDto = modelMapper.map(updatedDiscount, DiscountDto.class);
        return new SuccessDataResult<>(updatedDiscountDto, "Discount updated successfully");
    }

    @Override
    public DataResult<String> delete(long id) {
        discountRepository.deleteById(id);
        return new SuccessDataResult<>("Discount deleted successfully");
    }
}
