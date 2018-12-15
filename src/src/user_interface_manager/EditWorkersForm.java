package user_interface_manager;

import database_handler.DatabaseInfo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EditWorkersForm extends JFrame {

    String username;
    private JPanel editWorkersFormPanel;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JButton backButton;
    private JButton saveButton;

    public EditWorkersForm (String title, String username) {
        setTitle(title);
        this.username = username;
    }

    public void createUI () {
        updateWorkerComboBox();
        setJobTypeComboBox();
        setQualificationsComboBox();
        this.add(editWorkersFormPanel);
        setSize(1000, 600);

        // Go back to the last form.
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Click action for the save button which will update the information about the worker.
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int personID = Integer.parseInt(textField1.getText());
                String firstName = textField2.getText();
                String lastName = textField3.getText();
                String type = comboBox2.getSelectedItem().toString();
                String qualification = comboBox3.getSelectedItem().toString();

                updateWorker(personID, firstName, lastName, type, qualification);
            }
        });

        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTextFieldsOnSelection();
            }
        });

    }

    private void updateTextFieldsOnSelection () {
        String name = comboBox1.getSelectedItem().toString();
        String[] splitHold = name.split(" ");
        String idAsString = splitHold[2].substring(1,splitHold[2].length()-1);

        int personID = Integer.parseInt(idAsString);

        DatabaseInfo databaseInfo = new DatabaseInfo();
        Connection connection = databaseInfo.getConnection();
        String query = "SELECT * FROM People WHERE PeopleID = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,personID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                textField1.setText(""+personID);
                textField2.setText(resultSet.getString("FirstName"));
                textField3.setText(resultSet.getString("LastName"));
                comboBox2.setSelectedIndex(getJobTypeIndex(resultSet.getString("EmployeeType")));
                comboBox3.setSelectedIndex(getQualificationIndex(resultSet.getString("PilotQualification")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * This method will make a call to DatabaseInfo.java to get the names as a list of all the employees from the
     * database. This information will then be added to the comboBox for the user to select.
     */
    private void updateWorkerComboBox () {
        DatabaseInfo databaseInfo = new DatabaseInfo();
        ArrayList<String> people = databaseInfo.getAllPersonsStrings();

        comboBox1.removeAllItems();

        for (int i=0; i<people.size(); i++) {
            comboBox1.addItem(people.get(i));
        }
    }

    private void setJobTypeComboBox () {
        comboBox2.removeAllItems();

        comboBox2.addItem("Captain");
        comboBox2.addItem("First Officer");
        comboBox2.addItem("Flight Attendant");
    }

    private void setQualificationsComboBox () {
        comboBox3.removeAllItems();

        comboBox3.addItem("Qualified");
        comboBox3.addItem("Unqualified");
    }

    private int getJobTypeIndex (String type) {

        if (type.equals("Captain")) {
            return 0;
        } else if (type.equals("First Officer")) {
            return 1;
        } else if (type.equals("Flight Attendant")) {
            return 2;
        }else {
            return 0;
        }

    }

    private int getQualificationIndex (String qualification) {

        if (qualification.equals("Qualified")) {
            return 0;
        } else {
            return 1;
        }
    }

    // Method that takes new information from the user input and updates a persons's information in the database.
    private void updateWorker(int personID, String firstName, String lastName, String type, String qualification) {
        DatabaseInfo databaseInfo = new DatabaseInfo();
        Connection conn = databaseInfo.getConnection();
        String query = "UPDATE People SET FirstName = ?, LastName = ?, EmployeeType = ?, PilotQualification = ? " +
                "WHERE PeopleID = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, type);
            preparedStatement.setString(4, qualification);
            preparedStatement.setInt(5, personID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseInfo.closeConnection(conn);
        try {
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
        } catch (
                SQLException e) {
            System.out.println("SQLException: ");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
