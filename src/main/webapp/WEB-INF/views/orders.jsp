<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Orders</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

    <div class="container">
        <h1 class="mt-4 mb-4">Orders</h1>
        
        <table class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th>Instrument Name</th>
                    <th>Order Type</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="order" items="${orders}">
                <tr>
                    <td>${order.instrument.instrumentName}</td>
                    <td>${order.orderType}</td>
                    <td>${order.price}</td>
                    <td>${order.quantity}</td>
                    <td>${order.status}</td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <a href="/orders/add" class="btn btn-primary">Add Order</a>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
</body>
</html>