package com.omkar.service;


import com.omkar.model.Cart;
import com.omkar.model.CartItem;
import com.omkar.model.Product;


public interface CartItemService {

	 void removeCartItem(CartItem item);
	    CartItem isCartItemExist(Cart cart, Product product, String size, Long userId);
	    CartItem createCartItem(CartItem cartItem);
}
