package com.omkar.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.omkar.exception.ProductException;
import com.omkar.model.Product;
import com.omkar.model.Review;
import com.omkar.model.User;
import com.omkar.repository.ProductRepository;
import com.omkar.repository.ReviewRepository;
import com.omkar.request.ReviewRequest;

@Service
public class ReviewServiceImplementation implements ReviewService{
	
	private ReviewRepository reviewRepository;
	private ProductService productService;
	private	ProductRepository productRepository;
	
	public ReviewServiceImplementation( ReviewRepository reviewrepository,ProductService productService,ProductRepository productrepository) {
		this.reviewRepository= reviewRepository;
		this.productService=productService;
		this.productRepository= productrepository;
	}
	
	@Override
	public Review createReview(ReviewRequest req, User user) throws ProductException {
		Product product = productService.findProductById(req.getProductId());
		
		Review review = new Review();
		review.setUser(user);
		review.setProduct(product);
		review.setReview(req.getReview());
		review.setCreatedAt(LocalDateTime.now());
		
		 
		return reviewRepository.save(review);
	}

	@Override
	public List<Review> getAllReview(Long productId) {
		// TODO Auto-generated method stub
		return reviewRepository.getAllProductsReview(productId);
	}

}
