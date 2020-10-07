package com.meru.orders.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER_ITEMS")
public class Item implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false, updatable = false)
	private Long id;

	@Column(name = "ORDER_ID")
	private Long orderId;

	@Column(name = "ITEM_NAME")
	private String itemName;

	@Column(name = "QUANTITY")
	private Integer quantity;

	@Column(name = "AMOUNT")
	private Integer unitPrice;
	
	public Item() {
		
	}

	public Item(Long id, Long orderId, String itemName, Integer quantity, Integer unitPrice) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.itemName = itemName;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}
}
