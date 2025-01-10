package com.example.usermanagement.controller;

import com.example.usermanagement.dao.UserDAO;
import com.example.usermanagement.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        userDAO = new UserDAO();
    }

    // Display registration form
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }

    // Handle registration form submission
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        // Create a new User object and set the values
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);  // Note: In production, always hash the password!
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);

        // Call UserDAO to register the user
        userDAO.registerUser(user);

        // Redirect to login or profile page after registration
        response.sendRedirect(request.getContextPath() + "/login");
    }
}
