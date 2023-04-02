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
    <th>Order ID</th>    
    <th>Order Type</th>
    <th>Price</th>
    <th>Quantity</th>
    <th>Status</th>
    <th>Created On</th>
    <th>Action</th>
    </tr>
    
    <c:forEach var="order" items="${orders}">
    <tr>
    <td>${order.orderId}</td>
    <td>${order.orderType}</td>
    <td>${order.price}</td>
    <td>${order.quantity}</td>
    <td>${order.status}</td>
    <td>${order.createdOn}</td>
    <td>
        <form action="/orders/replace/${order.orderId}" method="get">
            <button type="submit">Replace</button>
        </form>
        <form action="/orders/cancel/${order.orderId}" method="get">
            <button type="submit">Cancel</button>
        </form>
    </td>
    </tr>
    </c:forEach>
    </table>
    
    <a href="/orders/add">Add Order</a>
    
    <a href="/"><button>Return</button></a>

</body>
</html>