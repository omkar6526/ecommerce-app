package com.omkar.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="description")
	private String description;
	
	@Column(name="price")
	private Integer price;
	
	@Column(name="discount_price")
	private Integer discountedPrice;
	

	@Column(name="discount_persent")
	private Integer discountPercent;
	
	@Column(name="quntity")
	private Integer quntity;
	
	@Column(name="brand")
	private String brand;
	
	@Column(name="color")
	private String color;
	
	@Embedded
	@ElementCollection
	@Column(name="size")
	private Set<Size>size= new HashSet<>();
	
	@Column(name="image_url")
	private String imageUrl;
	
	@OneToMany(mappedBy="product",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<Rating>rating=new ArrayList<>();
	
	@OneToMany(mappedBy="product",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<Review>reviews=new ArrayList<>();
	
	@Column(name="num_ratings")
	private String numRatings;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	 
	private LocalDateTime createAt;
	
	
	public Product() {
		// TODO Auto-generated constructor stub
	}




	public Product(Long id, String title, String description, Integer price, Integer discountPrice,
			Integer discountPersent, Integer quntity, String brand, String color, Set<Size> size, String imageUrl,
			List<Rating> rating, List<Review> reviews, String numRatings, Category category, LocalDateTime createAt) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.price = price;
		this.discountedPrice = discountPrice;
		this.discountPercent = discountPersent;
		this.quntity = quntity;
		this.brand = brand;
		this.color = color;
		this.size = size;
		this.imageUrl = imageUrl;
		this.rating = rating;
		this.reviews = reviews;
		this.numRatings = numRatings;
		this.category = category;
		this.createAt = createAt;
	}




	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Integer getPrice() {
		return price;
	}


	public void setPrice(Integer price) {
		this.price = price;
	}


	public Integer getDiscountPrice() {
		return discountedPrice;
	}


	public void setDiscountPrice(Integer discountPrice) {
		this.discountedPrice = discountPrice;
	}


	public Integer getDiscountPersent() {
		return discountPercent;
	}


	public void setDiscountPersent(Integer discountPersent) {
		this.discountPercent = discountPersent;
	}


	public Integer getQuntity() {
		return quntity;
	}


	public void setQuntity(Integer quntity) {
		this.quntity = quntity;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public Set<Size> getSize() {
		return size;
	}


	public void setSize(Set<Size> size) {
		this.size = size;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public List<Rating> getRating() {
		return rating;
	}


	public void setRating(List<Rating> rating) {
		this.rating = rating;
	}


	public List<Review> getReviews() {
		return reviews;
	}


	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}


	public String getNumRatings() {
		return numRatings;
	}


	public void setNumRatings(String numRatings) {
		this.numRatings = numRatings;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public LocalDateTime getCreateAt() {
		return createAt;
	}


	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}
	
	
	
}
