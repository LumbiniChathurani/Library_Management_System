package org.example.Controller;

import org.example.Model.Book;
import org.example.Model.CustomUser;
import org.example.View.AdminView;
import org.example.enums.UserRole;
import org.example.util.DBcon;
import org.example.util.PasswordUtils;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminController extends AdminView {

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


    public boolean updateMember(int memberId, String newEmail, String newPassword, String newUsername) {
        String sql = "UPDATE CustomUser SET email = ?, password = ?, userName = ? WHERE id = ?";

        try (Connection connection = DBcon.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            // Hash the new password before updating
            String hashedPassword = PasswordUtils.hashPassword(newPassword);

            // Set the parameters for the update query
            pstmt.setString(1, newEmail);
            pstmt.setString(2, hashedPassword);
            pstmt.setString(3, newUsername);
            pstmt.setInt(4, memberId);

            int rowsUpdated = pstmt.executeUpdate();

            // Check if the update was successful
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Member updated successfully!");
                return true; // Update was successful
            } else {
                JOptionPane.showMessageDialog(null, "Member ID not found.", "Update Failed", JOptionPane.ERROR_MESSAGE);
                return false; // Member ID does not exist
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error occurred.", "Error", JOptionPane.ERROR_MESSAGE);
            return false; // Error occurred
        }
    }


    public boolean deleteMember(int memberId) {
        String sql = "DELETE FROM CustomUser WHERE id = ?";

        try (Connection connection = DBcon.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            // Set the parameter for the delete query
            pstmt.setInt(1, memberId);

            int rowsDeleted = pstmt.executeUpdate();

            // Check if the delete was successful
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "Member deleted successfully!");
                return true; // Deletion successful
            } else {
                JOptionPane.showMessageDialog(null, "Member ID not found.", "Delete Failed", JOptionPane.ERROR_MESSAGE);
                return false; // Member ID does not exist
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error occurred.", "Error", JOptionPane.ERROR_MESSAGE);
            return false; // Error occurred
        }
    }



    public List<CustomUser> getMembers() {
        String sql = "SELECT id, email, userName FROM CustomUser WHERE userRole = ?";
        List<CustomUser> members = new ArrayList<>();

        try (Connection connection = DBcon.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, UserRole.USER.name()); // Fetch only members with role 'USER'

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String email = rs.getString("email");
                    String userName = rs.getString("userName");
                    members.add(new CustomUser(id, email, userName));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching members from the database.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return members;
    }


    public void displayMembers() {
        List<CustomUser> members = getMembers();

        if (members.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No members found.", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String[] columnNames = {"ID", "Email", "Username"};
        Object[][] data = new Object[members.size()][3];

        for (int i = 0; i < members.size(); i++) {
            data[i][0] = members.get(i).getId();
            data[i][1] = members.get(i).getEmail();
            data[i][2] = members.get(i).getUserName();
        }

        JTable memberTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(memberTable);
        memberTable.setFillsViewportHeight(true);

        // Display the table in a dialog or panel
        JOptionPane.showMessageDialog(null, scrollPane, "Member List", JOptionPane.PLAIN_MESSAGE);
    }


    public void saveBookToDatabase(String bookName, String bookType, String author, double price) {
        String sql = "INSERT INTO book (book_name, book_type, author, price) VALUES (?, ?, ?, ?)";

        try (Connection connection = DBcon.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, bookName);
            pstmt.setString(2, bookType);
            pstmt.setString(3, author);
            pstmt.setDouble(4, price);
            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Book added successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving book to database.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean updateBook(int bookId, String bookName, String bookType, String author, double price) {
        String sql = "UPDATE book SET book_name = ?, book_type = ?, author = ?, price = ? WHERE book_id = ?";

        try (Connection connection = DBcon.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {


            // Set the parameters for the update query
            pstmt.setString(1, bookName);
            pstmt.setString(2, bookType);
            pstmt.setString(3, author);
            pstmt.setDouble(4, price);
            pstmt.setInt(5, bookId);

            int rowsUpdated = pstmt.executeUpdate();

            // Check if the update was successful
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Book updated successfully!");
                return true; // Update was successful
            } else {
                JOptionPane.showMessageDialog(null, "Book ID not found.", "Update Failed", JOptionPane.ERROR_MESSAGE);
                return false; // Member ID does not exist
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error occurred.", "Error", JOptionPane.ERROR_MESSAGE);
            return false; // Error occurred
        }
    }

    public boolean deleteBook(int bookId) {
        String sql = "DELETE FROM book WHERE book_id = ?";

        try (Connection connection = DBcon.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            // Set the parameter for the delete query
            pstmt.setInt(1, bookId);

            int rowsDeleted = pstmt.executeUpdate();

            // Check if the delete was successful
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "Book deleted successfully!");
                return true; // Deletion successful
            } else {
                JOptionPane.showMessageDialog(null, "Book ID not found.", "Delete Failed", JOptionPane.ERROR_MESSAGE);
                return false; // Member ID does not exist
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error occurred.", "Error", JOptionPane.ERROR_MESSAGE);
            return false; // Error occurred
        }
    }




    public List<Book> getBooks() {
        String sql = "SELECT book_id, book_name, book_type, author, price FROM book";
        List<Book> books = new ArrayList<>();

        try (Connection connection = DBcon.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {



            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    long id = rs.getInt("book_id");
                    String bookName = rs.getString("book_name");
                    String bookType = rs.getString("book_type");
                    String author = rs.getString("author");
                    double price = rs.getDouble("price");
                    books.add(new Book(id, bookName, bookType, author, price));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching books from the database.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return books;
    }


    public void displayBooks() {
        List<Book> books = getBooks();

        if (books.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No books found.", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String[] columnNames = {"ID", "Book name", "Book type", "Author", "Price"};
        Object[][] data = new Object[books.size()][5];

        for (int i = 0; i < books.size(); i++) {
            data[i][0] = books.get(i).getId();
            data[i][1] = books.get(i).getName();
            data[i][2] = books.get(i).getType();
            data[i][3] = books.get(i).getAuthor();
            data[i][4] = books.get(i).getPrice();
        }

        JTable bookTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(bookTable);
        bookTable.setFillsViewportHeight(true);

        // Display the table in a dialog or panel
        JOptionPane.showMessageDialog(null, scrollPane, "Book List", JOptionPane.PLAIN_MESSAGE);
    }



}
