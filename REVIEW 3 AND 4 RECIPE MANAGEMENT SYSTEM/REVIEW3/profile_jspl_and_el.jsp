<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 60%;
            margin: 50px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
        }
        .user-info {
            margin: 20px;
        }
        .user-info p {
            font-size: 18px;
        }
        .user-info p strong {
            font-weight: bold;
        }
        .back-link {
            display: inline-block;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            margin-top: 20px;
        }
        .back-link:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

    <div class="container">
        <h1>User Profile</h1>

        <!-- Using JSTL c:if to check if the user object is available -->
        <c:if test="${not empty user}">
            <div class="user-info">
                <p><strong>Username:</strong> ${user.username}</p>
                <p><strong>First Name:</strong> ${user.firstName}</p>
                <p><strong>Last Name:</strong> ${user.lastName}</p>
                <p><strong>Email:</strong> ${user.email}</p>
            </div>
        </c:if>

        <!-- Display a message if no user data is found -->
        <c:if test="${empty user}">
            <p>No user data available. Please log in.</p>
        </c:if>

        <!-- Link back to home page -->
        <a href="index.jsp" class="back-link">Back to Home</a>
    </div>

</body>
</html>
