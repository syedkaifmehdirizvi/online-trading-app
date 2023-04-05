package com.ab.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ab.entities.Order;
import com.ab.entities.User;


@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>
{
	@Query("SELECT o FROM Order o WHERE o.status IN ('Partially Filled', 'Fully Filled')")
    List<Order> findFilledOrders();
	
	public List<Order> findAllByOrderType(String orderType);
	
	@Query("FROM Instrument i WHERE i.instrumentName = :instrumentName") 
	public List<Order> findByInstrumentName(@Param("instrumentName") String instrumentName);
	
	@Query("FROM Order i WHERE i.instrument.symbol = :symbol AND i.orderType != :orderType AND i.price = :price AND i.quantity >= :quantity AND i.status != 'FILLED'")
	public List<Order> findMatchingOrders(@Param("symbol") String symbol, @Param("orderType") String orderType, @Param("price") double price, @Param("quantity") int quantity);

	
	@Query("FROM Order i WHERE i.status != 'FILLED'")
	public List<Order> findOrderByStatus();
	
	List<Order> findByUser(User user);


}
