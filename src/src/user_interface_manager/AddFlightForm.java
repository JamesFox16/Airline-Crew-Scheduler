package user_interface_manager;

import data_manager.Flight;
import database_handler.DatabaseInfo;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class AddFlightForm extends JFrame {

    private JTextField planeNumberText;
    private JTextField flightNumberText;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JFormattedTextField scheduledDepartureText;
    private JFormattedTextField estimatedDepartureText;
    private JFormattedTextField actualDepartureText;
    private JFormattedTextField scheduledArrivalText;
    private JFormattedTextField estimatedArrivalText;
    private JFormattedTextField actualArrivalText;
    private JButton addFlightButton;
    private JButton backButton;
    private JComboBox comboBox3;
    private JPanel addFlightPanel;
    private JLabel fds;

    private String username;


    public AddFlightForm (String title, String username) {
        setTitle(title);
        this.username = username;
    }

    public void createUI () {
        initializeComponents();
    }

    /*
     * Nested Class that will be able to manage the combo items that are added to the comboBox. This will make it easier
     * for items to be added and for changes to be made in the future. If we want to expand the items it will be simple
     * call to do so. No other class will need this so it was decided to be a nested class.
     */
    private class ComboBoxItem {
        private String key;
        private int value;

        public ComboBoxItem(String key, int value) {
            this.key = key;
            this.value = value;
        }
        // Override of the to string method.
        public String toString () {
            return this.key;
        }
        // Return the value of the comboItem
        public int getValue () {
            return this.value;
        }

    }

    private void initializeComponents () {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        // Fill the comboBox with the available weather forecast conditions.
        comboBox3.addItem(new ComboBoxItem("Partly Cloudy", 0));
        comboBox3.addItem(new ComboBoxItem("Sunny", 1));
        comboBox3.addItem(new ComboBoxItem("Rainy", 2));
        comboBox3.addItem(new ComboBoxItem("Thunderstorm", 3));
        comboBox3.addItem(new ComboBoxItem("Windy", 4));
        comboBox3.addItem(new ComboBoxItem("Foggy", 5));
        comboBox3.addItem(new ComboBoxItem("Snowy", 6));

        // Fill the comboBox with the available flight options.
        comboBox1.addItem(new ComboBoxItem("EVA (Evanston, IL)", 0));
        comboBox1.addItem(new ComboBoxItem("IOW (Iowa City, IA)", 1));
        comboBox1.addItem(new ComboBoxItem("LAF (West Lafayette, IN)", 2));
        comboBox1.addItem(new ComboBoxItem("LNK (Lincoln, NE)", 3));

        comboBox2.addItem(new ComboBoxItem("EVA (Evanston, IL)", 0));
        comboBox2.addItem(new ComboBoxItem("IOW (Iowa City, IA)", 1));
        comboBox2.addItem(new ComboBoxItem("LAF (West Lafayette, IN)", 2));
        comboBox2.addItem(new ComboBoxItem("LNK (Lincoln, NE)", 3));

        // Add flight button clicked.
        addFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

                // Get the information from the GUI text elements and make variables for them.
                try {
                    String planeNumber = planeNumberText.getText();
                    String flightNumber = flightNumberText.getText();
                    String scheduledDeparture = scheduledDepartureText.getText();
                    Date parsedDate = dateFormat.parse(scheduledDeparture);
                    Timestamp parsedScheduledDeparture = new java.sql.Timestamp(parsedDate.getTime());
                    String estimatedDeparture = estimatedDepartureText.getText();
                    parsedDate = dateFormat.parse(estimatedDeparture);
                    Timestamp parsedEstimatedDeparture = new java.sql.Timestamp(parsedDate.getTime());
                    String actualDeparture = actualDepartureText.getText();
                    parsedDate = dateFormat.parse(actualDeparture);
                    Timestamp parsedActualDeparture = new java.sql.Timestamp(parsedDate.getTime());
                    String scheduledArrival = scheduledArrivalText.getText();
                    parsedDate = dateFormat.parse(scheduledArrival);
                    Timestamp parsedScheduledArrival = new java.sql.Timestamp(parsedDate.getTime());
                    String estimatedArrival = estimatedArrivalText.getText();
                    parsedDate = dateFormat.parse(estimatedArrival);
                    Timestamp parsedEstimatedArrival = new java.sql.Timestamp(parsedDate.getTime());
                    String actualArrival = actualArrivalText.getText();
                    parsedDate = dateFormat.parse(actualArrival);
                    Timestamp parsedActualArrival = new java.sql.Timestamp(parsedDate.getTime());

                    // Get the information from the GUI comboBox elements and make variables for them.
                    String weather = comboBox3.getSelectedItem().toString();
                    String departureAirport = comboBox1.getSelectedItem().toString().substring(0,3);
                    String arrivalAirport = comboBox2.getSelectedItem().toString().substring(0,3);

                    boolean check = checkIfValidInput(planeNumber, flightNumber, weather, departureAirport, arrivalAirport,
                            parsedScheduledDeparture, parsedEstimatedDeparture, parsedActualDeparture, parsedScheduledArrival,
                            parsedEstimatedArrival, parsedEstimatedArrival);

                    if (check) {
                        // make sure that the add call is made to the database.
                        Flight flight = new Flight(flightNumber, planeNumber, weather, departureAirport, arrivalAirport, parsedScheduledDeparture,
                                parsedEstimatedDeparture, parsedActualDeparture, parsedScheduledArrival, parsedEstimatedArrival,
                                parsedActualArrival, "On Time", username);
                        addFlight(flight);

                        // Remove the entered values from the text boxes.
                        planeNumberText.setText(null);
                        flightNumberText.setText(null);
                        scheduledDepartureText.setText(null);
                        estimatedDepartureText.setText(null);
                        actualDepartureText.setText(null);
                        scheduledArrivalText.setText(null);
                        estimatedArrivalText.setText(null);
                        actualArrivalText.setText(null);

                        // Reset the positioning of the comboBox.
                        comboBox3.setSelectedIndex(0);
                        comboBox1.setSelectedIndex(0);
                        comboBox2.setSelectedIndex(0);
                    } else {
                        msgbox("Input to add flight not valid. Please check to make sure that all values entered are correct.");
                    }
                } catch (ParseException p) {
                    p.printStackTrace();
                }
            }
        });

        // Go back to the menu
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = confirmMsgbox("If you have any unsaved entries, they will not saved.\n\nAre you sure you want to leave?");

                // If the user wants to close the menu.
                if (response == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });
        this.add(addFlightPanel);
        setSize(1000,600);
    }

    /*
     * This method will go through the inputs that the user entered into the GUI and determine if the input meets the
     * systems criteria for creating a flight. Some information will be allowed to be null because it may be an event
     * that has not yet occurred.
     */
    private boolean checkIfValidInput (String planeNumber, String flightNumber, String weather, String departureAirport,
                                       String arrivalAirport, Timestamp scheduledDeparture, Timestamp estimatedDeparture,
                                       Timestamp actualDeparture, Timestamp scheduledArrival, Timestamp estimatedArrival,
                                       Timestamp actualArrival) {

        // Check to see if the values that are needed are entered. If they are not it will return false.
        if (planeNumber.isEmpty()|| flightNumber.isEmpty() || weather.isEmpty() || departureAirport.isEmpty() ||
            arrivalAirport.isEmpty() || scheduledDeparture == null || estimatedDeparture == null ||
            scheduledArrival == null || estimatedArrival == null) {
            return false;
        }

        // If the arrival and departure airport are the same then the flight is not valid.
        if (departureAirport.equals(arrivalAirport)) {
            return false;
        }
        return true;
    }

    // Method that will allow for the use of a msg box to display a message to the user.
    private void msgbox (String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    private int confirmMsgbox(String message) {
        return  JOptionPane.showConfirmDialog(null, message, "Warning",JOptionPane.YES_NO_OPTION);
    }

    // Method that takes in a flight object then adds all attributes into the database.
    private void addFlight(Flight flight) {
        DatabaseInfo databaseInfo = new DatabaseInfo();
        Connection conn = databaseInfo.getConnection();
        String query = "INSERT INTO Flights (FlightNumber,PlaneNumber,Weather,OriginAirport,DestinationAirport, " +
                "ScheduledDeparture,EstimatedDeparture,ActualDeparture,ScheduledArrival,EstimatedArrival,ActualArrival," +
                "TimeStatus) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, flight.getFlightNum());
            preparedStatement.setString(2, flight.getPlaneNum());
            preparedStatement.setString(3, flight.getWeather());
            preparedStatement.setString(4, flight.getOriginAirport());
            preparedStatement.setString(5, flight.getdestAirport());
            preparedStatement.setTimestamp(6, flight.getScheduledDeparture());
            preparedStatement.setTimestamp(7, flight.getEstimatedDeparture());
            preparedStatement.setTimestamp(8, flight.getActualDeparture());
            preparedStatement.setTimestamp(9, flight.getScheduledArrival());
            preparedStatement.setTimestamp(10, flight.getEstimatedArrival());
            preparedStatement.setTimestamp(11, flight.getActualArrival());
            preparedStatement.setString(12, flight.getStatus());
            preparedStatement.executeUpdate();
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
    }
}
