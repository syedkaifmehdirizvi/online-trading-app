//package com.ab;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.ab.controllers.OrderController;
//import com.ab.entities.Instrument;
//import com.ab.entities.Order;
//import com.ab.entities.Trade;
//import com.ab.entities.User;
//import com.ab.repositories.OrderRepository;
//import com.ab.services.OrderService;
//
//@SpringBootTest
//public class OrderTest {
//	
//	@Mock
//	private OrderService orderServiceMock;
//	
//	@InjectMocks
//	private OrderController orderControllerMock;
//	
//	@Mock
//	private OrderRepository orderRepositoryMock;
//	
//	@Test
//	public void testOrderCreation() {
//		User user = new User(1, "Bob", "Jones", "bob@email.com");
//		Instrument instrument = new Instrument(1, "AAPL", "Apple", "Technology");
//		Order order = new Order(1, user, instrument, "BUY", 150.00, 150, "OPEN", LocalDate.now());
//		
//        assertEquals(1, order.getOrderId());
//        assertEquals(user, order.getUser());
//        assertEquals(instrument, order.getInstrument());
//        assertEquals("BUY", order.getOrderType());
//        assertEquals(150.00, order.getPrice());
//        assertEquals(150, order.getQuantity());
//        assertEquals("OPEN", order.getStatus());
//        assertEquals(LocalDate.now(), order.getCreatedOn());
//	}
//	
//	@Test
//	public void testGetAllBuyOrders() {
//	    User user = new User(1, "Bob", "Jones", "bob@email.com");
//	    Instrument instrument = new Instrument(1, "AAPL", "Apple", "Technology");
//
//	    List<Order> buyOrders = new ArrayList<>();
//	    buyOrders.add(new Order(1, user, instrument, "BUY", 10.0, 100, "OPEN", LocalDate.now()));
//	    buyOrders.add(new Order(2, user, instrument, "BUY", 50.0, 20, "OPEN", LocalDate.now()));
//
//
//	    when(orderServiceMock.getAllBuyOrders()).thenReturn(buyOrders);
//
//	    ModelAndView mav = orderControllerMock.getAllBuyOrders();
//	    List<Order> actualOrders = (List<Order>) mav.getModel().get("buyOrders");
//
//	    assertEquals(buyOrders, actualOrders);
//	}
//	
//	@Test
//	public void testGetAllSellOrders() {
//	    User user = new User(1, "Bob", "Jones", "bob@email.com");
//	    Instrument instrument = new Instrument(1, "AAPL", "Apple", "Technology");
//
//	    List<Order> sellOrders = new ArrayList<>();
//	    sellOrders.add(new Order(1, user, instrument, "SELL", 10.0, 100, "OPEN", LocalDate.now()));
//	    sellOrders.add(new Order(2, user, instrument, "SELL", 50.0, 20, "OPEN", LocalDate.now()));
//
//
//	    when(orderServiceMock.getAllSellOrders()).thenReturn(sellOrders);
//
//	    ModelAndView mav = orderControllerMock.getAllSellOrders();
//	    List<Order> actualOrders = (List<Order>) mav.getModel().get("sellOrders");
//	    
//	    assertEquals(sellOrders, actualOrders);
//	}
//	
//	@Test
//	public void testGetBuyOrdersByInstrumentName() {
//		User user = new User(1, "Bob", "Jones", "bob@email.com");
//	    Instrument instrument1 = new Instrument(1, "AAPL", "Apple", "Technology");
//	    Instrument instrument2 = new Instrument(2, "GOOGL", "Google", "Technology");
//	    
//	    List<Order> searchByOrders = new ArrayList<>();
//	    searchByOrders.add(new Order(1, user, instrument1, "BUY", 15.0, 513, "OPEN", LocalDate.now()));
//	    searchByOrders.add(new Order(2, user, instrument2, "BUY", 123, 24235, "OPEN", LocalDate.now()));
//	    
//	    when(orderServiceMock.getByInstrumentName("AAPL")).thenReturn(searchByOrders);
//	    
//
//	    ModelAndView mav = orderControllerMock.searchBuyByInstrumentName("AAPL");
//	    
//	    verify(orderServiceMock).getByInstrumentName("AAPL");
//	    
//	    List<Order> buyOrderSearch = (List<Order>) mav.getModel().get("buyOrderSearch");
//	    assertNotNull(buyOrderSearch);
//	    assertEquals(searchByOrders, buyOrderSearch);
//	    
//	    assertEquals("buy-search-results", mav.getViewName());
//	}
//	
//	@Test
//	public void testFindMatchingOrders() {
//	    User user = new User(1, "Bob", "Jones", "bob@email.com");
//	    Instrument instrument = new Instrument(1, "AAPL", "Apple", "Technology");
//
//	    Order sellOrder = new Order(1, user, instrument, "SELL", 100.0, 200, "OPEN", LocalDate.now());
//	    Order buyOrder = new Order(2, user, instrument, "BUY", 100.0, 150, "OPEN", LocalDate.now());
//
//	    sellOrder = orderRepositoryMock.save(sellOrder);
//	    buyOrder = orderRepositoryMock.save(buyOrder);
//
//	    // Call the findMatchingOrders method
//	    List<Trade> matchingOrders = orderServiceMock.findMatchingOrders(buyOrder);
//
//	    // Check that the orders were matched and processed correctly
//	    List<Order> expectedMatchingOrders = new ArrayList<>();
//	    expectedMatchingOrders.add(sellOrder); // add the sellOrder to the list
//
//	    // Check that the matching orders are as expected
//	    assertEquals(expectedMatchingOrders, matchingOrders);
//
//	    Optional<Order> removedBuyOrder = orderRepositoryMock.findById(buyOrder.getOrderId());
//	    assertFalse(removedBuyOrder.isPresent());
//
//	    // Check that the sell order was updated with the correct quantity
//	    Optional<Order> updatedSellOrder = orderRepositoryMock.findById(sellOrder.getOrderId());
//	    assertTrue(updatedSellOrder.isPresent());
//	    assertEquals(50, updatedSellOrder.get().getQuantity());
//	}
//}