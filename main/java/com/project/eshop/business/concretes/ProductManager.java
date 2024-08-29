/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.eshop.business.concretes;

import com.project.eshop.business.abstracts.ProductService;
import com.project.eshop.dataAccess.ProductRepository;
import com.project.eshop.entities.concretes.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Emre Yıldırım
 */

@Service
public class ProductManager implements ProductService {
    
    private ProductRepository productRepository;

    
    @Autowired
    public ProductManager(ProductRepository productRepository) {
        super();
        this.productRepository = productRepository;
    }
        
    
    @Override
    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Product addProduct(Product product) {
        return this.productRepository.save(product);
    }
    
}
