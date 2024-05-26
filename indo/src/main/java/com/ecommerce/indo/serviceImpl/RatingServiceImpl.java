package com.ecommerce.indo.serviceImpl;

import com.ecommerce.indo.exception.AuthException;
import com.ecommerce.indo.model.AppUser;
import com.ecommerce.indo.model.Product;
import com.ecommerce.indo.model.Rating;
import com.ecommerce.indo.model.RatingReq;
import com.ecommerce.indo.repo.RatingRepo;
import com.ecommerce.indo.service.ProductService;
import com.ecommerce.indo.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepo ratingRepo;

    @Autowired
    private ProductService productService;

    @Override
    public Rating createRating(RatingReq req, AppUser user) {
        try{
            Product product = productService.fetchProdById(req.getProductId());

            Rating rating = new Rating();
            rating.setProduct(product);
            rating.setUser(user);
            rating.setRating(req.getRating());
            rating.setCreatedAt(LocalDateTime.now());
            return rating;
        }catch (Exception e){
            throw new AuthException(e.getMessage());

        }
    }

    @Override
    public List<Rating> fetchProductsRating(Long productId) {
        try{
            return ratingRepo.findAllProductsRating(productId);
        }catch (Exception e){
            throw new AuthException(e.getMessage());

        }
    }
}
