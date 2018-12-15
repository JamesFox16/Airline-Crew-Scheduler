package user_interface_manager;

import data_manager.Flight;
import database_handler.DatabaseInfo;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;

public class ViewFlightsForm extends JFrame {

    private JComboBox comboBox1;
    private JButton searchButton;
    private JPanel viewFlightsPanel;
    private JScrollPane tableScroll;
    private JTable table1;

    public ViewFlightsForm(String title) {
        setTitle(title);
    }

    public ArrayList<Flight> getFlightList() {
        DefaultListModel<Flight> model = new DefaultListModel<>();
        DatabaseInfo databaseInfo = new DatabaseInfo();
        Connection conn = databaseInfo.getConnection();
        String query = "SELECT * FROM Flights";
        PreparedStatement preparedStatement = null;
        ArrayList<Flight> flightList = new ArrayList<>();

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int flightID = resultSet.getInt("FlightsID");
                String planeNum = resultSet.getString("PlaneNumber");
                String flightNum = resultSet.getString("FlightNumber");
                String weather = resultSet.getString("Weather");
                String originAirport = resultSet.getString("OriginAirport");
                String destAirport = resultSet.getString("DestinationAirport");
                Timestamp scheduledDeparture = resultSet.getTimestamp("ScheduledDeparture");
                Timestamp estimatedDeparture = resultSet.getTimestamp("EstimatedDeparture");
                Timestamp actualDeparture = resultSet.getTimestamp("ActualDeparture");
                Timestamp scheduledArrival = resultSet.getTimestamp("ScheduledArrival");
                Timestamp estimatedArrival = resultSet.getTimestamp("EstimatedArrival");
                Timestamp actualArrival = resultSet.getTimestamp("ActualArrival");
                String status = resultSet.getString("TimeStatus");
                String created = resultSet.getString("CreatedBy");

                Flight flight = new Flight(flightID, planeNum, flightNum, weather, originAirport, destAirport,
                        scheduledDeparture, estimatedDeparture, actualDeparture, scheduledArrival, estimatedArrival,
                        actualArrival, status, created);
                flightList.add(flight);
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
        return flightList;
    }

    public void createUI() {
        updateTable(getFlightList());
        this.add(viewFlightsPanel);
        this.setSize(1000, 600);
    }

    private void updateTable (ArrayList<Flight> flights) {

        //FlightTableModel flightTableModel = new FlightTableModel(flights);

        //table1.setModel(flightTableModel);
        String[] columnNames = {"Flight Number", "Weather", "Origin Airport", "DestinationAirport", "Scheduled Departure",
                "Estimated Departure", "Time Status"};

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("FlightNumber");
        model.addColumn("Weather");
        model.addColumn("Origin Airport");
        model.addColumn("DestinationAirport");
        model.addColumn("Scheduled Departure");
        model.addColumn("Estimated Departure");
        model.addColumn("Time Status");

        for (int i=0; i<flights.size(); i++){
            model.addRow(new Object[] {flights.get(i).getFlightNum(), flights.get(i).getWeather(), flights.get(i).getOriginAirport(),
                                    flights.get(i).getdestAirport(), flights.get(i).getScheduledDeparture(),
                                    flights.get(i).getEstimatedDeparture(), flights.get(i).getStatus()});
        }
        table1.setModel(model);
    }
}