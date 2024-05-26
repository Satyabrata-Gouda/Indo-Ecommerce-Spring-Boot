package com.ecommerce.indo.serviceImpl;

import com.ecommerce.indo.exception.AuthException;
import com.ecommerce.indo.model.AppUser;
import com.ecommerce.indo.model.Product;
import com.ecommerce.indo.model.Review;
import com.ecommerce.indo.model.ReviewReq;
import com.ecommerce.indo.repo.ProductRepo;
import com.ecommerce.indo.repo.ReviewRepo;
import com.ecommerce.indo.service.ProductService;
import com.ecommerce.indo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepo reviewRepo;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepo productRepo;


    @Override
    public Review createReview(ReviewReq req, AppUser user) {
        try {
            Product product = productService.fetchProdById(req.getProductId());

            Review review = new Review();
            review.setUser(user);
            review.setProduct(product);
            review.setReview(req.getReview());
            review.setCreatedAt(LocalDateTime.now());

            return reviewRepo.save(review);
        } catch (Exception e) {
            throw new AuthException(e.getMessage());
        }
    }

    @Override
    public List<Review> fetchAllReview(Long productId) {
        try {
            return reviewRepo.findAllProductsReview(productId);
        } catch (Exception e) {
            throw new AuthException(e.getMessage());
        }
    }
}
