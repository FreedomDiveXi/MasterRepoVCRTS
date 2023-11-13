import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

public class User {
    private String username;
    private String hashedPassword;

    public User(String username, String password) {
        this.username = username;
        this.hashedPassword = password;
    }

    // Hashes the password for storage using SHA-256
    private String hashPassword(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    public String getUsername() {
        return username;
    }

    public String getHashedPassword(){ return hashedPassword;}

    // Note: We don't provide a method to get hashedPassword for security reasons

    public void changePassword(String newPassword) {
        validatePassword(newPassword);
        this.hashedPassword = hashPassword(newPassword);
    }

    public boolean login(String attemptedPassword) {
        return this.hashedPassword.equals(hashPassword(attemptedPassword));
    }

    private void validateUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
    }

    private void validatePassword(String password) {
        // For this example, let's say a valid password needs to be at least 8 characters
        // and contain at least one number. Adjust these rules as needed.
        if (password == null || password.length() < 8 || !Pattern.compile("[0-9]").matcher(password).find()) {
            throw new IllegalArgumentException("Password must be at least 8 characters and contain at least one number");
        }
    }
}
