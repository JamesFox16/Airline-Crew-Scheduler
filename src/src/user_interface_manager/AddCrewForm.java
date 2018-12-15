package user_interface_manager;

import data_manager.ScheduleManager;
import database_handler.DatabaseInfo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddCrewForm extends JFrame{
    private JPanel addCrewForm;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JComboBox comboBox5;
    private JButton backButton;
    private JButton addCrewButton;

    private DatabaseInfo databaseInfo = new DatabaseInfo();

    public AddCrewForm (String title, String username) {
        setTitle(title);
    }

    public void createUI () {
        updateFlightsComboBox();
        updateCaptainsComboBox();
        updateFirstOfficersComboBox();
        updateFlightAttendantsComboBox();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        addCrewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement add query
                ScheduleManager scheduleManager = new ScheduleManager();
                if (scheduleManager.checkWorkerHours(comboBox1.getSelectedIndex()+1,2) &&
                    scheduleManager.checkWorkerHours(comboBox2.getSelectedIndex()+1, 2) &&
                    scheduleManager.checkWorkerHours(comboBox3.getSelectedIndex()+1, 2) &&
                    scheduleManager.checkWorkerHours(comboBox4.getSelectedIndex()+1, 2) &&
                    scheduleManager.checkWorkerHours(comboBox5.getSelectedIndex()+1, 2)) {
                    // If all checks out add to the crew database table.
                    // Not correct at the current time because i did not have enough time to implement the call.
                } else {
                    // handle case where a selected worker is not able to work.
                }
            }
        });


        setSize(1000,600);
        this.add(addCrewForm);
    }

    // Set the comboBox to have all the flight numbers of flights that are made.
    private void updateFlightsComboBox () {
        comboBox1.removeAllItems();

        ArrayList<String> flightNumbers = new ArrayList<>();
        flightNumbers = databaseInfo.getAllFlightNumbers();

        for (int i=0; i<flightNumbers.size(); i++) {
            comboBox1.addItem(flightNumbers.get(i));
        }
    }

    // Set the comboBoxes of the Captains that are in the system.
    private void updateCaptainsComboBox () {
        comboBox2.removeAllItems();

        ArrayList<String> captains = new ArrayList<>();
        captains = databaseInfo.getAllCaptains();

        for (int i=0; i<captains.size(); i++) {
            comboBox2.addItem(captains.get(i));
        }
    }

    // Set the comboBoxes of the firstOfficers that are in the system.
    private void updateFirstOfficersComboBox () {
        comboBox3.removeAllItems();

        ArrayList<String> firstOfficers = new ArrayList<>();
        firstOfficers = databaseInfo.getAllFirstOfficers();

        for (int i=0; i<firstOfficers.size(); i++) {
            comboBox3.addItem(firstOfficers.get(i));
        }
    }

    // Set the comboBoxes of the flight attendants that are in the system.
    private void updateFlightAttendantsComboBox() {
        comboBox4.removeAllItems();
        comboBox5.removeAllItems();

        ArrayList<String> flightAttendants = new ArrayList<>();
        flightAttendants = databaseInfo.getAllFlightAttendants();

        for (int i=0; i<flightAttendants.size(); i++) {
            comboBox4.addItem(flightAttendants.get(i));
            comboBox5.addItem(flightAttendants.get(i));

        }
    }



}
