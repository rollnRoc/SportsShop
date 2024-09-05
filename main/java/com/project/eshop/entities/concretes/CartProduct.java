package com.project.eshop.entities.concretes;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 * @author Emre Yıldırım
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart_products")
@IdClass(CartProductId.class)  // Composite key class
public class CartProduct implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", referencedColumnName = "id", nullable = false)
    private Cart cart;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product;

    @Column(name = "quantity", nullable = false)
    private int quantity;
}
