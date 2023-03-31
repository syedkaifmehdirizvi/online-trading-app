package com.ab.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_book")
public class OrderBook 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderBookId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instrument_id")
    private Instrument instrument;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exchange_id")
    private Exchange exchange;

    @Column(name = "created_on")
    private LocalDate createdOn;

    
	public OrderBook() {
		super();
	}


	public OrderBook(int orderBookId, Instrument instrument, Exchange exchange, LocalDate createdOn) {
		super();
		this.orderBookId = orderBookId;
		this.instrument = instrument;
		this.exchange = exchange;
		this.createdOn = createdOn;
	}


	
	public int getOrderBookId() {
		return orderBookId;
	}


	public void setOrderBookId(int orderBookId) {
		this.orderBookId = orderBookId;
	}


	public Instrument getInstrument() {
		return instrument;
	}


	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}


	public Exchange getExchange() {
		return exchange;
	}


	public void setExchange(Exchange exchange) {
		this.exchange = exchange;
	}


	public LocalDate getCreatedOn() {
		return createdOn;
	}


	public void setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
	}
    
    
}
