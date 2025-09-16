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

import com.omkar.exception.OrderException;
import com.omkar.exception.UserException;
import com.omkar.model.Address;
import com.omkar.model.Order;
import com.omkar.model.User;
import com.omkar.service.OrderService;
import com.omkar.service.UserService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<Order>createOrder(@RequestBody Address shippingAddress,@RequestHeader("Authorization")String jwt)throws UserException{
		
		User user = userService.findUserProfileByjwt(jwt);
		
		Order order = orderService.createOrder(user, shippingAddress);
		
		return new ResponseEntity<Order>(order,HttpStatus.CREATED);
	}
	
	@GetMapping("user")
	public ResponseEntity<List<Order>>usersOrderHistory(@RequestHeader("Authorization")String jwt)throws UserException{
		
		User user = userService.findUserProfileByjwt(jwt);
		
		List<Order> orders = orderService.userOrderHistory(user.getId());
		return new ResponseEntity<>(orders,HttpStatus.CREATED);
		
	}
	
	
	@GetMapping("/{Id}")
	public ResponseEntity<Order>findOrderById(@PathVariable("Id")Long orderId,@RequestHeader("Authorization")String jwt)throws UserException, OrderException{
		
		User user = userService.findUserProfileByjwt(jwt);
		
		Order order = orderService.findOrderById(orderId);
		
		return new ResponseEntity<>(order,HttpStatus.CREATED);
	}
	
}
