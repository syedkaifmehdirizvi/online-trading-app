package com.ab.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

	@GetMapping("/orders/add")
	public String showAddOrderForm(Model model) 
	{
        // Add necessary data to the model
        List<User> users = userService.getAllUsers();
        List<Instrument> instruments = instrumentService.getAllInstruments();
        model.addAttribute("users", users);
        model.addAttribute("instruments", instruments);
        model.addAttribute("order", new Order());
        return "addOrder";
    }

    @PostMapping("/orders/add")
    public String addOrder(
            @ModelAttribute("order") Order order) 
    {
        orderService.addOrder(order.getUser().getUserId(), order.getInstrument().getInstrumentId(), order.getOrderType(), order.getPrice(), order.getQuantity(), order.getStatus());
        return "redirect:/orders";
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
            @ModelAttribute("order") Order order) 
    {
        orderService.replaceOrder(orderId, order.getInstrument().getInstrumentId(), order.getOrderType(), order.getPrice(), order.getQuantity());
        return "redirect:/orders";
    }

    @GetMapping("/orders/cancel/{orderId}")
    public String cancelOrder(
            @PathVariable("orderId") Integer orderId) 
    {
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
