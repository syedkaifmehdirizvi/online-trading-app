package com.ab.services;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ab.entities.Instrument;
import com.ab.repositories.InstrumentRepository;

@Service
public class InstrumentService 
{
	 @Autowired
	    private InstrumentRepository instrumentRepository;

	    public List<Instrument> getAllInstruments() {
	        return instrumentRepository.findAll();
	    }

	    public Optional<Instrument> getInstrumentById(Integer instrumentId) {
	        return instrumentRepository.findById(instrumentId);
	    }
}
