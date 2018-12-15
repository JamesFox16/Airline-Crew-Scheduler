package user_interface_manager;

import database_handler.DatabaseInfo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EditCrewForm extends JFrame {

    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JComboBox comboBox5;
    private JButton saveButton;
    private JButton backButton;
    private JPanel editCrewPanel;

    public EditCrewForm (String title, String username) {
        setTitle(title);
    }

    public void createUI () {
        updateFlightNumbers();
        updateCaptians();
        updateFirstOfficers();
        updateFlightAttendants();


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String flightNumber = comboBox1.getSelectedItem().toString();
                String captain = comboBox2.getSelectedItem().toString();
                String firstOfficer = comboBox3.getSelectedItem().toString();
                String flightAttendant1 = comboBox4.getSelectedItem().toString();
                String flightAttendant2 = comboBox5.getSelectedItem().toString();

                // Call to update the crew for that flight.
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int exit = confirmMsgbox("Are you sure you would like to exit?");

                if (exit == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });
        this.add(editCrewPanel);
        setSize(1000,600);
    }

    private void updateFlightNumbers () {
        comboBox1.removeAllItems();

        DatabaseInfo databaseInfo = new DatabaseInfo();
        ArrayList<String> arrayList = databaseInfo.getAllFlightNumbers();

        for (int i=0; i < arrayList.size(); i++) {
            comboBox1.addItem(arrayList.get(i));
        }
    }

    private void updateCaptians () {
        comboBox2.removeAllItems();

        DatabaseInfo databaseInfo = new DatabaseInfo();
        ArrayList<String> arrayList = databaseInfo.getAllCaptains();

        for (int i=0; i< arrayList.size(); i++) {
            comboBox2.addItem(arrayList.get(i));
        }
    }

    private void updateFirstOfficers () {
        comboBox3.removeAllItems();

        DatabaseInfo databaseInfo = new DatabaseInfo();
        ArrayList<String> arrayList = databaseInfo.getAllFirstOfficers();

        for (int i=0; i<arrayList.size(); i++) {
            comboBox3.addItem(arrayList.get(i));
        }
    }

    private void updateFlightAttendants () {
        comboBox4.removeAllItems();
        comboBox5.removeAllItems();

        DatabaseInfo databaseInfo = new DatabaseInfo();
        ArrayList<String> arrayList = databaseInfo.getAllFlightAttendants();

        for (int i=0; i<arrayList.size(); i++) {
            comboBox4.addItem(arrayList.get(i));
            comboBox5.addItem(arrayList.get(i));
        }
    }

    private int confirmMsgbox(String message) {
        return  JOptionPane.showConfirmDialog(null, message, "Warning",JOptionPane.YES_NO_OPTION);
    }
}
