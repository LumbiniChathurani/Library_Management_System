package org.example;

import org.example.View.LoginForm;
import org.example.util.DBcon;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        try (var conn = DBcon.getConnection()) {

            var loginView = new LoginForm();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
