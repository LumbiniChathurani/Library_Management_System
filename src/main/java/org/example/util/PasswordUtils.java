package org.example.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {

    // Method to hash a plain-text password
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }
    // Method to validate the plain-text password against the hashed password
    public static boolean validatePassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
