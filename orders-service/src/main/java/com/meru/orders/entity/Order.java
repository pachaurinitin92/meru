package com.meru.orders.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ORDERS")
public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false, updatable = false)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ORDER_DATE")
	private Date orderDate;

	@Column(name = "AMOUNT")
	private Integer amount;

	@Column(name = "CUST_ID")
	private Long customerId;

	@Column(name = "IS_PAID")
	private Boolean isPaid;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, 
			orphanRemoval = true, targetEntity = Item.class)
	@JoinColumn(name = "ORDER_ID", referencedColumnName = "ID")
	private Set<Item> items;

	public Order() {

	}

	public Order(Long id, Date orderDate, Integer amount, Long customerId, Boolean isPaid) {
		super();
		this.id = id;
		this.orderDate = orderDate;
		this.amount = amount;
		this.customerId = customerId;
		this.isPaid = isPaid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Boolean getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(Boolean isPaid) {
		this.isPaid = isPaid;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}
	
	@Override
	public String toString() {
		return "Order [orderId=" + id + ", customerId=" + customerId + ", price="+ amount + "]";
	}
}
