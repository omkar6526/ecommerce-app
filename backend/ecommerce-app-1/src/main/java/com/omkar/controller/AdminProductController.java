package com.omkar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omkar.exception.ProductException;
import com.omkar.model.Order;
import com.omkar.model.Product;
import com.omkar.request.CreateProductRequest;
import com.omkar.response.ApiResponse;
import com.omkar.service.ProductService;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {

	@Autowired
	private ProductService productService;

	
	@PostMapping("/")
	public ResponseEntity<Product>createProduct(@RequestBody CreateProductRequest req){
		
		Product product = productService.createProduct(req);
		
		return new ResponseEntity<Product>(product,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{productId}/delete")
	public ResponseEntity<ApiResponse>deletProduct(@PathVariable Long productId) throws ProductException{
		
		productService.deleteProduct(productId);
		ApiResponse res = new ApiResponse();
		res.setMessage("Product Delete Successfully");
		res.setStatus(true);
		
		return new ResponseEntity<>(res,HttpStatus.OK);	
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Product>>findAllProduct(){
		
		List<Product>product=productService.findProductByCategory(null);	
		return new ResponseEntity<>(product,HttpStatus.OK);
	}
	
	@PutMapping("/{productId}/update")
	public ResponseEntity<Product>createProduct(@RequestBody Product req,@PathVariable Long productId)throws ProductException{
	
		Product product = productService.updateProduct(productId , req);
		return new ResponseEntity<Product>(product,HttpStatus.CREATED);
	}
	
	@PostMapping("/creates")
	public ResponseEntity<ApiResponse>createMultipleProduct(@RequestBody CreateProductRequest[]req){
		
		for(CreateProductRequest product:req) {
			productService.createProduct(product);
		}
		ApiResponse res = new ApiResponse();
		res.setMessage("Product Created Successfully");
		res.setStatus(true);
		
		return new ResponseEntity<>(res,HttpStatus.CREATED);
	}
	
}
