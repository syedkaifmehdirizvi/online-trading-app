<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="style.css">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
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
<section class="vh-100 bg-image card-section" style="background-image: url('https://images.unsplash.com/photo-1605792657660-596af9009e82?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1102&q=80');">
  <div class="mask d-flex align-items-center gradient-custom-3">
    <div class="container w-200 h-100">
      <div class="row d-flex justify-content-center align-items-center h-100 mt-5">
        <div class="col-12 col-md-9 col-lg-12 col-xl-6">
          <div class="card style="border-radius: 15px;">
            <div class="card-body">
	<h3 class="text-center">${user.firstName}'s Profile</h3>
    
    <!-- Orders Section -->
    <h5 class="text-center">Orders</h5>
    
    <div style="height: 300px; overflow-y: scroll;">
    
    
<table class="table">
    <thead>
        <tr>
            <th scope="col">Instrument Name</th>
            <th scope="col">Order Type</th>
            <th scope="col">Price</th>
            <th scope="col">Quantity</th>
            <th scope="col">Status</th>
            <th scope="col">Action</th>
        </tr>
    </thead>
  <tbody>
        <c:forEach var="order" items="${orders}">
            <tr>
                <td class="table-secondary">${order.instrument.instrumentName}</td>
                <td class="table-secondary">${order.orderType}</td>
                <td class="table-secondary">${order.price}</td>
                <td class="table-secondary">${order.quantity}</td>
                <td class="table-secondary">${order.status}</td>
                <td>
                    <form action="/orders/replace/${order.orderId}" method="get" style="display:inline;">
                        <button type="submit" class="btn btn-primary btn-block btn-sm gradient-custom-4 text-white">Update</button>
                    </form>
                    <form action="/orders/cancel/${order.orderId}" method="get" style="display:inline;">
                        <button class="btn btn-primary btn-block btn-sm gradient-custom-4 text-white" type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
  </table>
  

   </div>
       
    

    <!-- Trading History Section -->
    <h5 class="text-center">Trading History</h5>
    
    <div style="height: 100px; overflow-y: scroll;">
    
    <table>
        <tr>
            <th>Trade ID</th>
            <th>Order ID</th>
            <th>Trade Type</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Created On</th>
        </tr>
        
     </thread>
  <tbody>
        
        <c:forEach var="trade" items="${tradingHistory}">
            <tr>
                <td>${trade.tradeId}</td>
                <td>${trade.order.orderId}</td>
                <td>${trade.tradeType}</td>
                <td>${trade.price}</td>
                <td>${trade.quantity}</td>
                <td>${trade.createdOn}</td> 
            </tr>
            </tbody>
        </c:forEach>
        
        
    </table>
    
    </div>
    
    <div class="text-center">
    	<a class="btn btn-primary btn-block btn-lg mt-5 gradient-custom-4 text-white" href="/orders/add">Add Order</a>
    	</div>
      </div>
      
          </div>
        </div>
      </div>
    </div>
  </div>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</section>

  
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
 
</body>
</html>
