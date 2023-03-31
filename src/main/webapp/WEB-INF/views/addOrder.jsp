<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Order</title>
</head>
<body>
	<h1>Add Order</h1>
    <form action="/orders/add" method="post">
        <br>
        <label for="instrumentId">Instrument:</label>
        <select name="instrumentId" required>
            <c:forEach items="${instruments}" var="instrument">
                <option value="${instrument.instrumentId}">${instrument.name}</option>
            </c:forEach>
        </select>
        <br>
        <label for="orderType">Order Type:</label>
        <input type="text" name="orderType" required>
        <br>
        <label for="price">Price:</label>
        <input type="number" name="price" step="0.01" required>
        <br>
        <label for="quantity">Quantity:</label>
        <input type="number" name="quantity" required>
        <br>
		
        <input type="submit" value="Add Order">
    </form>
</body>
</html>