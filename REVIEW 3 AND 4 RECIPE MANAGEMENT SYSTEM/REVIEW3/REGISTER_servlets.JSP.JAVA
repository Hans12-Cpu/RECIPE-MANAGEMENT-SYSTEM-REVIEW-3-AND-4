package com.example.servlets;

import com.example.models.User;
import com.example.dao.UserDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        userDAO = new UserDAO(); // Assume DAO handles DB interactions
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward the request to the registration page (JSP)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/register.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the form parameters from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        // Create a new User object
        User user = new User(username, password, firstName, lastName, email);

        // Register the user (insert into the database)
        boolean registrationSuccess = userDAO.registerUser(user);

        if (registrationSuccess) {
            // If registration is successful, forward to profile page
            request.setAttribute("user", user);  // Pass the user object to the JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("/profile.jsp");
            dispatcher.forward(request, response);
        } else {
            // If registration fails, set an error message and forward back to register.jsp
            request.setAttribute("errorMessage", "Registration failed. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/register.jsp");
            dispatcher.forward(request, response);
        }
    }
}
