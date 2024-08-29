/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.eshop.controller;

import com.project.eshop.business.abstracts.ProductService;
import com.project.eshop.entities.concretes.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Emre Yıldırım
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    
    @Autowired
    public ProductController(ProductService productService) {
        super();
        this.productService = productService;
    }
    
    
   @GetMapping("/getAll")
   public List<Product> getAll(){
       return this.productService.getAll();
   }
    
   
   @PostMapping("/addProduct")
   @ResponseStatus(HttpStatus.CREATED)
   public Product addProduct(@RequestBody Product product){
       return this.productService.addProduct(product);
   }
    
    
}
