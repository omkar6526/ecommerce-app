package com.omkar.service;

import java.util.List;

import com.omkar.exception.ProductException;
import com.omkar.model.Rating;
import com.omkar.model.User;
import com.omkar.request.RatingRequest;

public interface RatingService  {

	public Rating createRating(RatingRequest req,User user)throws ProductException;
	public List<Rating>getProductsReating(Long productId);
	
	
}
