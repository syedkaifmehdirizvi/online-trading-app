<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Filled Orders</title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
    </style>
</head>
<body>

 <h1>Filled Orders</h1>
    <table>
        <thead>
            <tr>
                <th>Order ID</th>
                <th>User</th>
                <th>Instrument</th>
                <th>Type</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Status</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${orders}" var="order">
                <tr>
                    <td>${order.orderId}</td>
                    <td>${order.user.username}</td>
                    <td>${order.instrument.name}</td>
                    <td>${order.orderType}</td>
                    <td>${order.price}</td>
                    <td>${order.quantity}</td>
                    <td>${order.status}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>