<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Trading History</title>
</head>
<body>
<header>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="#"></a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarText">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/orders">Orders</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/profile">Profile</a>
        </li>
        <li class="nav-item">
		    <a class="nav-link" href="${sessionScope.containsKey('user') ? '/logout' : '/login'}">
		        ${sessionScope.containsKey('user') ? 'Logout' : 'Login'}
		    </a>
		</li>
       
      </ul>
      <span class="navbar-text">
       ${teamName}
      </span>
    </div>
  </div>
</nav>
</header>

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
