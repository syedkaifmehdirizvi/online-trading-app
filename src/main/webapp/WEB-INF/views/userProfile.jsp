<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profile</title>
</head>
<body>
	<h1>${user.firstName}'s Profile</h1>
    
    <!-- Orders Section -->
    <h2>Orders</h2>
    <table>
        <tr>
            <th>Instrument Name</th>
            <th>Order Type</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
        
        <c:forEach var="order" items="${orders}">
            <tr>
                <td>${order.instrument.instrumentName}</td>
                <td>${order.orderType}</td>
                <td>${order.price}</td>
                <td>${order.quantity}</td>
                <td>${order.status}</td>
                <td>
                    <form action="/orders/replace/${order.orderId}" method="get" style="display:inline;">
                        <button type="submit">Update</button>
                    </form>
                    <form action="/orders/cancel/${order.orderId}" method="get" style="display:inline;">
                        <button type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="/orders/add">Add Order</a>

    <!-- Trading History Section -->
    <h2>Trading History</h2>
    <table>
        <tr>
            <th>Trade ID</th>
            <th>Order ID</th>
            <th>Trade Type</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Created On</th>
        </tr>
        
        <c:forEach var="trade" items="${tradingHistory}">
            <tr>
                <td>${trade.tradeId}</td>
                <td>${trade.order.orderId}</td>
                <td>${trade.tradeType}</td>
                <td>${trade.price}</td>
                <td>${trade.quantity}</td>
                <td>${trade.createdOn}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>