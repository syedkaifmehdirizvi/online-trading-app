<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
    <h1>Login</h1>
    <form action="/login" method="post">
        <label for="username">Username:</label>
        <input type="text" name="username" required>
        <br>
        <label for="password">Password:</label>                                  
        <input type="password" name="password" required>                       
        <br>
        <input type="submit" value="Login">
    </form>
    <c:if test="${not empty error}">
        <p>${error}</p>
    </c:if>
    <p>Don't have an account? <a href="/register">Register here</a></p>
</body>
</html>