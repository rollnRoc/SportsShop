package com.project.eshop.business.concretes;

import com.project.eshop.business.abstracts.CartService;
import com.project.eshop.core.utilities.mapping.ModelMapperService;
import com.project.eshop.core.utilities.results.*;
import com.project.eshop.dataAccess.CartProductRepository;
import com.project.eshop.dataAccess.CartRepository;
import com.project.eshop.dataAccess.ProductRepository;
import com.project.eshop.entities.concretes.Cart;
import com.project.eshop.entities.concretes.CartProduct;
import com.project.eshop.entities.concretes.Product;
import com.project.eshop.entities.dto.CartDto;
import com.project.eshop.entities.dto.CartProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Lazy;

@Service
public class CartManager implements CartService {

    private CartRepository cartRepository;
    private CartProductRepository cartProductRepository;
    private ProductRepository productRepository;
    private ModelMapperService modelMapperService;

    @Autowired
    public CartManager(@Lazy CartRepository cartRepository,
                       @Lazy CartProductRepository cartProductRepository,
                       @Lazy ProductRepository productRepository,
                       @Lazy ModelMapperService modelMapperService) {
        this.cartRepository = cartRepository;
        this.cartProductRepository = cartProductRepository;
        this.productRepository = productRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<CartDto> getCartByUserId(Long userId) {
        Cart cart = cartRepository.findByUser_Id(userId);
        if (cart == null) {
            return new SuccessDataResult<>(null, "Cart not found for user.");
        }
        CartDto cartDto = modelMapperService.map(cart, CartDto.class);
        return new SuccessDataResult<>(cartDto);
    }

    @Override
    public Result addProductToCart(Long cartId, CartProductDto cartProductDto) {
        Optional<Cart> cart = cartRepository.findById(cartId);
        if (!cart.isPresent()) {
            return new ErrorResult("Cart not found.");
        }
        Optional<Product> product = productRepository.findById(cartProductDto.getProductId());
        if (!product.isPresent()) {
            return new ErrorResult("Product not found.");
        }
        CartProduct cartProduct = modelMapperService.map(cartProductDto, CartProduct.class);
        cartProduct.setCart(cart.get());
        cartProduct.setProduct(product.get());
        cartProductRepository.save(cartProduct);
        return new SuccessResult("Product added to cart.");
    }

    @Override
    public Result removeProductFromCart(Long cartId, Long productId) {
        CartProduct cartProduct = cartProductRepository.findByCart_IdAndProduct_Id(cartId, productId);
        if (cartProduct == null) {
            return new ErrorResult("Product not found in cart.");
        }
        cartProductRepository.delete(cartProduct);
        return new SuccessResult("Product removed from cart.");
    }

    @Override
    public DataResult<List<CartProductDto>> getAllProductsInCart(Long cartId) {
        List<CartProduct> cartProducts = cartProductRepository.findByCart_Id(cartId);
        List<CartProductDto> productDtos = cartProducts.stream()
                .map(product -> modelMapperService.map(product, CartProductDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(productDtos, "Products fetched.");
    }
}

