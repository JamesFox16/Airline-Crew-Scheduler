package data_manager;

import database_handler.DatabaseInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class ScheduleManager {

    // Default constructor.
    public ScheduleManager () {

    }

    /*
     * This method will look for the hours that the worker selected is working and see if they are eligible to work, or
     * if they will go over the hours that they are allowed to work. Will return false if the worker is not able to work.
     * Method will return true when the worker is able to work.
     */
    public boolean checkWorkerHours (int peopleID, int flightHours) {
        DatabaseInfo databaseInfo = new DatabaseInfo();
        Connection connection = databaseInfo.getConnection();
        String query = "SELECT HoursWorked FROM People WHERE PeopleID = ?";
        PreparedStatement preparedStatement = null;
        int hoursWorked = 0;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, peopleID);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            hoursWorked = resultSet.getInt("HoursWorked");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseInfo.closeConnection(connection);

        if (hoursWorked + flightHours > 8) {
            return false;
        }
        return true;
    }

    /*
     * This method will look at the selected worker and see what the time is that they are scheduled to work and see if
     * there is another flight that they need to be present on.
     */
    public boolean checkIfScheduledDuringTime (int peopleID, Date time) {
        return false;
    }
}
