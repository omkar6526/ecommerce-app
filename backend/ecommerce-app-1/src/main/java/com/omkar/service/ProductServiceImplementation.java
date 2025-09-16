package com.omkar.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.omkar.exception.ProductException;
import com.omkar.model.Category;
import com.omkar.model.Product;
import com.omkar.repository.CategoryRepository;
import com.omkar.repository.ProductRepository;
import com.omkar.request.CreateProductRequest;


@Service
public class ProductServiceImplementation implements ProductService {
	
	private ProductRepository productRepository;
	private UserService userService;
	private CategoryRepository categoryRepository;
	
	public ProductServiceImplementation(ProductRepository productRepository,UserService userService,CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.userService = userService;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public Product createProduct(CreateProductRequest req) {
		
		Category topLevel= categoryRepository.findByName(req.getTopLevelCategory());
		
		if(topLevel==null) {
			Category topLevelCategory = new Category();
			topLevelCategory.setName(req.getTopLevelCategory());
			topLevelCategory.setLevel(1);
			
			topLevel = categoryRepository.save(topLevelCategory);
			
		}
		
		Category secondLevel= categoryRepository.findByNameAndParant(req.getSecondLevelCategory(),topLevel.getName());
		
		if(secondLevel==null) {
			Category secondLevelCategory = new Category();
			secondLevelCategory.setName(req.getSecondLevelCategory());
			secondLevelCategory.setLevel(2);
			
			secondLevel = categoryRepository.save(secondLevelCategory);
			
			
		}
		
		Category thirdLevel= categoryRepository.findByNameAndParant(req.getThirdLevelCategory(),secondLevel.getName());
		
		if(thirdLevel==null) {
			Category thirdLevelCategory = new Category();
			thirdLevelCategory.setName(req.getThirdLevelCategory());
			thirdLevelCategory.setLevel(3);
			
			thirdLevel = categoryRepository.save(thirdLevelCategory);
			
			
		}
		
		Product product = new Product();
		product.setTitle(req.getTitle());
		product.setColor(req.getColor());
		product.setDescription(req.getDescription());
		product.setDiscountPrice(req.getDiscountedPrice());
		product.setDiscountPersent(req.getDiscountedPersent());
		product.setImageUrl(req.getImageUrl());
		product.setBrand(req.getBran());
		product.setPrice(req.getPrice());
		product.setQuntity(req.getQuantity()); 
		product.setCategory(thirdLevel);
		product.setSize(req.getSize());
		product.setCreateAt(LocalDateTime.now());
		
		
		Product savedProduct = productRepository.save(product);
		
		return savedProduct; 
	}

	@Override
	public String deleteProduct(Long ProductId) throws ProductException {
		
		Product product = findProductById(ProductId);
		product.getSize().clear();
		productRepository.delete(product);
		
		return "Product Deleted Successfully"; 
	}

	@Override
	public Product updateProduct(Long ProductId, Product req) throws ProductException {
		
		Product product = findProductById(ProductId);
		
		if(req.getQuntity()!=0) {
			product.setQuntity(req.getQuntity());
		}
		
		return productRepository.save(product);
	}

	@Override
	public Product findProductById(Long id) throws ProductException {
		if (id == null) {
			throw new ProductException("Product ID must not be null");
		}
		Optional<Product> opt = productRepository.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		}
		throw new ProductException("Product Not Found With Id - " + id);
	}

//	@Override
//	public List<Product> findAllProduct() {
//		// TODO Auto-generated method stub
//		return productRepository.findAll();
//	}

	@Override
	public Page<Product> getAllProduct(String category, List<String> colors, List<String> size, Integer minPrice,
			Integer maxPrice, Integer minDescount, String sort, String stock, Integer pageNumber, Integer pageSize) {
		
		Pageable pageble = PageRequest.of(pageNumber,pageSize);
		
		List<Product>products= productRepository.filterProducts(category, minPrice, maxPrice, pageSize, sort);
		
		if(!colors.isEmpty()) {
			products=products.stream().filter(p-> colors.stream().anyMatch(c->c.equalsIgnoreCase(p.getColor()))).collect(Collectors.toList());
		}
		if(stock!=null) {
			if(stock.equals("in_stock")){
				products = products.stream().filter(p->p.getQuntity()>0).collect(Collectors.toList());
			}
			else if (stock.equals("out_of_stock")) {
				products = products.stream().filter(p->p.getQuntity()<1).collect(Collectors.toList());
			}
		}
		
		int startIndex = (int) pageble.getOffset();
		int endIndex = Math.min(startIndex +  pageble.getPageSize(),products.size());
		List<Product>pageContent = products.subList(startIndex, endIndex);
		
		Page<Product>filteredProducts = new PageImpl<>(pageContent,pageble,products.size());
		
		return filteredProducts;
	}

	@Override
	public List<Product> findProductByCategory(String category) {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}
	
	
}
