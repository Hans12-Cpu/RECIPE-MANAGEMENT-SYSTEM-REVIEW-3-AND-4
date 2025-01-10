<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
            max-width: 800px;
            margin: 50px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            color: #333;
        }

        .profile-details {
            margin: 20px 0;
        }

        .profile-details div {
            margin-bottom: 10px;
        }

        .profile-details label {
            font-weight: bold;
        }

        .profile-details span {
            font-size: 1.1em;
            color: #555;
        }

        .btn {
            display: inline-block;
            background-color: #4CAF50;
            color: #fff;
            padding: 10px 20px;
            text-align: center;
            border-radius: 5px;
            text-decoration: none;
            font-weight: bold;
        }

        .btn:hover {
            background-color: #45a049;
        }

        .back-btn {
            background-color: #f44336;
        }

        .back-btn:hover {
            background-color: #e53935;
        }
    </style>
</head>
<body>

    <div class="container">
        <h1>User Profile</h1>

        <!-- Check if the user object is available -->
        <c:if test="${not empty user}">
            <div class="profile-details">
                <div>
                    <label for="username">Username:</label>
                    <span id="username">${user.username}</span>
                </div>
                <div>
                    <label for="firstName">First Name:</label>
                    <span id="firstName">${user.firstName}</span>
                </div>
                <div>
                    <label for="lastName">Last Name:</label>
                    <span id="lastName">${user.lastName}</span>
                </div>
                <div>
                    <label for="email">Email:</label>
                    <span id="email">${user.email}</span>
                </div>
            </div>

            <!-- Button to logout or go back -->
            <div class="text-center">
                <a href="logout" class="btn">Logout</a>
                <a href="updateProfile.jsp" class="btn">Update Profile</a>
                <a href="home.jsp" class="btn back-btn">Back to Home</a>
            </div>
        </c:if>

        <!-- If no user data available -->
        <c:if test="${empty user}">
            <div class="alert">
                <p>User not found or not logged in. Please <a href="login.jsp">login</a> first.</p>
            </div>
        </c:if>
    </div>

</body>
</html>
