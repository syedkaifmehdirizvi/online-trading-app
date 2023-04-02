<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
</head>
<body>


<!-- Users should only be able to see add order/cancel order/replace order/logout/trade history if they are logged in -->
<!-- Consider creating a user account page which shows pending orders + trade history -->
<!-- Cancel order does not need to be it's own page. We are just using a button to execute a method -->
<!-- What is filledOrders.jsp page for? is this needed, filled orders will be shown as trades on trade history -->
<!-- Add information to home page to make it more user friendly: welcome message/explain the site/how it works -->
<!-- Create css file for styling + bootstrap + thymeleaf -->


	<a href="/register">Register</a>
	<a href="/login">Login</a>
	<a href="/orders">Orders</a>
	
	
</body>
</html>