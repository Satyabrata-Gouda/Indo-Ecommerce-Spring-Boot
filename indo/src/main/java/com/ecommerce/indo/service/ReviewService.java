package com.ecommerce.indo.service;

import com.ecommerce.indo.model.AppUser;
import com.ecommerce.indo.model.Review;
import com.ecommerce.indo.model.ReviewReq;

import java.util.List;

public interface ReviewService {

Review createReview(ReviewReq req, AppUser user);

List<Review> fetchAllReview(Long productId);
}
