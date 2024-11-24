package org.example.View;


import org.example.Controller.LoginController;
import org.example.enums.UserRole;
import org.example.util.DBcon;
import org.example.util.PasswordUtils;

import javax.swing.*;
import java.util.regex.Pattern;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateAccount extends JFrame{
    private JTextField emailField;
    private JPasswordField passwordFld;
    private JTextField userNameFld;
    private JButton createButton;
    private JPanel accountPanel;
    private Connection connection;

    public CreateAccount(){
        super("Create Account");
        this.setSize(600,800);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setContentPane(accountPanel);

        this.setVisible(true);

        this.createButton.addActionListener(e -> {
            LoginController control = new LoginController();
            if (validateInputs()) {
                String email = emailField.getText();
                String password = new String(passwordFld.getPassword());  // Use getPassword() for JPasswordField
                String username = userNameFld.getText();


                try {
                    control.saveUserToDatabase(email, password, username);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                System.out.println("Email: " + email);
                System.out.println("Password: " + password);
                System.out.println("UserName: " + username);
            } else {
                JOptionPane.showMessageDialog(this, "Please fix input errors.", "Invalid Input", JOptionPane.ERROR_MESSAGE);

            }
        });
    }

    private boolean validateInputs() {
        String email = emailField.getText();
        String password = new String(passwordFld.getPassword());
        String username = userNameFld.getText();

        // Email validation
        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Invalid email format.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Password validation
        if (!isValidPassword(password)) {
            JOptionPane.showMessageDialog(this, "Password must be at least 6 characters, including letters and numbers.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Username validation
        if (!isValidUsername(username)) {
            JOptionPane.showMessageDialog(this, "Username cannot be empty and must be alphanumeric.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private boolean isValidEmail(String email) {
        String emailPattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        return Pattern.matches(emailPattern, email);
    }

    private boolean isValidPassword(String password) {
        // Check for minimum length and alphanumeric combination
        return password.length() >= 6 && password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+$");
    }

    private boolean isValidUsername(String username) {
        // Check for non-empty, alphanumeric only
        return username.length() > 0 && username.matches("^[A-Za-z0-9]+$");
    }



}
