<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
</head>
<body>
    <h1>Register</h1>
    <form action="/register" method="post">
    	<label for="fname">First Name: </label>
        <input type="text" name="fname" required>
        <br>
        <label for="lname">Last Name: </label>
        <input type="text" name="lname" required>
        <br>
        <label for="email">Email: </label>
        <input type="email" name="email" required>
        <br>
        <label for="username">Username:</label>
        <input type="text" name="username" required>
        <br>
        <label for="password">Password:</label>
        <input type="password" name="password" required>
        
        <input type="submit" value="Register">
        
    </form>
    <c:if test="${not empty message}">
        <p>${message}</p>
    </c:if>
</body>
</html>