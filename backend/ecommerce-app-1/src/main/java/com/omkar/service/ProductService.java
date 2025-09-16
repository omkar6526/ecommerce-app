 package com.omkar.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.omkar.exception.ProductException;
import com.omkar.model.Product;
import com.omkar.request.CreateProductRequest;

public interface ProductService {

	 public Product createProduct(CreateProductRequest req);
	 
	 public String deleteProduct(Long ProductId)throws ProductException;
	 
	 public Product updateProduct(Long ProductId, Product req)throws ProductException;
	 
	 public Product findProductById(Long id )throws ProductException;
	 
	 public List<Product>findProductByCategory(String category);
	  
	 public Page<Product>getAllProduct(String category,List<String>colors,List<String>sizes,Integer minPrice,Integer maxPrice,Integer minDescount, String sort, String stock,Integer pageNumber, Integer pageSize);
	 
	 
}
