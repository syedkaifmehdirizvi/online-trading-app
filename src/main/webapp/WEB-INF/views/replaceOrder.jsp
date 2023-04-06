<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
          <a class="nav-link" href="/orders">Trading History</a>
        </li>
      </ul>
      <span class="navbar-text">
       The Trade Nation
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

</div>
          </div>
        </div>
      </div>
    </div>
  </div>
 </section>
 </body>
</html>