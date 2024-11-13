package org.example;

import org.example.View.CreateAccount;
import org.example.View.LoginForm;
import org.example.util.DBcon;

import javax.swing.*;

public class App {
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        System.out.println("Hello World!");
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        try (var conn = DBcon.getConnection()) {

            var loginView = new LoginForm();
            var accountCreate = new CreateAccount();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
