package com.omkar.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omkar.exception.UserException;
import com.omkar.model.User;
import com.omkar.request.UpdateCartItemRequest;
import com.omkar.response.ApiResponse;
import com.omkar.service.CartService;
import com.omkar.service.UserService;

@RestController
@RequestMapping("/api/cart_items")
public class CartIteamController {
	  @Autowired
	    private CartService cartService;

	    @Autowired
	    private UserService userService;

	    @DeleteMapping("/{itemId}")
	    public ResponseEntity<ApiResponse> removeCartItem(
	            @PathVariable("itemId") Long itemId,
	            @RequestHeader("Authorization") String jwt
	    ) throws UserException {
	        // Validate JWT and fetch user
	        User user = userService.findUserProfileByjwt(jwt);

	        // Remove cart item
	        cartService.removeCartItem(user.getId(), itemId);

	        // Prepare response
	        ApiResponse response = new ApiResponse();
	        response.setMessage("Cart item removed successfully");
	        response.setStatus(true);

	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	    
	    @PutMapping("/{itemId}")
	    public ResponseEntity<ApiResponse> updateCartItem(
	            @PathVariable("itemId") Long itemId,
	            @RequestHeader("Authorization") String jwt,
	            @RequestBody UpdateCartItemRequest req
	    ) throws UserException {

	        User user = userService.findUserProfileByjwt(jwt);
	        cartService.updateCartItem(user.getId(), itemId, req.getQuantity());

	        ApiResponse response = new ApiResponse();
	        response.setMessage("Cart item updated successfully");
	        response.setStatus(true);

	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }

	}