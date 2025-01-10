package model;

public class User {
    private String username; // The username of the user
    private String password; // The password of the user (stored securely)
    private String email;    // The email address of the user
    private String fullName; // The full name of the user

    // Default constructor
    public User() {
    }

    // Constructor with all fields
    public User(String username, String password, String email, String fullName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
    }

    // Getter for username
    public String getUsername() {
        return username;
    }

    // Setter for username
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter for password
    public String getPassword() {
        return password;
    }

    // Setter for password
    public void setPassword(String password) {
        this.password = password;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }

    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter for full name
    public String getFullName() {
        return fullName;
    }

    // Setter for full name
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    // Override the toString method to provide a string representation of the User object
    @Override
    public String toString() {
        return "User{" +
               "username='" + username + '\'' +
               ", password='" + password + '\'' +
               ", email='" + email + '\'' +
               ", fullName='" + fullName + '\'' +
               '}';
    }
}
