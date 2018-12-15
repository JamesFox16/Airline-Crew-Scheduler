package data_manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database_handler.DatabaseInfo;

public class Airport {
    private String airportName;
    private String timeZone;
    private String city;
    private String state;

    public Airport (String airportName, String city, String state, String timeZone) {
        this.airportName = airportName;
        this.city = city;
        this.state = state;
        this.timeZone = timeZone;
    }
    public String getAirportName(){
        return airportName;
    }

    public void setAirportName(String airportName){
        this.airportName = airportName;
    }

    public String getCity(){
        return city;
    }

    public void setCity(String city){
        this.city = city;
    }

    public String getState(){
        return state;
    }

    public void setState(String state){
        this.state = state;
    }

    public String getTimeZone(){
        return timeZone;
    }

    public void setTimeZone(String timeZone){
        this.timeZone = timeZone;
    }

    public static void addAirport(String airportName, String city, String state, String timeZone){
        DatabaseInfo databaseInfo = new DatabaseInfo();
        Connection conn = databaseInfo.getConnection();
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO Airports (AirportName,TimeZone,City,State) VALUES (?,?,?,?)";

        try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, airportName);
            preparedStatement.setString(2, city);
            preparedStatement.setString(3, state);
            preparedStatement.setString(4, timeZone);
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
    public static void deleteAirport () {
        /*DatabaseInfo databaseInfo = new DatabaseInfo();
        Connection conn = databaseInfo.getConnection();
        String query = "DELETE FROM Airplanes";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.executeUpdate();
            query = "DELETE FROM StandbyCrew";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.executeUpdate();
            query = "DELETE FROM AIRPORT";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.executeUpdate();
        }catch(SQLException e) {
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
        }*/
    }
}