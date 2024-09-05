package com.project.eshop.business.concretes;

import com.project.eshop.business.abstracts.CartProductService;
import com.project.eshop.core.utilities.mapping.ModelMapperService;
import com.project.eshop.core.utilities.results.DataResult;
import com.project.eshop.core.utilities.results.SuccessDataResult;
import com.project.eshop.dataAccess.CartProductRepository;
import com.project.eshop.entities.concretes.CartProduct;
import com.project.eshop.entities.dto.CartProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartProductManager implements CartProductService {

    private final CartProductRepository cartProductRepository;
    private final ModelMapperService modelMapperService;

    @Autowired
    public CartProductManager(CartProductRepository cartProductRepository, ModelMapperService modelMapperService) {
        this.cartProductRepository = cartProductRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<CartProductDto>> getProductsByCartId(long cartId) {
        List<CartProduct> cartProducts = this.cartProductRepository.findByCart_Id(cartId);
        List<CartProductDto> cartProductDtos = cartProducts.stream()
                .map(cartProduct -> modelMapperService.map(cartProduct, CartProductDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(cartProductDtos, "Cart products retrieved successfully");
    }
}
