package com.ab.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "order_table")
public class Order {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "instrument_id")
    private Instrument instrument;
    
    @Column(name = "order_type")
    private String orderType;
    
    @Column(name = "price", columnDefinition = "DECIMAL(10, 2)")
    private double price;
    
    @Column(name = "quantity")
    private int quantity;
    
    @Column(name = "status")
    private String status;
    
    @Column(name = "created_on")
    private LocalDate createdOn;

    
    
	public Order() {
		super();
	}

	public Order(int orderId, User user, Instrument instrument, String orderType, double price, int quantity,
			String status, LocalDate createdOn) {
		super();
		this.orderId = orderId;
		this.user = user;
		this.instrument = instrument;
		this.orderType = orderType;
		this.price = price;
		this.quantity = quantity;
		this.status = status;
		this.createdOn = LocalDate.now();
		
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Instrument getInstrument() {
		return instrument;
	}

	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
	}
    
}
