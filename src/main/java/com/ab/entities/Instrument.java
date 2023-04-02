package com.ab.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "instrument")
public class Instrument 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "instrument_id")
    private int instrumentId;
    
    @Column(name = "symbol")
    private String symbol;
    
    @Column(name = "name")
    private String instrumentName;
    
    @Column(name = "exchange")
    private String exchange;

	public Instrument() {
		super();
	}


	public Instrument(int instrumentId, String symbol, String instrumentName, String exchange) {
		super();
		this.instrumentId = instrumentId;
		this.symbol = symbol;
		this.instrumentName = instrumentName;
		this.exchange = exchange;
	}

	
	public int getInstrumentId() {
		return instrumentId;
	}

	public void setInstrumentId(int instrumentId) {
		this.instrumentId = instrumentId;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getInstrumentName() {
		return instrumentName;
	}

	public void setName(String instrumentName) {
		this.instrumentName = instrumentName;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
    
    
    
    
}
