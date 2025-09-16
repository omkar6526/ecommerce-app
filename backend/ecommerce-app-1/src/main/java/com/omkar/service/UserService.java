package com.omkar.service;

import com.omkar.exception.UserException;
import com.omkar.model.User;

public interface UserService {
	
	public User findUserById(Long userId) throws UserException;
	
	public User findUserProfileByjwt(String jwt)throws UserException;

}
