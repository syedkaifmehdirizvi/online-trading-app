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
    
    public Order addOrder(Integer userId, Integer instrumentId, String orderType, double price, Integer quantity, String status) 
    {
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
    
    public Order cancelOrder(Integer orderId) 
    {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            updateOrderStatus(orderId, "Cancelled");
            return orderRepository.save(order);
        } 
        else 
        {
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
            updateOrderStatus(orderId, "Replaced");
            
            return orderRepository.save(order);
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
 
    
}