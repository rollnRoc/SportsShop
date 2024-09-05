package com.project.eshop.business.concretes;

import com.project.eshop.business.abstracts.FavouriteService;
import com.project.eshop.core.utilities.results.*;
import com.project.eshop.dataAccess.FavouriteProductRepository;
import com.project.eshop.dataAccess.FavouriteRepository;
import com.project.eshop.dataAccess.ProductRepository;
import com.project.eshop.dataAccess.UserRepository;
import com.project.eshop.entities.concretes.Favourite;
import com.project.eshop.entities.concretes.FavouriteProducts;
import com.project.eshop.entities.concretes.Product;
import com.project.eshop.entities.concretes.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FavouriteManager implements FavouriteService {

    private final FavouriteRepository favouriteRepository;
    private final FavouriteProductRepository favouriteProductRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public FavouriteManager(FavouriteRepository favouriteRepository, FavouriteProductRepository favouriteProductRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.favouriteRepository = favouriteRepository;
        this.favouriteProductRepository = favouriteProductRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public DataResult<FavouriteProducts> addProductToFavourites(Long userId, Long productId) {
        // Kullanıcı ve Ürün var mı diye kontrol et
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Product> productOptional = productRepository.findById(productId);

        if (userOptional.isEmpty()) {
            return new ErrorDataResult<>("Kullanıcı bulunamadı");
        }

        if (productOptional.isEmpty()) {
            return new ErrorDataResult<>("Ürün bulunamadı");
        }

        User user = userOptional.get();
        Product product = productOptional.get();

        // Kullanıcının mevcut favouriteleri var mı kontrol et
        Favourite favourite = favouriteRepository.findByUserId(userId);

        if (favourite == null) {
            // Eğer yoksa, yeni bir favourite listesi oluştur
            favourite = new Favourite();
            favourite.setUser(user);
            favourite = favouriteRepository.save(favourite);
        }

        // Ürün zaten favouritelere eklenmiş mi kontrol et
        List<FavouriteProducts> existingFavouriteProducts = favouriteProductRepository.findByFavouriteId(favourite.getId());
        boolean isAlreadyFavourited = existingFavouriteProducts.stream()
                .anyMatch(favouriteProduct -> favouriteProduct.getProduct().getId() == productId);

        if (isAlreadyFavourited) {
            return new ErrorDataResult<>("Ürün zaten favorilere eklenmiş");
        }

        // Yeni FavouriteProducts oluştur ve kaydet
        FavouriteProducts favouriteProduct = new FavouriteProducts();
        favouriteProduct.setFavourite(favourite);
        favouriteProduct.setProduct(product);
        favouriteProduct.setFavouriteDate(new Date()); // Favorilere eklenme tarihi
        favouriteProductRepository.save(favouriteProduct);

        return new SuccessDataResult<>(favouriteProduct, "Ürün favorilere eklendi");
    }

    @Override
    public DataResult<List<FavouriteProducts>> getFavouriteProductsByUserId(Long userId) {
        Favourite favourite = favouriteRepository.findByUserId(userId);
        if (favourite == null) {
            return new ErrorDataResult<>("Kullanıcının favori listesi bulunamadı");
        }

        List<FavouriteProducts> favouriteProducts = favouriteProductRepository.findByFavouriteId(favourite.getId());
        return new SuccessDataResult<>(favouriteProducts, "Favori ürünler başarıyla getirildi.");
    }
}
