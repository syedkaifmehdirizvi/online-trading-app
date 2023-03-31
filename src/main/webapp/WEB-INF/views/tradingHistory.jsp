<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Trading History</title>
</head>
<body>

    <h1>Trading History</h1>
    
    <table>
    <tr>
    <th>Trade ID</th>    
    <th>Order ID</th>
    <!-- Add instrument and User here -->
    <th>Trade Type</th>
    <th>Price</th>
    <th>Quantity</th>
    <th>Created On</th>
    </tr>
    
    <c:forEach var="trade" items="${tradingHistory}">
    <tr>
    <td>${trade.tradeId}</td>
    <td>${trade.order.orderId}</td>
    <!-- Add instrument and User here -->
    <td>${trade.tradeType}</td>
    <td>${trade.price}</td>
    <td>${trade.quantity}</td>
    <td>${trade.createdOn}</td>
    </tr>
    </c:forEach>
    </table>

</body>
</html>