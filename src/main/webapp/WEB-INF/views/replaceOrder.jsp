<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Replace Order</title>
</head>
<body>
    <h1>Replace Order</h1>
    <form action="/orders/replace/${order.orderId}" method="post">
        <input type="hidden" name="instrumentId" value="${order.instrument.instrumentId}">
        <br>
        <label for="instrument">Instrument:</label>
        <select name="instrument" disabled>
            <c:forEach items="${instruments}" var="instrument">
                <option value="${instrument.instrumentId}" <c:if test="${instrument.instrumentId == order.instrument.instrumentId}">selected</c:if>>${instrument.instrumentName}</option>
            </c:forEach>
        </select>
        <br>
        <label for="orderType">Order Type:</label>
        <input type="text" name="orderType" value="${order.orderType}" required>
        <br>
        <label for="price">Price:</label>
        <input type="number" name="price" step="0.01" value="${order.price}" required>
        <br>
        <label for="quantity">Quantity:</label>
        <input type="number" name="quantity" value="${order.quantity}" required>
        <br>
		
        <input type="submit" value="Replace Order">
    </form>
</body>
</html>