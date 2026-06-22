package model;

public class Admin {
    private String username;
    private String password;

    public Admin(String username, String password) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters long.");
        }
        this.username = username;
        this.password = password;
    }

    public boolean validateCredentials(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
        return this.username.equals(username) && this.password.equals(password);
    }

    public void changeCredentials(String username, String password) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters long.");
        }
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
}