package data_manager;

import database_handler.DatabaseInfo;

import java.sql.*;

public class FlightChange extends Flight{

    // Default constructor
    public FlightChange () {

    }

    public FlightChange(int flightID, String planeNum, String flightNum, String weather, String originAirport,
                        String destAirport, Timestamp scheduledDeparture, Timestamp estimatedDeparture,
                        Timestamp actualDeparture, Timestamp scheduledArrival, Timestamp estimatedArrival,
                        Timestamp actualArrival, String status, String created) {
        super(flightID, planeNum, flightNum, weather, originAirport, destAirport, scheduledDeparture,
                estimatedDeparture, actualDeparture, scheduledArrival, estimatedArrival, actualArrival, status, created);
    }

    // Method that takes in new flight times and updates a flight using its flight number.
    public void changeTimes(Timestamp estimatedDeparture, Timestamp actualDeparture, Timestamp estimatedArrival,
                            Timestamp actualArrival, String flightNum) {
        DatabaseInfo databaseInfo = new DatabaseInfo();
        Connection conn = databaseInfo.getConnection();
        String query = "UPDATE Flights SET EstimatedDeparture = ?, ActualDeparture = ?, EstimatedArrival = ?, " +
                "ActualArrival = ?, TimeStatus = 'Delayed' WHERE FlightNumber = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setTimestamp(1, estimatedDeparture);
            preparedStatement.setTimestamp(2, actualDeparture);
            preparedStatement.setTimestamp(3, estimatedArrival);
            preparedStatement.setTimestamp(4, actualArrival);
            preparedStatement.setString(5, flightNum);
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
    public Flight getFlight(String flightNum) {
        DatabaseInfo databaseInfo = new DatabaseInfo();
        Connection conn = databaseInfo.getConnection();
        String query = "SELECT * FROM Flights WHERE FlightNumber = ?";
        PreparedStatement preparedStatement = null;
        return null;
    }
}