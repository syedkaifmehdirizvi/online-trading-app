<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

   
  </head>
  <body>
<section class="vh-100 bg-image" style="background-image: url('https://images.unsplash.com/photo-1605792657660-596af9009e82?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1102&q=80');">
  <div class="mask d-flex align-items-center h-100 gradient-custom-3">
    <div class="container h-100">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-12 col-md-9 col-lg-7 col-xl-6">
          <div class="card" style="border-radius: 15px;">
            <div class="card-body p-5">
              <h2 class="text-uppercase text-center mb-5">Create an account</h2>

              <form action="/register" method="POST">

                <div class="form-outline mb-4">
                  <input type="text" name="fname" id="fname" class="form-control form-control-lg" required>
                  <label class="form-label" for="fname">First Name</label>
                </div>
                 <div class="form-outline mb-4">
                  <input type="text" name="lname" id="lname" class="form-control form-control-lg" required> 
                  <label class="form-label" for="lname">Last Name</label>
                </div>
                <div class="form-outline mb-4">
                  <input type="text" name="username" id="username" class="form-control form-control-lg" required>
                  <label class="form-label" for="form3Example1cg">Username</label>
                </div>

                <div class="form-outline mb-4">
                  <input type="text" name="email" id="email" class="form-control form-control-lg" required>
                  <label class="form-label" for="email"> Email</label>
                </div>

                <div class="form-outline mb-4">
                  <input type="text" name="password" id="password" class="form-control form-control-lg" required> 
                  <label class="form-label" for="form3Example4cg">Password</label>
                </div>

               

                <div class="d-flex justify-content-center">
                  <button type="submit" class="btn btn-primary btn-block btn-lg gradient-custom-4 text-white">Register</button>
                </div>

                <p class="text-center text-muted mt-5 mb-0">Have already an account? <a href="login" class="fw-bold text-body"><u>Login here</u></a></p>

              </form>
              <c:if test="${not empty message}">
        <p>${message}</p>
    </c:if>

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
 