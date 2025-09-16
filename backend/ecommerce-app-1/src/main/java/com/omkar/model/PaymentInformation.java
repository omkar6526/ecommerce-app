package com.omkar.model;

import jakarta.persistence.Column;

public class PaymentInformation {
	
	@Column(name="cardholder_name")
	private String cardholderName;
	
	@Column(name="card_Number")
	private String cardNumber;
	
	@Column(name="expiration_date")
	private String expirationDate;
	
	@Column(name="cvv")
	private String cvv;

}
