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
import java.util.Optional;
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
        Optional<Brand> brandOptional = brandRepository.findByBrandName(productDto.getBrandName());
        if (brandOptional.isEmpty()) {
            return new ErrorDataResult<>("Brand not found");
        }
        Brand brand = brandOptional.get();

        Optional<Category> categoryOptional = categoryRepository.findByCategoryName(productDto.getCategoryName());
        if (categoryOptional.isEmpty()) {
            return new ErrorDataResult<>("Category not found");
        }
        Category category = categoryOptional.get();

        // Seller'ı bulma
        Optional<Seller> sellerOptional = sellerRepository.findByBusinessName(productDto.getSellerName());
        if (sellerOptional.isEmpty()) {
            return new ErrorDataResult<>("Seller not found");
        }
        Seller seller = sellerOptional.get();

        Optional<Price> priceOptional = priceRepository.findById(productDto.getPriceId());
        if (priceOptional.isEmpty()) {
            return new ErrorDataResult<>("Price not found");
        }
        Price price = priceOptional.get();

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
        Optional<Product> productOptional = productRepository.findById(productDto.getId());
        if (productOptional.isEmpty()) {
            return new ErrorDataResult<>("Product not found");
        }
        Product product = productOptional.get();

        Optional<Brand> brandOptional = brandRepository.findByBrandName(productDto.getBrandName());
        if (brandOptional.isEmpty()) {
            return new ErrorDataResult<>("Brand not found");
        }
        Brand brand = brandOptional.get();

        Optional<Category> categoryOptional = categoryRepository.findByCategoryName(productDto.getCategoryName());
        if (categoryOptional.isEmpty()) {
            return new ErrorDataResult<>("Category not found");
        }
        Category category = categoryOptional.get();

        // Seller'ı bulma
        Optional<Seller> sellerOptional = sellerRepository.findByBusinessName(productDto.getSellerName());
        if (sellerOptional.isEmpty()) {
            return new ErrorDataResult<>("Seller not found");
        }
        Seller seller = sellerOptional.get();

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
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            return new ErrorDataResult<>("Product not found");
        }
        ProductDto productDto = modelMapper.map(productOptional.get(), ProductDto.class);
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
