package com.omkar.model;

public class PayamentDetails {

	private String paymentMethod;
	private String status;
	private String paymentId;
	private String razorpaypaymentLinkId;
	private String razorpaypaymentLinkRefernceId;
	private String razorpaypaymentLinkStatus;
	private String razorpaypaymentId;
	
	public PayamentDetails() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public PayamentDetails(String paymentMethod, String status, String paymentId, String razorpaypaymentLinkId,
			String razorpaypaymentLinkRefernceId, String razorpaypaymentLinkStatus, String razorpaypaymentId) {
		super();
		this.paymentMethod = paymentMethod;
		this.status = status;
		this.paymentId = paymentId;
		this.razorpaypaymentLinkId = razorpaypaymentLinkId;
		this.razorpaypaymentLinkRefernceId = razorpaypaymentLinkRefernceId;
		this.razorpaypaymentLinkStatus = razorpaypaymentLinkStatus;
		this.razorpaypaymentId = razorpaypaymentId;
	}



	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getRazorpaypaymentLinkId() {
		return razorpaypaymentLinkId;
	}
	public void setRazorpaypaymentLinkId(String razorpaypaymentLinkId) {
		this.razorpaypaymentLinkId = razorpaypaymentLinkId;
	}
	public String getRazorpaypaymentLinkRefernceId() {
		return razorpaypaymentLinkRefernceId;
	}
	public void setRazorpaypaymentLinkRefernceId(String razorpaypaymentLinkRefernceId) {
		this.razorpaypaymentLinkRefernceId = razorpaypaymentLinkRefernceId;
	}
	public String getRazorpaypaymentLinkStatus() {
		return razorpaypaymentLinkStatus;
	}
	public void setRazorpaypaymentLinkStatus(String razorpaypaymentLinkStatus) {
		this.razorpaypaymentLinkStatus = razorpaypaymentLinkStatus;
	}
	public String getRazorpaypaymentId() {
		return razorpaypaymentId;
	}
	public void setRazorpaypaymentId(String razorpaypaymentId) {
		this.razorpaypaymentId = razorpaypaymentId;
	}
	
	
}
