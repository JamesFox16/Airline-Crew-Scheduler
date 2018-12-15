package data_manager;

import database_handler.DatabaseInfo;

import java.sql.*;

public class FlightScheduled extends Flight {

    // Default constructor.
    public FlightScheduled (int flightID, String planeNum, String flightNum, String weather, String originAirport,
                           String destAirport, Timestamp scheduledDeparture, Timestamp estimatedDeparture,
                           Timestamp actualDeparture, Timestamp scheduledArrival, Timestamp estimatedArrival,
                           Timestamp actualArrival, String status, String created) {
        super (flightID, planeNum, flightNum, weather, originAirport, destAirport, scheduledDeparture,
                estimatedDeparture, actualDeparture, scheduledArrival, estimatedArrival, actualArrival, status, created);


    }

    public Flight seeScheduledFlight (String flightNum) {
        DatabaseInfo databaseInfo = new DatabaseInfo();
        Connection conn = databaseInfo.getConnection();
        String query = "SELECT * FROM Flights WHERE FlightNumber = ?";
        PreparedStatement preparedStatement = null;
        Flight flight = null;

        try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, flightNum);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            int flightID = resultSet.getInt("FlightsID");
            String planeNum = resultSet.getString("PlaneNumber");
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

            flight = new Flight(flightID, planeNum, flightNum, weather, originAirport, destAirport,
                    scheduledDeparture, estimatedDeparture, actualDeparture, scheduledArrival, estimatedArrival,
                    actualArrival, status, created);
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
        return flight;
    }

    /*public static void main(String[] args) {
        Flight flight = seeScheduledFlight("THY176");
        System.out.println(flight.toString());
    }*/
}
