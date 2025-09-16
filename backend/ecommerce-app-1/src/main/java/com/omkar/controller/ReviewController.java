package com.omkar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omkar.exception.ProductException;
import com.omkar.exception.UserException;
import com.omkar.model.Review;
import com.omkar.model.User;
import com.omkar.request.ReviewRequest;
import com.omkar.service.ReviewService;
import com.omkar.service.UserService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/create")
	public ResponseEntity<Review>createReviewReview(@RequestBody ReviewRequest req,@RequestHeader("Authorization")String jwt)throws UserException,ProductException{
		
		User user = userService.findUserProfileByjwt(jwt);
		
		Review review = reviewService.createReview(req,user);
		
		return new ResponseEntity<Review>(review,HttpStatus.CREATED);
	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<List<Review>>getProductReview(@PathVariable Long productId,@RequestHeader("Authorization")String jwt)throws UserException,ProductException{
		
		List<Review> review = reviewService.getAllReview(productId);
		return new ResponseEntity<>(review,HttpStatus.ACCEPTED);
		
	}
}
