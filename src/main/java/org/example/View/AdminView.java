package org.example.View;

import org.example.Controller.AdminController;

import javax.swing.*;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class AdminView extends JFrame{
    private JTabbedPane mainPane;
    private JPanel panel1;
    private JTabbedPane memberPane;
    private JTabbedPane bookPane;
    private JTextField addmemberUserNamefld;
    private JPasswordField addMemberPasswordfld;
    private JTextField addMemberEmailfld;
    private JTextField updateMemberIdfld;
    private JTextField updateMemberUsernamefld;
    private JButton addMemberAddButton;
    private JPasswordField updateMemberPasswordFld;
    private JTextField updateMemberEmailFld;
    private JButton updateMemberUpdateButton;
    private JTextField deleteMemberIdfld;
    private JButton deleteMemberDeleteButton;
    private JTextField addBookNamefld;
    private JTextField addBookTypefld;
    private JTextField addAuthorfld;
    private JTextField addPricefld;
    private JButton addBookbtn;
    private JTextField updateBookIdfld;
    private JTextField updateBookNamefld;
    private JTextField updateBookTypefld;
    private JTextField updateBookAuthorfld;
    private JTextField updateBookPricefld;
    private JButton updateBookBtn;
    private JTextField deleteBookfld;
    private JButton deleteBookDeletebtn;
    private JButton showBtn;
    private JButton showBooksButton;

    public AdminView(){
        super();
        this.setVisible(true);
        this.setSize(600,800);
        this.setResizable(false);

        this.setContentPane(mainPane);

        this.addMemberAddButton.addActionListener(e -> {
            AdminController control = new AdminController();
           if (validateInputs()){
               String enteredusername = addmemberUserNamefld.getText();
               String enteredemail = addMemberEmailfld.getText();
               String enteredpassword = new String(addMemberPasswordfld.getPassword());

               try {
                   control.saveUserToDatabase(enteredemail, enteredpassword, enteredusername);
               } catch (SQLException ex) {
                   throw new RuntimeException(ex);
               }

           } else {
               JOptionPane.showMessageDialog(this, "Please fix input errors.", "Invalid Input", JOptionPane.ERROR_MESSAGE);

           }


        });


        this.updateMemberUpdateButton.addActionListener(e -> {
            AdminController control = new AdminController();

            int memberId = Integer.parseInt(updateMemberIdfld.getText()); // Get the ID from a text field
            String newEmail = updateMemberEmailFld.getText(); // Get the new email from a text field
            String newPassword = new String(updateMemberPasswordFld.getPassword()); // Get the new password from a password field
            String newUsername = updateMemberUsernamefld.getText(); // Get the new username from a text field

           control.updateMember(memberId,newEmail,newPassword,newUsername);
        });



        this.deleteMemberDeleteButton.addActionListener(e -> {
            AdminController control = new AdminController();
            try {
                // Get the member ID from the input field
                int memberId = Integer.parseInt(deleteMemberIdfld.getText());

                // Confirm the delete action
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this member?",
                        "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    // Call the deleteMember method
                    boolean isDeleted = control.deleteMember(memberId);

                    if (isDeleted) {
                        // Optionally refresh the member list in the view
                         // Reload the members into a table or list
                    }
                }
            } catch (NumberFormatException e2) {
                JOptionPane.showMessageDialog(null, "Please enter a valid member ID.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
            }

        });

this.showBtn.addActionListener(e -> {
    AdminController control = new AdminController();
    control.displayMembers();
});


this.addBookbtn.addActionListener(e->{
    String bookName = addBookNamefld.getText();
    String bookType = addBookTypefld.getText();
    String author = addAuthorfld.getText();
   double price = Double.parseDouble(addPricefld.getText());
    AdminController control = new AdminController();
    control.saveBookToDatabase(bookName, bookType, author, price);
});

this.updateBookBtn.addActionListener(e -> {
    AdminController control = new AdminController();
    int bookId = Integer.parseInt(updateBookIdfld.getText());
    String bookName = updateBookNamefld.getText();
    String bookType = updateBookTypefld.getText();
    String author = updateBookAuthorfld.getText();
    double price = Double.parseDouble(updateBookPricefld.getText());

    control.updateBook(bookId, bookName, bookType, author,price);
});

this.deleteBookDeletebtn.addActionListener(e -> {
    AdminController control = new AdminController();
    try {
        // Get the member ID from the input field
        int bookId = Integer.parseInt(deleteBookfld.getText());

        // Confirm the delete action
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this book?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            // Call the deleteMember method
            boolean isDeleted = control.deleteBook(bookId);

            if (isDeleted) {
                // Optionally refresh the member list in the view
                // Reload the members into a table or list
            }
        }
    } catch (NumberFormatException e2) {
        JOptionPane.showMessageDialog(null, "Please enter a valid book ID.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
    }
});


this.showBooksButton.addActionListener(e -> {
    AdminController control = new AdminController();
    control.displayBooks();
});



    }
    private boolean validateInputs() {
        String email = addMemberEmailfld.getText();
        String password = new String(addMemberPasswordfld.getPassword());
        String username = addmemberUserNamefld.getText();

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

    private void createUIComponents() {
        // TODO: place custom component creation code here

    }


}
