<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Orders</title>
</head>
<body>

    <h1>Orders</h1>
    
    <table>
    <tr>
    <th>Instrument Name</th>
    <th>Order Type</th>
    <th>Price</th>
    <th>Quantity</th>
    <th>Status</th>
    </tr>
    
    <c:forEach var="order" items="${orders}">
    <tr>
    <td>${order.instrument.instrumentName}</td>
    <td>${order.orderType}</td>
    <td>${order.price}</td>
    <td>${order.quantity}</td>
    <td>${order.status}</td>
    <td>
        
    </td>
    </tr>
    </c:forEach>
    </table>
    
    <a href="/orders/add">Add Order</a>

</body>
</html>