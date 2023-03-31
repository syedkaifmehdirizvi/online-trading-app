package com.ab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ab.entities.Exchange;

public interface ExchangeRepository extends JpaRepository<Exchange, Integer>
{

}
