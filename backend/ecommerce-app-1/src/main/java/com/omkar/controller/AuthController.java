package com.omkar.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.omkar.config.jwtProvider;
import com.omkar.exception.UserException;
import com.omkar.model.Cart;
import com.omkar.model.User;
import com.omkar.repository.UserRepository;
import com.omkar.request.LoginRequest;
import com.omkar.response.AuthResponse;
import com.omkar.service.CartService;
import com.omkar.service.CustomeUserServiceimplementation;

@RestController 
@RequestMapping("/auth")
public class AuthController {


	
	private UserRepository userRepository;
	private jwtProvider jwtProvider;
	private PasswordEncoder passwordEncoder;
	private CustomeUserServiceimplementation customeUserService;
	private CartService cartService;
	
	
	public AuthController(UserRepository userRepository,CustomeUserServiceimplementation customeUserServiceDetails,PasswordEncoder passwordEncoder,jwtProvider jwtProvider,CartService cartService) {
		this.userRepository= userRepository;
		this.customeUserService =customeUserServiceDetails;
		this.passwordEncoder = passwordEncoder;
		this.jwtProvider = jwtProvider;
		this.cartService=cartService;

	}
	
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse>createUserHandler(@RequestBody User user)throws UserException{ 
		
		String email=user.getEmail();
		String password = user.getPassword();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		
		User isEmailExist= userRepository.findByEmail(email);
		
		if(isEmailExist!=null) {
			throw new UserException("Email Is Already Used With Another Accoung");
			
		}
		
				User createdUser= new User();
		createdUser.setEmail(email);
		createdUser.setPassword(passwordEncoder.encode(password));
		createdUser.setFirstName(firstName);
		createdUser.setLastName(lastName);
		
		User savedUser = userRepository.save(createdUser);
		Cart cart= cartService.createCart(savedUser);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication); 
		
		String Token = jwtProvider.genrateTkone(authentication);
		
		AuthResponse authResponse= new AuthResponse("","");
		authResponse.setJwt(Token);
		authResponse.setMessage("Signup success ");
		
		return new ResponseEntity <AuthResponse>(authResponse,HttpStatus.CREATED);

}
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse>loginUserHandler(@RequestBody LoginRequest loginRequest){
		
		String username= loginRequest.getEmail();
		String password = loginRequest.getPassword();
		
		Authentication authentication= authenticate(username,password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String Token = jwtProvider.genrateTkone(authentication);
		
		AuthResponse authResponse= new AuthResponse("","");
		authResponse.setJwt(Token);
		authResponse.setMessage("Signin success ");
		
		return new ResponseEntity <AuthResponse>(authResponse,HttpStatus.CREATED);
		
}

	private Authentication authenticate(String username, String password) {
		UserDetails userDetails = customeUserService.loadUserByUsername(username);
		
		if(userDetails==null) {
			throw new BadCredentialsException("Invalid UserName...");
		}
		
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid Password....");
		}
		return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
	} 
} 
