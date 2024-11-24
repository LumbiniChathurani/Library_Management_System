package org.example.View;

import org.example.Controller.UserController;

import javax.swing.*;

public class UserView extends JFrame {
    private JTabbedPane userHomePane;
    private JPanel panel1;
    private JTextField searchFld;
    private JList<String> bookList; // Specify type as JList<String>
    private JButton searchBtn;
    private JTextField idField;
    private JButton showCardBtn;
    private JLabel cardTitle;
    private JLabel userName;
    private JLabel expirationDate;

    public UserView() {
        super();
        this.setVisible(true);
        this.setSize(600, 800);
        this.setResizable(false);

        this.setContentPane(userHomePane);

        // Initialize the controller with the bookList
        UserController control = new UserController(bookList);

        // Add action listener for search button
        this.searchBtn.addActionListener(e -> {
            String searchData = searchFld.getText(); // Get text from the search field
            control.displayResults(searchData); // Call the controller's method to fetch and display results
        });

        this.showCardBtn.addActionListener(e -> {
            int memberId = Integer.parseInt(idField.getText());

cardTitle.setText("Membership Card");
            userName.setText("UserName: "+control.displayUserName(memberId));
            expirationDate.setText("Expiration Date: "+control.displayExpirationDate(memberId));

        });
    }
}
