package user_interface_manager;

import data_manager.Flight;
import data_manager.FlightChange;
import database_handler.DatabaseInfo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ChangeFlightForm extends JFrame {

    private JComboBox comboBox1;
    private JButton saveTimeChangeButton;
    private JTextField textField1;
    private JTextField textField2;
    private JPanel changeFlightPanel;
    private JTextField textField3;
    private JTextField textField4;

    public ChangeFlightForm(String title, String username) {
        setTitle(title);
    }

    public void createUI () {
        updateComboBox();

        comboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // Get the flight number from the comboBox.
                String flightNumber = comboBox1.getSelectedItem().toString();

                // Get the flight object from the database.
                FlightChange flightChange = new FlightChange();
                Flight flight = flightChange.getFlight(flightNumber);

                // Set the fields to the default values that they already have.
                textField1.setText(flight.getScheduledDeparture().toString());
                textField2.setText(flight.getScheduledArrival().toString());
            }
        });

        saveTimeChangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newDepartureTime = textField1.getText();
                String newArrivalTime = textField2.getText();
                String newActualDeparture = textField1.getText();   // Change
                String newActualArrivalTime = textField2.getText(); // Change

                // Get the flight number from the comboBox.
                String flightNumber = comboBox1.getSelectedItem().toString();

                // Change the timestamps.
                flightChange((Timestamp.valueOf(newDepartureTime)),(Timestamp.valueOf(newActualDeparture)),
                        (Timestamp.valueOf(newArrivalTime)),(Timestamp.valueOf(newActualArrivalTime)), flightNumber);
            }
        });

        this.add(changeFlightPanel);
        setSize(1000,600);
    }

    // Method that creates a FlightChange object and calls its changeTimes() method.
    public void flightChange (Timestamp estimatedDeparture, Timestamp actualDeparture, Timestamp estimatedArrival,
                              Timestamp actualArrival, String flightNum) {
        FlightChange changeFlight = new FlightChange();
        changeFlight.changeTimes(estimatedDeparture, actualDeparture, estimatedArrival, actualArrival, flightNum);
    }

    private void updateComboBox () {
        comboBox1.removeAllItems();

        DatabaseInfo databaseInfo = new DatabaseInfo();
        ArrayList<String> flightNumbers = databaseInfo.getAllFlightNumbers();

        for (int i=0; i<flightNumbers.size(); i++) {
            comboBox1.addItem(flightNumbers.get(i));
        }
    }
}
