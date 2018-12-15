package user_interface_manager;

import data_manager.Flight;
import data_manager.FlightCancel;
import data_manager.FlightChange;
import database_handler.DatabaseInfo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CancelFlightForm extends JFrame {

    private JPanel cancelFlightPanel;
    private JComboBox comboBox1;
    private JButton cancelFlightButton;
    private JButton backButton;

    public CancelFlightForm (String title, String username) {
        setTitle(title);
    }

    public void cancelFlight (String flightNum) {
        FlightCancel flight = null;
        flight.deleteFlight(flightNum);
        createUI();
    }

    public void createUI () {
        updateCombo();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        cancelFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String flightNumber = comboBox1.getSelectedItem().toString();

                // Make Call to cancel Flight.
                FlightCancel flightCancel = new FlightCancel();
                flightCancel.deleteFlight(flightNumber);

                // Update the comboBox
                updateCombo();
            }
        });

        this.add(cancelFlightPanel);
        setSize(1000,600);

    }

    public void updateCombo () {
        comboBox1.removeAllItems();

        DatabaseInfo databaseInfo = new DatabaseInfo();
        ArrayList<String> arrayList = databaseInfo.getAllFlightNumbers();

        for (int i=0; i < arrayList.size(); i++) {
            comboBox1.addItem(arrayList.get(i));
        }
    }
}
