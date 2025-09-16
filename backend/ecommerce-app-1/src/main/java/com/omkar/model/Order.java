 package com.omkar.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name="order_id")
	private String orderId;
	
	@ManyToOne  
	private User user;
	
	@OneToMany(mappedBy ="order",cascade = CascadeType.ALL)
	private List<OrderItem>orderIteams = new ArrayList<>();
	
	private LocalDateTime orderDate;
	
	private LocalDateTime delivaryDate;
	
	@OneToOne
	private Address shippingAddress;
	
	@Embedded
	private PayamentDetails paymentDetails = new PayamentDetails();
	
	private double totalPrice;
	
	private Integer totlaDiscountedPrice;
	
	private Integer discount;
	
	private String orderStatus;
	
	private Integer totalIteam;
	
	private LocalDateTime createdAt;
	
	public Order() {
		// TODO Auto-generated constructor stub
	}

	
	
	public Order(Long id, String orderId, User user, List<OrderItem> orderIteams, LocalDateTime orderDate,
			LocalDateTime delivaryDate, Address shippingAddress, PayamentDetails paymentDetails, double totalPrice,
			Integer totlaDiscountedPrice, Integer discount, String orderStatus, Integer totalIteam,
			LocalDateTime createdAt) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.user = user;
		this.orderIteams = orderIteams;
		this.orderDate = orderDate;
		this.delivaryDate = delivaryDate;
		this.shippingAddress = shippingAddress;
		this.paymentDetails = paymentDetails;
		this.totalPrice = totalPrice;
		this.totlaDiscountedPrice = totlaDiscountedPrice;
		this.discount = discount;
		this.orderStatus = orderStatus;
		this.totalIteam = totalIteam;
		this.createdAt = createdAt;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItem> getOrderIteams() {
		return orderIteams;
	}

	public void setOrderIteams(List<OrderItem> orderIteams) {
		this.orderIteams = orderIteams;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDateTime getDelivaryDate() {
		return delivaryDate;
	}

	public void setDelivaryDate(LocalDateTime delivaryDate) {
		delivaryDate = delivaryDate;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public PayamentDetails getPaymentDetails() {
		return paymentDetails;
	}

	public void setPaymentDetails(PayamentDetails paymentDetails) {
		this.paymentDetails = paymentDetails;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getTotlaDiscountedPrice() {
		return totlaDiscountedPrice;
	}

	public void setTotlaDiscountedPrice(Integer totlaDiscountedPrice) {
		this.totlaDiscountedPrice = totlaDiscountedPrice;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getTotalIteam() {
		return totalIteam;
	}

	public void setTotalIteam(Integer totalIteam) {
		this.totalIteam = totalIteam;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	
	
	
	
}
