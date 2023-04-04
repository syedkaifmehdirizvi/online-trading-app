package com.ab.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ab.entities.Instrument;
import com.ab.entities.Order;
import com.ab.entities.User;
import com.ab.repositories.InstrumentRepository;
import com.ab.repositories.OrderRepository;
import com.ab.repositories.UserRepository;

@Service
public class OrderService 
{
	@Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private InstrumentRepository instrumentRepository;
    
    
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    
    // get all orders by BUY order type
	public List<Order> getAllBuyOrders(){
		return orderRepository.findAllByOrderType("BUY");
	}
	
	// get all orders by SELL order type
	public List<Order> getAllSellOrders(){
		return orderRepository.findAllByOrderType("SELL");
	}
	
	// get orders by instrument name
	public List<Order> getByInstrumentName(String instrumentName){ 
		return orderRepository.findByInstrumentName(instrumentName); 
	}

    public Order createOrder(Integer userId, Integer instrumentId, String orderType, double price, Integer quantity, String status) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Instrument instrument = instrumentRepository.findById(instrumentId).orElseThrow(() -> new RuntimeException("Instrument not found"));
        LocalDate createdAt = LocalDate.now();

        Order order = new Order();
        order.setUser(user);
        order.setInstrument(instrument);
        order.setOrderType(orderType);
        order.setPrice(price);
        order.setQuantity(quantity);
        order.setStatus(status);
        order.setCreatedOn(createdAt);

        return orderRepository.save(order);
    }
    
    
    
//    public Order addOrder(Integer instrumentId, String orderType, double price, Integer quantity, String status) 
//    {
//        //User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//        Instrument instrument = instrumentRepository.findById(instrumentId).orElseThrow(() -> new RuntimeException("Instrument not found"));
//        LocalDate createdAt = LocalDate.now();
//        
//        Order order = new Order();
//        //order.setUser(user);
//        order.setInstrument(instrument);
//        order.setOrderType(orderType);
//        order.setPrice(price);
//        order.setQuantity(quantity);
//        order.setStatus("OPEN");
//        order.setCreatedOn(createdAt);
//        
//        // return orderRepository.save(order);
//        
//        Order savedOrder = orderRepository.save(order);
//        findMatchingOrders(savedOrder);
//
//        return savedOrder;
//    }
    
    public void cancelOrder(Integer orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            orderRepository.delete(order);
        } else {
            throw new RuntimeException("Order not found");
        }
    }
    
    public Order replaceOrder(Integer orderId, Integer newInstrumentId, String newOrderType, double newPrice, Integer newQuantity)
    {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            Instrument newInstrument = instrumentRepository.findById(newInstrumentId).orElseThrow(() -> new RuntimeException("Instrument not found"));
            
            order.setInstrument(newInstrument);
            order.setOrderType(newOrderType);
            order.setPrice(newPrice);
            order.setQuantity(newQuantity);
            // Removed the line that updates the status to "Replaced"
            
            //return orderRepository.save(order);
            
            Order updatedOrder = orderRepository.save(order);
            findMatchingOrders(updatedOrder);
            
            return updatedOrder;
        } 
        else 
        {
            throw new RuntimeException("Order not found");
        }
    }
    
    public void updateOrderStatus(Integer orderId, String status) 
    {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setStatus(status);
            orderRepository.save(order);
        } 
        else 
        {
            throw new RuntimeException("Order not found");
        }
        
    }
    
    public List<Order> getFilledOrders() 
    {
        return orderRepository.findFilledOrders();
    }
    
    public Optional<Order> getOrderById(Integer orderId) {
        return orderRepository.findById(orderId);
    }
 
	// find matching orders algo
	public List<Order> findMatchingOrders(Order order){
		List<Order> matchingOrders = orderRepository.findMatchingOrders(
				order.getInstrument().getSymbol(), order.getOrderType(), order.getPrice(), order.getQuantity());
		
		// Process trades
		for (Order matchingOrder : matchingOrders) {
			if(matchingOrder.getQuantity() == order.getQuantity()) {
				// remove orders if full match
				orderRepository.delete(matchingOrder);
				orderRepository.delete(order);
			} else if (matchingOrder.getQuantity() > order.getQuantity()) {
				// update matching order quantity and save
				matchingOrder.setQuantity(matchingOrder.getQuantity() - order.getQuantity());
				matchingOrder.setStatus("PARTIALLY FILLED");
				orderRepository.save(matchingOrder);
				
				// remove the buy order that fully matched
				orderRepository.delete(order);
			} else {
				// update the buy order quantity and save
				order.setQuantity(order.getQuantity() - matchingOrder.getQuantity());
				orderRepository.save(order);
				
				// remove the sell order that fully matched
				orderRepository.delete(matchingOrder);
			}
		}
		return matchingOrders;
	}

	public List<Order> getOrdersForUser(User user) {
	    return orderRepository.findByUser(user);
	}
	
}