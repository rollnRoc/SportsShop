/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.eshop.business.concretes;

import com.project.eshop.business.abstracts.ProductService;
import com.project.eshop.core.utilities.results.DataResult;
import com.project.eshop.core.utilities.results.ErrorDataResult;
import com.project.eshop.core.utilities.results.SuccessDataResult;
import com.project.eshop.dataAccess.BrandRepository;
import com.project.eshop.dataAccess.CategoryRepository;
import com.project.eshop.dataAccess.PriceRepository;
import com.project.eshop.dataAccess.ProductRepository;
import com.project.eshop.dataAccess.SellerRepository;
import com.project.eshop.entities.concretes.Brand;
import com.project.eshop.entities.concretes.Category;
import com.project.eshop.entities.concretes.Price;
import com.project.eshop.entities.concretes.Product;
import com.project.eshop.entities.concretes.Seller;
import com.project.eshop.entities.dto.BrandDto;
import com.project.eshop.entities.dto.PriceDto;
import com.project.eshop.entities.dto.ProductDto;
import java.util.List;
import java.util.Set;
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
public class ProductManager implements ProductService {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final SellerRepository sellerRepository;
    private final ModelMapper modelMapper;
    private final PriceRepository priceRepository;

    @Autowired
    public ProductManager(ProductRepository productRepository, BrandRepository brandRepository,
            CategoryRepository categoryRepository, SellerRepository sellerRepository, ModelMapper modelMapper, PriceRepository priceRepository) {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
        this.sellerRepository = sellerRepository;
        this.modelMapper = modelMapper;
        this.priceRepository = priceRepository;
    }

    @Override
    public DataResult<ProductDto> addProduct(ProductDto productDto) {
        Brand brand = brandRepository.findById(productDto.getBrandId())
                .orElseThrow(() -> new EntityNotFoundException("Brand not found"));

        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        Seller seller = sellerRepository.findById(productDto.getSellerId())
                .orElseThrow(() -> new EntityNotFoundException("Seller not found"));
        
        Price price = priceRepository.findById(productDto.getPriceId())
                .orElseThrow(() -> new EntityNotFoundException("Price not found"));

        Product product = modelMapper.map(productDto, Product.class);
        product.setBrand(brand);
        product.setCategory(category);
        product.setSeller(seller);
        product.setPrice(price);
        
        price.setProduct(product);
        

        

        Product savedProduct = productRepository.save(product);
        ProductDto savedProductDto = modelMapper.map(savedProduct, ProductDto.class);

        return new SuccessDataResult<>(savedProductDto, "Product added successfully");
    }

    @Override
    public DataResult<ProductDto> updateProduct(ProductDto productDto) {
        Product product = productRepository.findById(productDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        Brand brand = brandRepository.findById(productDto.getBrandId())
                .orElseThrow(() -> new EntityNotFoundException("Brand not found"));

        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        Seller seller = sellerRepository.findById(productDto.getSellerId())
                .orElseThrow(() -> new EntityNotFoundException("Seller not found"));

        product.setProductName(productDto.getProductName());
        product.setStock(productDto.getStock());
        product.setBrand(brand);
        product.setCategory(category);
        product.setSeller(seller);

        Product updatedProduct = productRepository.save(product);
        ProductDto updatedProductDto = modelMapper.map(updatedProduct, ProductDto.class);

        return new SuccessDataResult<>(updatedProductDto, "Product updated successfully");
    }

    @Override
    public DataResult<ProductDto> getProductById(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        return new SuccessDataResult<>(productDto, "Product found successfully");
    }

    @Override
    public DataResult<List<ProductDto>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(productDtos, "All products retrieved successfully");
    }
}
