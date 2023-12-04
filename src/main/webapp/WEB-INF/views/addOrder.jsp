<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Add Order</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
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
	<div class="container">
        <h1 class="mt-4 mb-4">Add Order</h1>

		<form action="/orders/add" method="post">
			<label for="instrumentId">Instrument:</label>
			<select name="instrumentId" required>
				<c:forEach items="${instruments}" var="instrument">
					<option value="${instrument.instrumentId}">${instrument.symbol} - ${instrument.instrumentName} (${instrument.exchange})</option>>
				</c:forEach>
			</select>
			<br>
			<label for="orderType">Order Type:</label>
			<input type="radio" name="orderType" value="buy" required>Buy
			<input type="radio" name="orderType" value="sell" required>Sell
			<br>
			<label for="price">Price:</label>
			<input type="number" name="price" min="0" step="0.01" required>
			<br>
			<label for="quantity">Quantity:</label>
			<input type="number" name="quantity" min="1" required>
			<br>
			<input type="submit" value="Add Order">
		</form>
    </div>
	
	<script src="https://code.jquery.com/jquery-3.6.0.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
</body>
</html>
