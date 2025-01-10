package dao;

import model.User;
import java.sql.*;

public class UserDAO {
    private Connection connection; // Database connection object

    // Constructor to initialize the connection
    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    // Method to register a new user in the database
    public boolean registerUser(User user) throws SQLException {
        // SQL query to insert the new user's details into the users table
        String query = "INSERT INTO users (username, password, email, full_name) VALUES (?, ?, ?, ?)";
        
        // Prepare the statement to execute the query
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            // Set the values from the User object to the PreparedStatement
            ps.setString(1, user.getUsername()); // Username
            ps.setString(2, user.getPassword()); // Password (Should be hashed in real-world apps)
            ps.setString(3, user.getEmail());    // Email
            ps.setString(4, user.getFullName()); // Full Name

            // Execute the insert statement and return true if one row is affected
            return ps.executeUpdate() > 0;
        }
    }

    // Method to get a user by their username from the database
    public User getUserByUsername(String username) throws SQLException {
        // SQL query to retrieve a user based on their username
        String query = "SELECT * FROM users WHERE username = ?";
        
        // Prepare the statement to execute the query
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, username); // Set the username parameter

            // Execute the query and process the result
            try (ResultSet rs = ps.executeQuery()) {
                // If the user is found in the database
                if (rs.next()) {
                    // Create a new User object and populate it with data from the ResultSet
                    User user = new User();
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setEmail(rs.getString("email"));
                    user.setFullName(rs.getString("full_name"));
                    return user; // Return the User object
                }
            }
        }
        // Return null if no user is found
        return null;
    }

    // Method to authenticate a user by username and password
    public User authenticateUser(String username, String password) throws SQLException {
        // SQL query to retrieve a user based on username and password
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        
        // Prepare the statement to execute the query
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, username); // Set the username parameter
            ps.setString(2, password); // Set the password parameter

            // Execute the query and process the result
            try (ResultSet rs = ps.executeQuery()) {
                // If the user is found and the password matches
                if (rs.next()) {
                    // Create a new User object and populate it with data from the ResultSet
                    User user = new User();
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setEmail(rs.getString("email"));
                    user.setFullName(rs.getString("full_name"));
                    return user; // Return the User object
                }
            }
        }
        // Return null if authentication fails
        return null;
    }

    // Optional: Method to update user information in the database
    public boolean updateUser(User user) throws SQLException {
        // SQL query to update the user's details based on their username
        String query = "UPDATE users SET password = ?, email = ?, full_name = ? WHERE username = ?";
        
        // Prepare the statement to execute the query
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, user.getPassword()); // Set new password
            ps.setString(2, user.getEmail());    // Set new email
            ps.setString(3, user.getFullName()); // Set new full name
            ps.setString(4, user.getUsername()); // Set username (to identify the user)

            // Execute the update statement and return true if one row is affected
            return ps.executeUpdate() > 0;
        }
    }
}
