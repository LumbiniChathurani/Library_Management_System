package org.example.View;

import javax.swing.*;

public class LoginForm extends JFrame{
    private JTextField emailtf;
    private JPasswordField passwordtf;
    private JButton loginButton;
    private JPanel loginPanel;

    public LoginForm(){
        super("Login");

        this.setSize(400,400);
        this.setResizable(false);

        this.setContentPane(loginPanel);

        this.setVisible(true);

    }

}
