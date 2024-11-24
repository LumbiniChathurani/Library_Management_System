package org.example.Controller;
import org.example.View.AdminView;
import org.example.View.CreateAccount;
import org.example.View.UserView;
import org.example.enums.UserRole;
import org.example.util.DBcon;
import org.example.util.PasswordUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginController extends CreateAccount{
    public void saveUserToDatabase(String email, String password, String username) throws SQLException {
        String sql = "INSERT INTO CustomUser (email, password, userName,userRole) VALUES (?, ?, ?, ?)";

        try (Connection connection = DBcon.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, PasswordUtils.hashPassword(password));
            pstmt.setString(3, username);
            pstmt.setString(4, UserRole.USER.name());
            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Account created successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving account to database.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }



    public void validateLogin(String enteredEmail, String enteredPassword) {
        String adminEmail = "admin@gmail.com";
        String sqlAdmin = "SELECT password FROM customuser WHERE email = ?";
        String sqlUser = "SELECT password, userRole FROM customuser WHERE email = ?";

        try (Connection connection = DBcon.getConnection()) {
            //Check if the entered credentials match the admin
            try (PreparedStatement pstmtAdmin = connection.prepareStatement(sqlAdmin)) {
                pstmtAdmin.setString(1, adminEmail);
                try (ResultSet rsAdmin = pstmtAdmin.executeQuery()) {
                    if (rsAdmin.next()) {
                        String hashedAdminPassword = rsAdmin.getString("password");
                        if (PasswordUtils.validatePassword( enteredPassword,  hashedAdminPassword) && enteredEmail.equals(adminEmail)) {
                            // Admin credentials match
                            JOptionPane.showMessageDialog(null, "Welcome Admin!");
                            new AdminView();
                            return;

                        }
                    }
                }
            }
            // If not admin, check against other users
            try (PreparedStatement pstmtUser = connection.prepareStatement(sqlUser)) {
                pstmtUser.setString(1, enteredEmail);
                try (ResultSet rsUser = pstmtUser.executeQuery()) {
                    if (rsUser.next()) {
                        String hashedUserPassword = rsUser.getString("password");
                        String userRole = rsUser.getString("userRole");
                        if (PasswordUtils.validatePassword(enteredPassword, hashedUserPassword)) {
                            //user credentials match
                            if (userRole.equals("USER")) {
                                JOptionPane.showMessageDialog(null, "Welcome User!");
                                new UserView();
                            }
                            return;
                        }
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Invalid credentials. Please try again.",
                    "Login Failed", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error occurred.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }
}
