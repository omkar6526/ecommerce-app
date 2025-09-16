package com.omkar.service;

import java.util.Iterator;

import org.springframework.stereotype.Service;

import com.omkar.exception.ProductException;
import com.omkar.model.Cart;
import com.omkar.model.CartItem;
import com.omkar.model.Product;
import com.omkar.model.User;
import com.omkar.repository.CartRepository;
import com.omkar.request.AddItemRequest;

@Service
public class CartServiceImplementation implements  CartService {

	private CartRepository cartRepository;
	private CartItemService cartItemService;
	private ProductService productService;
	
	public CartServiceImplementation(CartRepository cartRepository,CartItemService cartItemService,ProductService productService) {
		this.cartItemService = cartItemService;
		this.cartRepository =cartRepository;
		this.productService =productService;
	}
	
	@Override
	public Cart createCart(User user) {
		
		Cart cart = new Cart();
		cart.setUser(user);
		return cartRepository.save(cart);
	}

	@Override
	public String addCartItem(Long userid, AddItemRequest req) throws ProductException {
		Cart cart = cartRepository.findByUserId(userid);
		Product product = productService.findProductById(req.getProductId());
		
		CartItem isPresent = cartItemService.isCartItemExist(cart, product, req.getSize(), userid);
		
		if(isPresent==null) {
			CartItem cartItem = new CartItem();
			cartItem.setProduct(product);
			cartItem.setCart(cart);
			cartItem.setQuantity(req.getQuantity());
			cartItem.setUserId(userid);
			
			int  price = req.getQuantity()*product.getDiscountPrice();
			cartItem.setPrice(price);
			cartItem.setSize(req.getSize());
			
			CartItem createdCartItem = cartItemService.createCartItem(cartItem);
			cart.getCartItems().add(createdCartItem);
			
		}
		return "Item Add to Cart";
	}

	@Override
	public Cart findUserCart(Long userId) {
		
		Cart cart = cartRepository.findByUserId(userId);
		
		int totalPrice = 0;
		int totalDiscountedPrice = 0;
		int totalItem = 0;
		
		
		
		for(CartItem cartItem:cart.getCartItems()) {
			totalPrice=totalPrice+cartItem.getPrice();
			totalDiscountedPrice=totalDiscountedPrice+cartItem.getDiscountedPrice();
			totalItem=totalItem+cartItem.getQuantity();		
		}
		cart.setTotalDiscountedPrice(totalDiscountedPrice);
		cart.setTotalitem(totalItem);
		cart.setTotalPrice(totalPrice);
		cart.setDiscounte(totalPrice-totalDiscountedPrice);

		return cartRepository.save(cart);
	}

	@Override
	public void removeCartItem(Long userId, Long itemId) {
		        Cart cart = cartRepository.findByUserId(userId);
		        if (cart == null) {
		            throw new RuntimeException("Cart not found for user id: " + userId);
		        }

		        Iterator<CartItem> iterator = cart.getCartItems().iterator();
		        boolean found = false;

		        while (iterator.hasNext()) {
		            CartItem item = iterator.next();
		            if (item.getId().equals(itemId)) {
		                iterator.remove(); // Remove from cart's local list
		                cartItemService.removeCartItem(item); // Remove from DB
		                found = true;
		                break;
		            }
		        }

		        if (!found) {
		            throw new RuntimeException("Cart item not found or doesn't belong to the user.");
		        }

		        cartRepository.save(cart); // Save updated cart
		    
		
	}
	
	@Override
	public void updateCartItem(Long userId, Long itemId, Integer quantity) {
	    Cart cart = cartRepository.findByUserId(userId);

	    if (cart == null) {
	        throw new RuntimeException("Cart not found for user id: " + userId);
	    }

	    cart.getCartItems().stream()
	        .filter(item -> item.getId().equals(itemId))
	        .findFirst()
	        .ifPresent(item -> {
	            item.setQuantity(quantity);

	            // ✅ Recalculate prices
	            Product product = item.getProduct();
	            int totalPrice = quantity * product.getPrice();
	            int discountedPrice = quantity * product.getDiscountPrice();

	            item.setPrice(totalPrice);
	            item.setDiscountedPrice(discountedPrice);
	        });

	    // ✅ Update cart totals
	    int totalPrice = 0;
	    int totalDiscountedPrice = 0;
	    int totalItem = 0;

	    for (CartItem cartItem : cart.getCartItems()) {
	        totalPrice += cartItem.getPrice();
	        totalDiscountedPrice += cartItem.getDiscountedPrice();
	        totalItem += cartItem.getQuantity();
	    }

	    cart.setTotalPrice(totalPrice);
	    cart.setTotalDiscountedPrice(totalDiscountedPrice);
	    cart.setTotalitem(totalItem);
	    cart.setDiscounte(totalPrice - totalDiscountedPrice);

	    cartRepository.save(cart);
	}


	
	

	    
}

