package org.example.View;

import javax.swing.*;

public class CreateAccount extends JFrame{
    private JTextField emailField;
    private JPasswordField passwordFld;
    private JTextField userNameFld;
    private JButton createButton;
    private JPanel accountPanel;

    public CreateAccount(){
        super("Create Account");
        this.setSize(400,400);
        this.setResizable(false);

        this.setContentPane(accountPanel);

        this.setVisible(true);
    }
}
