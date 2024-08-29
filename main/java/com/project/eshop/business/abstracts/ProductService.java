/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.project.eshop.business.abstracts;

import com.project.eshop.entities.concretes.Product;
import java.util.List;

/**
 *
 * @author Emre Yıldırım
 */
public interface ProductService {
    List<Product> getAll();
    Product addProduct(Product product);
}
