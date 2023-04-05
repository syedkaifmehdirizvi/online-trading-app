<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Buy Orders</title>
</head>
<body>
    <h1>Buy Orders</h1>
    <table>
        <tr>
            <th>Order ID</th>
            <th>Instrument</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Status</th>
        </tr>
        <c:forEach var="order" items="${buyOrders}">
            <tr>
                <td>${order.orderId}</td>
                <td>${order.instrument.instrumentName}</td>
                <td>${order.price}</td>
                <td>${order.quantity}</td>
                <td>${order.status}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>