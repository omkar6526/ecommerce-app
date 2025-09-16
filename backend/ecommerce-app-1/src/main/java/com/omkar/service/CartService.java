package com.omkar.service;

import com.omkar.exception.ProductException;
import com.omkar.model.Cart;
import com.omkar.model.CartItem;
import com.omkar.model.Product;
import com.omkar.model.User;
import com.omkar.request.AddItemRequest;

public interface CartService {
	
	public Cart createCart(User user);
	
	public String addCartItem(Long userid,AddItemRequest req)throws ProductException;

	public Cart findUserCart(Long userId);

	void removeCartItem(Long userId, Long itemId);
	
	 public void updateCartItem(Long userId, Long itemId, Integer quantity);
	


}

