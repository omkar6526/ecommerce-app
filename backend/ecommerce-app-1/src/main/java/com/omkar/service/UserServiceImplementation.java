package com.omkar.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.omkar.config.jwtProvider;
import com.omkar.exception.UserException;
import com.omkar.model.User;
import com.omkar.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {

	private UserRepository userRepository;
	private jwtProvider jwtProvider;
	
	public UserServiceImplementation(UserRepository userRepository,jwtProvider jwtProvider) {
		this.userRepository = userRepository;
		this.jwtProvider = jwtProvider;
	}
	
	@Override
	public User findUserById(Long userId) throws UserException {
		
		Optional<User>user = userRepository.findById(userId);
		if(user.isPresent()) {
			return user.get();
		}
		
		throw new UserException("User Not Found With Id:"+userId);
	}

	@Override
	public User findUserProfileByjwt(String jwt) throws UserException {
		
//		if (jwt != null && jwt.startsWith("Bearer ")) {
//	        jwt = jwt.substring(7).trim(); // Remove 'Bearer ' and trim whitespace
//	    } else {
//	        throw new UserException("Invalid Authorization header format");
//	    }
		
		String email = jwtProvider.getEmailFromToken(jwt);
		
		User user = userRepository.findByEmail(email);
		
		if(user == null) {
			
			throw new UserException("User Not Found With Email"+email);
			
		}
		
		return user;
	}

}
