package com.meru.carts.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CART")
public class Cart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "CUST_ID")
	private Long customerId;

	@Column(name = "ITEM_NAME")
	private String productName;

	@Column(name = "PRICE")
	private Double price;

	@Column(name = "QUANTITY")
	private Integer quantity;
	
	public Cart() {
		
	}
	
	public Cart(Integer quantity) {
		this.quantity = quantity;
	}
	
	public Cart(Long id, Long customerId, String productName, Double price, Integer quantity) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "Cart [cartId=" + id + ", customerId=" + customerId + ", productName="
				+ productName + ", quantity=" + quantity + "]";
	}

}
