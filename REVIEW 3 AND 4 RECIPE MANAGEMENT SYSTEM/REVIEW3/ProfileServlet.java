package com.example.usermanagement.controller;

import com.example.usermanagement.dao.UserDAO;
import com.example.usermanagement.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        userDAO = new UserDAO();
    }

    // Display user profile
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the logged-in user's username (from session)
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if (username != null) {
            // Fetch user details from the database
            User user = userDAO.getUserByUsername(username);

            // Set user object as request attribute
            request.setAttribute("user", user);

            // Forward to profile.jsp
            request.getRequestDispatcher("/profile.jsp").forward(request, response);
        } else {
            // Redirect to login page if user is not logged in
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}
