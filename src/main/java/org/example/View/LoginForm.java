package org.example.View;

import org.example.Controller.LoginController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.example.util.DBcon;
import org.example.util.PasswordUtils;
import java.sql.Connection;
public class LoginForm extends JFrame{
    private JTextField emailtf;
    private JPasswordField passwordtf;
    private JButton loginButton;
    private JPanel loginPanel;
    private JButton createButton;
    private JPanel newPanel;

    public LoginForm(){
        super("Login");

        this.setSize(600,800);
        this.setResizable(false);
       this.createButton.addActionListener((e ->{
               System.out.println("Create button is clicked");
              new CreateAccount();
       }
               ));
        this.setContentPane(loginPanel);
        this.setVisible(true);

        this.loginButton.addActionListener(e -> {
            LoginController control = new LoginController();
            String email = emailtf.getText();
            String password = new String(passwordtf.getPassword());

            System.out.println("Login button is clicked");
            System.out.println("Password: "+password);
            System.out.println("email: "+email);

            control.validateLogin(email,password);

        });

    }

}
