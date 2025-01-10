package servlet;

import service.UserService;
import model.User;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class UserServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        Connection connection = (Connection) getServletContext().getAttribute("DBConnection");
        userService = new UserService(connection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Display registration or login form
        String action = request.getParameter("action");
        if ("register".equals(action)) {
            request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
        } else if ("login".equals(action)) {
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("register".equals(action)) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String fullName = request.getParameter("fullName");

            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setFullName(fullName);

            try {
                boolean isRegistered = userService.registerUser(user);
                if (isRegistered) {
                    response.sendRedirect("login");
                } else {
                    response.getWriter().println("Registration failed");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.getWriter().println("Database error");
            }
        } else if ("login".equals(action)) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            try {
                User user = userService.loginUser(username, password);
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                    response.sendRedirect("profile");
                } else {
                    response.getWriter().println("Invalid login credentials");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.getWriter().println("Database error");
            }
        }
    }
}
