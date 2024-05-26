package com.ecommerce.indo.service;

import com.ecommerce.indo.model.AppUser;
import com.ecommerce.indo.model.Rating;
import com.ecommerce.indo.model.RatingReq;

import java.util.List;

public interface RatingService {

    Rating createRating(RatingReq req, AppUser user);

    List<Rating> fetchProductsRating(Long productId);


}
