package com.omkar.service;

import java.util.List;

import com.omkar.exception.ProductException;
import com.omkar.model.Review;
import com.omkar.model.User;
import com.omkar.request.ReviewRequest;

public interface ReviewService {

	public Review createReview(ReviewRequest req,User user) throws  ProductException;
	
	public List<Review>getAllReview(Long productId);
	
	
}
