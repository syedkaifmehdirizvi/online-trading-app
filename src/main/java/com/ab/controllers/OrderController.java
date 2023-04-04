package com.ab.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ab.entities.Instrument;
import com.ab.entities.Order;
import com.ab.entities.User;
import com.ab.services.InstrumentService;
import com.ab.services.OrderService;
import com.ab.services.UserService;


@Controller
public class OrderController 
{
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private InstrumentService instrumentService;
    
    
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String showOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "orders";
    }
    
    // View buy orders
	@GetMapping("/orders/buy")
	public ModelAndView getAllBuyOrders() {
		ModelAndView mav = new ModelAndView("buyOrders");
		List<Order> buyOrders = orderService.getAllBuyOrders();
		mav.addObject("buyOrders", buyOrders);
		return mav;
	}
	
	// Search buy orders by instrument name
    @GetMapping("/orders/search") 
    public ModelAndView searchBuyByInstrumentName(@RequestParam("instrumentName") String instrumentName) { 
	    List<Order> buyOrderSearch = orderService.getByInstrumentName (instrumentName); 
	    // change the view from buy-search-results to the correct jsp page
	    ModelAndView mav = new ModelAndView("buy-search-results"); 
	    mav.addObject("buyOrderSearch", buyOrderSearch); 
	    return mav; 
    }

	// View sell orders
	@GetMapping("/orders/sell")
	public ModelAndView getAllSellOrders() {
		ModelAndView mav = new ModelAndView("sellOrders");
		List<Order> sellOrders = orderService.getAllSellOrders();
		mav.addObject("sellOrders", sellOrders);
		return mav;
	}
	
	// Search sell orders by instrument name
	@GetMapping("orders/sell/search") 
	public ModelAndView searchSellByInstrumentName(@RequestParam("instrumentName") String instrumentName) { 
		List<Order> sellOrderSearch = orderService.getByInstrumentName(instrumentName);
		// change the view from sell-search-results to the correct jsp page
		ModelAndView mav = new ModelAndView("sell-search-results"); 
		mav.addObject("sellOrderSearch", sellOrderSearch); 
		return mav; 
	}
	

 // Display the form for adding a new order
    @GetMapping("/orders/add")
    public String showAddOrderForm(Model model) {
        // Add necessary data to the model
        List<Instrument> instruments = instrumentService.getAllInstruments();
        model.addAttribute("instruments", instruments);
        model.addAttribute("order", new Order());
        return "addOrder";
    }

 // Add a new order to the system
    @PostMapping("/orders/add")
    public String addOrder(@ModelAttribute("order") Order order,
                            @RequestParam("instrumentId") int instrumentId,
                            HttpSession session) {
        // Get the logged-in user from the session
        User loggedInUser = (User) session.getAttribute("user");
        order.setUser(loggedInUser);

        // Check if the loggedInUser is not null
        if (loggedInUser == null) {
            return "redirect:/login"; // Redirect to login page if the user is not logged in
        }

        // Set the user and the Instrument object for the Order
        order.setUser(loggedInUser);
        
        // Set the Instrument object for the Order
        Instrument instrument = instrumentService.getInstrumentById(instrumentId);
        order.setInstrument(instrument);

        orderService.createOrder(order.getUser().getUserId(), order.getInstrument().getInstrumentId(), order.getOrderType(), order.getPrice(), order.getQuantity(), order.getStatus());
        return "redirect:/profile";
    }

    
    
    @GetMapping("/orders/replace/{orderId}")
    public String showReplaceOrderForm(
            @PathVariable("orderId") Integer orderId, Model model) 
    {
    	Order order = orderService.getOrderById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        // Add necessary data to the model
        List<User> users = userService.getAllUsers();
        List<Instrument> instruments = instrumentService.getAllInstruments();
        model.addAttribute("users", users);
        model.addAttribute("instruments", instruments);
        model.addAttribute("order", order);
        return "replaceOrder";
    }

    @PostMapping("/orders/replace/{orderId}")
    public String replaceOrder(
            @PathVariable("orderId") Integer orderId,
            @ModelAttribute("order") Order order,
            @RequestParam("instrumentId") int instrumentId) {
        Instrument instrument = instrumentService.getInstrumentById(instrumentId);
        order.setInstrument(instrument);
        orderService.replaceOrder(orderId, order.getInstrument().getInstrumentId(), order.getOrderType(), order.getPrice(), order.getQuantity());
        return "redirect:/orders";
    }

    @GetMapping("/orders/cancel/{orderId}")
    public String cancelOrder(@PathVariable("orderId") Integer orderId) {
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }

    @GetMapping("/orders/filled")
    public String showFilledOrders(Model model) 
    {
        List<Order> orders = orderService.getFilledOrders();
        // Add necessary data to the model
        model.addAttribute("orders", orders);
        return "filledOrders";
    }
}
