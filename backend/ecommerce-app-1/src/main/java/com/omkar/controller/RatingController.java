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
import com.omkar.model.Rating;
import com.omkar.model.User;
import com.omkar.request.RatingRequest;
import com.omkar.service.RatingService;
import com.omkar.service.UserService;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

	@Autowired
	private RatingService ratingService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/create")
	public ResponseEntity<Rating>createRating(@RequestBody RatingRequest req,@RequestHeader("Authorization")String jwt)throws UserException,ProductException{
		
		User user = userService.findUserProfileByjwt(jwt);
		
		Rating rating = ratingService.createRating(req,user);
		
		return new ResponseEntity<Rating>(rating,HttpStatus.CREATED);
	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<List<Rating>>getProductRating(@PathVariable Long productId,@RequestHeader("Authorization")String jwt)throws UserException,ProductException{
		
		User user = userService.findUserProfileByjwt(jwt);
		
		List<Rating> ratings = ratingService.getProductsReating(productId);
		return new ResponseEntity<>(ratings,HttpStatus.CREATED);
		
	}
}
