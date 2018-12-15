package data_manager;

import database_handler.DatabaseInfo;

import java.sql.*;

public class FlightCancel extends Flight {

    public FlightCancel() {

    }

    public FlightCancel(int flightID, String planeNum, String flightNum, String weather, String originAirport,
                        String destAirport, Timestamp scheduledDeparture, Timestamp estimatedDeparture,
                        Timestamp actualDeparture, Timestamp scheduledArrival, Timestamp estimatedArrival,
                        Timestamp actualArrival, String status, String created) {
        super(flightID, planeNum, flightNum, weather, originAirport, destAirport, scheduledDeparture,
                estimatedDeparture, actualDeparture, scheduledArrival, estimatedArrival, actualArrival, status, created);

    }

    public void deleteFlight(String flightNum) {
        DatabaseInfo databaseInfo = new DatabaseInfo();
        Connection conn = databaseInfo.getConnection();
        String query = "UPDATE Flights SET TimeStatus = 'Cancelled' WHERE FlightNumber = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, flightNum);
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
/*    public static void main (String[] args) {
        deleteFlight();
    }*/
}
