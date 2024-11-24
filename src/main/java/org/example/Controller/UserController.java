package org.example.Controller;

import org.example.util.DBcon;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class UserController {

    private JList<String> bookJList; // Declare a JList for book data

    // Constructor to initialize the JList
    public UserController(JList<String> bookJList) {
        this.bookJList = bookJList;
    }

    // Method to display results
    public void displayResults(String searchText) {

        String sql = "SELECT * FROM book WHERE LOWER(book_name) LIKE LOWER(?)";
        ArrayList<String> bookList = new ArrayList<>();

        try (Connection connection = DBcon.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Set the search parameter with wildcards
            preparedStatement.setString(1, "%" + searchText + "%");

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Loop through the results
            while (resultSet.next()) {
                String bookName = resultSet.getString("book_name"); // Get book name
                bookList.add(bookName); // Add it to the ArrayList
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exceptions
        }

        // Update the JList with the search results
        updateJList(bookList);
    }

    // Method to update the JList with data
    private void updateJList(ArrayList<String> bookList) {
        // Convert ArrayList to an array
        String[] bookArray = bookList.toArray(new String[0]);

        // Update the JList with the array data
        bookJList.setListData(bookArray);
    }

    public String displayExpirationDate(int memberId) {
       String userName = null;
        String expirationDate = null;

        String sql = "SELECT userName, expiration_date FROM customuser WHERE id = ?;";
        try (Connection connection = DBcon.getConnection(); // Establish database connection
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Set the value of the placeholder `?` with the provided userId
            preparedStatement.setInt(1, memberId);

            // Execute the query and get the result set
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if the result set has a record
            if (resultSet.next()) {
                // Retrieve values from the result set
                userName = resultSet.getString("userName"); // Get userName
                expirationDate = resultSet.getString("expiration_date"); // Get expiration_date
            }

            // Print the retrieved values (for testing)
            System.out.println("User Name: " + userName);
            System.out.println("Expiration Date: " + expirationDate);

        } catch (SQLException e) {
            e.printStackTrace(); // Print the error stack trace for debugging
        }

        return  expirationDate;
    }


    public String displayUserName(int memberId) {
        String userName = null;


        String sql = "SELECT userName FROM customuser WHERE id = ?;";
        try (Connection connection = DBcon.getConnection(); // Establish database connection
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Set the value of the placeholder `?` with the provided userId
            preparedStatement.setInt(1, memberId);

            // Execute the query and get the result set
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if the result set has a record
            if (resultSet.next()) {
                // Retrieve values from the result set
                userName = resultSet.getString("userName"); // Get userName

            }

            // Print the retrieved values (for testing)
            System.out.println("User Name: " + userName);


        } catch (SQLException e) {
            e.printStackTrace(); // Print the error stack trace for debugging
        }

        return  userName;
    }


}
