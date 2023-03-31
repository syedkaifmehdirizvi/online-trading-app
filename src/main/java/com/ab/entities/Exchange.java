package com.ab.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "exchange")
public class Exchange 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exchange_id")
    private int exchangeId;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "region", length = 50)
    private String region;

    @Column(name = "fee_ladder")
    private double feeLadder;

    @Column(name = "created_on")
    private LocalDate createdOn;

    
    
	public Exchange() {
		super();
	}

	public Exchange(int exchangeId, String name, String region, double feeLadder, LocalDate createdOn) {
		super();
		this.exchangeId = exchangeId;
		this.name = name;
		this.region = region;
		this.feeLadder = feeLadder;
		this.createdOn = createdOn;
	}

	
	public int getExchangeId() {
		return exchangeId;
	}

	public void setExchangeId(int exchangeId) {
		this.exchangeId = exchangeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public double getFeeLadder() {
		return feeLadder;
	}

	public void setFeeLadder(double feeLadder) {
		this.feeLadder = feeLadder;
	}

	public LocalDate getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
	}
    
    
    
    
}
