//package com.project.eshop.business.concretes;
//
//import com.project.eshop.business.abstracts.FavouriteProductService;
//import com.project.eshop.core.utilities.results.DataResult;
//import com.project.eshop.core.utilities.results.Result;
//import com.project.eshop.core.utilities.results.SuccessDataResult;
//import com.project.eshop.core.utilities.results.SuccessResult;
//import com.project.eshop.dataAccess.FavouriteProductRepository;
//import com.project.eshop.dataAccess.FavouriteRepository;
//import com.project.eshop.dataAccess.ProductRepository;
//import com.project.eshop.dataAccess.UserRepository;
//import com.project.eshop.entities.concretes.Favourite;
//import com.project.eshop.entities.concretes.FavouriteProducts;
//import com.project.eshop.entities.concretes.Product;
//import com.project.eshop.entities.concretes.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.persistence.EntityNotFoundException;
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class FavouriteProductManager {
//
//    @Autowired
//    private FavouriteProductRepository favouriteProductRepository;
//
//    @Autowired
//    private FavouriteRepository favouriteRepository;
//
//    public DataResult<FavouriteProducts> addProductToFavourite(Long userId, Long productId) {
//        Optional<Favourite> favouriteOpt = favouriteRepository.findByUserId(userId);
//
//        if (!favouriteOpt.isPresent()) {
//            return new DataResult<>(null, false, "Favourite not found for user");
//        }
//
//        Favourite favourite = favouriteOpt.get();
//        FavouriteProducts favouriteProduct = new FavouriteProducts();
//        favouriteProduct.setFavourite(favourite);
//        favouriteProduct.setProductId(productId);
//        favouriteProduct.setFavouriteDate(LocalDateTime.now());
//
//        FavouriteProducts savedProduct = favouriteProductRepository.save(favouriteProduct);
//
//        return new DataResult<>(savedProduct, true, "Product added to favourites successfully");
//    }
//
//    public DataResult<List<FavouriteProducts>> getAllFavouriteProducts(Long userId) {
//        Optional<Favourite> favouriteOpt = favouriteRepository.findByUserId(userId);
//
//        if (!favouriteOpt.isPresent()) {
//            return new DataResult<>(null, false, "Favourite not found for user");
//        }
//
//        List<FavouriteProducts> favouriteProducts = favouriteProductRepository.findByFavouriteId(favouriteOpt.get().getId());
//        return new DataResult<>(favouriteProducts, true, "Favourite products retrieved successfully");
//    }
//}
//
