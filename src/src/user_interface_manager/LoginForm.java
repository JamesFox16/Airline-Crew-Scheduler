package user_interface_manager;


import data_manager.Flight;
import database_handler.DatabaseInfo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/*THE REGISTERED USERS ARE AS FOLLOWS:
USERNAME        PASSWORD
admin           password
mmorey          turtles4
jpestal         Silverado6
mcleary         backpack1
nanno           bigblock1*/

public class LoginForm extends JFrame {
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton backButton;
    private JButton signInButton;
    private JPanel loginPanel;

    public LoginForm(String title) {
        setTitle(title);
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkSignIn(textField1.getText(), passwordField1.getText())) {
                    signIn(textField1.getText(), passwordField1.getText());
                } else {
                    msgbox("Invalid password");
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public void createUI() {
        this.add(loginPanel);
        setSize(1000, 600);
    }

    private boolean checkSignIn(String username, String password) {
        // Default username and password. Will be changed later.
        DatabaseInfo databaseInfo = new DatabaseInfo();
        Connection conn = databaseInfo.getConnection();
        String query = "SELECT * FROM Users";
        PreparedStatement preparedStatement = null;

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String savedUsername = resultSet.getString("Username");
                String savedPassword = resultSet.getString("Password");

                if (savedUsername.trim().equals(username.trim()) && savedPassword.trim().equals(password.trim())) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseInfo.closeConnection(conn);
        try {
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            System.out.println("SQLException: ");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return false;
    }

    private void signIn(String username, String password) {
        textField1.setText(null);
        passwordField1.setText(null);

        // code to login and go to the next screen.
        LoggedInMenu loggedInMenu = new LoggedInMenu("Airline Manager: " + username, username);
        loggedInMenu.createUI();
        loggedInMenu.setVisible(true);
    }

    private void msgbox(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
