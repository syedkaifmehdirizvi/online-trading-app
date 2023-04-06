package com.ab.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ab.entities.Instrument;
import com.ab.entities.Order;
import com.ab.entities.Trade;
import com.ab.entities.User;
import com.ab.repositories.InstrumentRepository;
import com.ab.repositories.OrderRepository;
import com.ab.repositories.TradeRepository;
import com.ab.repositories.UserRepository;

@Service
public class OrderService 
{
	@Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private TradeRepository tradeRepository;
    
    @Autowired
    private InstrumentRepository instrumentRepository;
    
    
    public List<Order> getOrderByStatus() {
        return orderRepository.findOrderByStatus();
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
        order.setOrderType(orderType.toUpperCase());
        order.setPrice(price);
        order.setQuantity(quantity);
        order.setStatus("OPEN");
        order.setCreatedOn(createdAt);

        // return orderRepository.save(order);
        Order createdOrder = orderRepository.save(order);
        findMatchingOrders(createdOrder);

        return createdOrder;
    }
    

    
    // rename to deleteOrder
    public void cancelOrder(Integer orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            orderRepository.delete(order);
        } else {
            throw new RuntimeException("Order not found");
        }
    }
    
    // rename to updateOrder
    public Order replaceOrder(Integer orderId, Integer newInstrumentId, String newOrderType, double newPrice, Integer newQuantity)
    {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            Instrument newInstrument = instrumentRepository.findById(newInstrumentId).orElseThrow(() -> new RuntimeException("Instrument not found"));
            
            order.setInstrument(newInstrument);
            order.setOrderType(newOrderType.toUpperCase());
            order.setPrice(newPrice);
            order.setQuantity(newQuantity);

            
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
    
    // what is this used for?
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
    
    // not needed as we will use trade orders to display filled orders to the user?
    public List<Order> getFilledOrders() 
    {
        return orderRepository.findFilledOrders();
    }
    
    public Optional<Order> getOrderById(Integer orderId) {
        return orderRepository.findById(orderId);
    }
 
	// find matching orders algo


    @Transactional
    public List<Trade> findMatchingOrders(Order order) {
        List<Order> matchingOrders = orderRepository.findMatchingOrders(
                order.getInstrument().getSymbol(), order.getOrderType(), order.getPrice(), order.getQuantity());

        List<Trade> trades = new ArrayList<>();

        // Process trades
        for (Order matchingOrder : matchingOrders) {
            if (matchingOrder.getQuantity() == order.getQuantity()) {
                Trade trade = createTrade(order, matchingOrder);
                tradeRepository.save(trade);
                trades.add(trade);
                
                order.setStatus("FILLED");
                orderRepository.save(order);
                
                matchingOrder.setStatus("FILLED");
                orderRepository.save(matchingOrder);

            } else if (matchingOrder.getQuantity() > order.getQuantity()) {
                Trade trade = createTrade(order, matchingOrder);
                tradeRepository.save(trade);
                trades.add(trade);

                // update matching order status and save
                matchingOrder.setQuantity(matchingOrder.getQuantity() - order.getQuantity());
                matchingOrder.setStatus("PARTIALLY FILLED");
                orderRepository.save(matchingOrder);

                // update buy order status and save
                order.setStatus("FILLED");
                orderRepository.save(order);

                break;
            } else {
                Trade trade = createTrade(order, matchingOrder);
                tradeRepository.save(trade);
                trades.add(trade);

                // update buy order quantity, status and save
                order.setQuantity(order.getQuantity() - matchingOrder.getQuantity());
                order.setStatus("PARTIALLY FILLED");
                orderRepository.save(order);

                // remove the sell order that fully matched
                matchingOrder.setStatus("FILLED");
                orderRepository.save(matchingOrder);
            }
        }

        return trades;
    }

    private Trade createTrade(Order order, Order matchingOrder) 
    {
        Trade trade = new Trade();
        trade.setOrder(order);
        trade.setInstrument(order.getInstrument());
        trade.setUser(order.getUser());
        trade.setTradeType(order.getOrderType());
        trade.setPrice(order.getPrice());
        trade.setQuantity(order.getQuantity());
        trade.setCreatedOn(LocalDate.now());
        return trade;
    }


	public List<Order> getOrdersForUser(User user) {
	    return orderRepository.findByUserAndStatusNot(user, "FILLED");
	}

}