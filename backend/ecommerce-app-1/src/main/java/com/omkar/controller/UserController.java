package com.omkar.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omkar.exception.UserException;
import com.omkar.model.User;
import com.omkar.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/profile")
	public ResponseEntity<User>getUserProfileHandler(@RequestHeader("Authorization")String jwt)throws UserException{
		 if (jwt.startsWith("Bearer ")) {
		        jwt = jwt.substring(7); // Remove "Bearer " prefix
		    }
		User user = userService.findUserProfileByjwt(jwt);
		
		return new ResponseEntity<User>(user,HttpStatus.ACCEPTED);
		
	}
}
