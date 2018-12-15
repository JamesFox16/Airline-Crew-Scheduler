package database_handler;

import data_manager.*;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseInfo {
    private static final String url = "jdbc:mysql://cse.unl.edu/nlawrence";
    private static final String username = "nlawrence";
    private static final String password = ":m3CMg";

    static public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (InstantiationException e) {
            System.out.println("InstantiationException: ");
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            System.out.println("IllegalAccessException: ");
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException: ");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(DatabaseInfo.url, DatabaseInfo.username, DatabaseInfo.password);
        } catch (SQLException e) {
            System.out.println("SQLException: ");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return conn;
    }

    public void closeConnection(Connection c) {
        try {
            if (c != null && !c.isClosed()) {
                c.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * This method will go through the database and get all of the flight numbers that are entered into the Flights
     * Table in the database. The method will take the string of the flight number and store it in an ArrayList of
     * Strings. The method will then return the ArrayList of flight numbers.
     */
    public ArrayList<String> getAllFlightNumbers () {
        DatabaseInfo databaseInfo = new DatabaseInfo();
        Connection conn = databaseInfo.getConnection();
        String query = "SELECT FlightNumber FROM Flights WHERE NOT TimeStatus = 'Cancelled'";
        PreparedStatement preparedStatement = null;

        ArrayList<String> flightNumbers = new ArrayList<String>();

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                flightNumbers.add(resultSet.getString("FlightNumber"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseInfo.closeConnection(conn);

        return flightNumbers;
    }

    /*
     * This method will go into the database and get all the information for the for the flights. The flights
     * information that is in the database will be used to create the Flight object using the Flight.java class.
     * The flight object will then be entered into an ArrayList of Flight objects.
     * The method will then return the ArrayList at the end.
     */
    public ArrayList<Flight> getFlightList() {
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

    /*
     * This method will search the database and look through the People table to find all Captains that are entered
     * into the database. This function will return the first and the last name of all found in the form of an
     * ArrayList of Strings.
     */
    public ArrayList<String> getAllCaptains () {
        DatabaseInfo databaseInfo = new DatabaseInfo();
        Connection conn = databaseInfo.getConnection();
        String query = "SELECT FirstName, LastName FROM People WHERE EmployeeType = 'Captain'";
        PreparedStatement preparedStatement = null;

        ArrayList<String> names = new ArrayList<String>();

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                names.add(firstName + " " + lastName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseInfo.closeConnection(conn);

        return names;
    }

    /*
     * This method will search the database and look through the People table to find all First Officers that are
     * entered into the database. This function will return the first and the last name of all found in the form of an
     * ArrayList of Strings.
     */
    public ArrayList<String> getAllFirstOfficers () {
        DatabaseInfo databaseInfo = new DatabaseInfo();
        Connection conn = databaseInfo.getConnection();
        String query = "SELECT FirstName, LastName FROM People WHERE EmployeeType = 'First Officer'";
        PreparedStatement preparedStatement = null;

        ArrayList<String> names = new ArrayList<String>();

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                names.add(firstName + " " + lastName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseInfo.closeConnection(conn);

        return names;
    }

    /*
     * This method will search the database and look through the People Table to find all flight attendants that are
     * entered into the database. This function will return the first and last name of all found in the form of an
     * ArrayList of Strings.
     */
    public ArrayList<String> getAllFlightAttendants () {
        DatabaseInfo databaseInfo = new DatabaseInfo();
        Connection conn = databaseInfo.getConnection();
        String query = "SELECT FirstName, LastName FROM People WHERE EmployeeType = 'Flight Attendant'";
        PreparedStatement preparedStatement = null;

        ArrayList<String> names = new ArrayList<String>();

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                names.add(firstName + " " + lastName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseInfo.closeConnection(conn);

        return names;
    }

    /*
     * This method will call the database and get the Airport Name of all Airports that are able to be flown to. The
     * method will then store then name of the airport as a String and enter it into an ArrayList of Strings.
     */
    public ArrayList<String> getAllAirports () {
        DatabaseInfo databaseInfo = new DatabaseInfo();
        Connection conn = databaseInfo.getConnection();
        String query = "SELECT AirportName FROM Airports";
        PreparedStatement preparedStatement = null;

        ArrayList<String> airports = new ArrayList<String>();

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                airports.add(resultSet.getString("AirportName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseInfo.closeConnection(conn);

        return airports;
    }

    /*
     * This method will return the objects of all the flight crew that are in the database.
     * This method will be different than the queries that are for getting just the strings that were used for the
     * comboBox list information on other forms.
     */
    public ArrayList<Person> getAllPersons () {
        ArrayList<Person> personArrayList = new ArrayList<>();

        DatabaseInfo databaseInfo = new DatabaseInfo();
        Connection connection = databaseInfo.getConnection();
        String query = "SELECT * FROM People";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int peopleID = resultSet.getInt("PeopleID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String type = resultSet.getString("EmployeeType");
                String qualification = resultSet.getString("PilotQualification");

                Person person = new Person(peopleID, firstName, lastName, type, qualification);
                personArrayList.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseInfo.closeConnection(connection);
        return personArrayList;
    }

    public ArrayList<String> getAllPersonsStrings () {
        ArrayList<String> persons = new ArrayList<>();

        DatabaseInfo databaseInfo = new DatabaseInfo();
        Connection connection = databaseInfo.getConnection();
        String query = "SELECT * FROM People";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int peopleID = resultSet.getInt("PeopleID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");

                String addToList = lastName + ", " + firstName + " [" + peopleID + "]";
                persons.add(addToList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseInfo.closeConnection(connection);
        return persons;
    }


    /*
     * This method will get all of the captains from the People table in the database. The method will then take
     * the information to create objects of the captain and store it in an ArrayList.
     */
    public ArrayList<Captain> getCaptainsOBJ () {
        DatabaseInfo databaseInfo = new DatabaseInfo();
        Connection connection = databaseInfo.getConnection();
        String query = "SELECT * FROM People WHERE EmployeeType = 'Captain'";

        ArrayList<Captain> captains = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int peopleID = resultSet.getInt("PeopleID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String type = resultSet.getString("EmployeeType");
                String qualification = resultSet.getString("PilotQualification");

                Captain captain = new Captain(peopleID, firstName, lastName, type, qualification);
                captains.add(captain);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseInfo.closeConnection(connection);

        return captains;
    }

    /*
     * This method will get all of the first officers from the People table in the database. The method will then take
     * the information to create objects of the first officer and store it in an ArrayList.
     */
    public ArrayList<FirstOfficer> getFirstOfficersOBJ () {
        DatabaseInfo databaseInfo = new DatabaseInfo();
        Connection connection = databaseInfo.getConnection();
        String query = "SELECT * FROM People WHERE EmployeeType = 'FirstOfficer'";

        ArrayList<FirstOfficer> firstOfficers = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int personID = resultSet.getInt("PeopleID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String type = resultSet.getString("EmployeeType");
                String qualification = resultSet.getString("PilotQualification");

                FirstOfficer firstOfficer = new FirstOfficer(personID, firstName, lastName, type,qualification);
                firstOfficers.add(firstOfficer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseInfo.closeConnection(connection);

        return firstOfficers;
    }

    /*
     * This method will get all of the flight attendants from the People table in the database. The method will then take
     * the information to create objects of the flight attendant and store it in an ArrayList.
     */
    public ArrayList<FlightAttendant> getFlightAttendantsOBJ () {
        DatabaseInfo databaseInfo = new DatabaseInfo();
        Connection connection = databaseInfo.getConnection();
        String query = "SELECT * FROM People WHERE EmployeeType = 'FlightAttendant'";

        ArrayList<FlightAttendant> flightAttendants = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int personID = resultSet.getInt("PeopleID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String type = resultSet.getString("EmployeeType");
                String qualification = resultSet.getString("PilotQualification");

                FlightAttendant flightAttendant = new FlightAttendant(personID, firstName, lastName, type, qualification);
                flightAttendants.add(flightAttendant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseInfo.closeConnection(connection);

        return flightAttendants;
    }

    /*
     * This method will display all information in the database to the console. It is not designed to be called upon for
     * the actual operation of the program, but is useful for the developers if they can't access the database remotely.
     */
    public static void displayDatabase() {
        /*Display Flights Table*/
        DatabaseInfo databaseInfo = new DatabaseInfo();
        Connection conn = databaseInfo.getConnection();
        String query = "SELECT * FROM Flights";
        PreparedStatement preparedStatement = null;

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            System.out.format("FLIGHTS TABLE\n%-10s %-15s %-15s %-15s %-15s %-20s %-12s %-20s %-20s %-20s %-20s %-20s " +
                            "%-20s %-12s %-20s\n", "FlightsID", "PlaneNumber", "FlightNumber", "Weather", "OriginAirport",
                    "DestinationAirport", "FlightDate", "ScheduledDeparture", "EstimatedDeparture", "ActualDeparture",
                    "ScheduledArrival", "EstimatedArrival", "ActualArrival", "TimeStatus", "CreatedBy");
            while (resultSet.next()) {
                int flightID = resultSet.getInt("FlightsID");
                String planeNum = resultSet.getString("PlaneNumber");
                String flightNum = resultSet.getString("FlightNumber");
                String weather = resultSet.getString("Weather");
                String originAirport = resultSet.getString("OriginAirport");
                String destAirport = resultSet.getString("DestinationAirport");
                Date date = resultSet.getDate("ScheduledDeparture");
                Time scheduledDeparture = resultSet.getTime("ScheduledDeparture");
                Time estimatedDeparture = resultSet.getTime("EstimatedDeparture");
                Time actualDeparture = resultSet.getTime("ActualDeparture");
                Time scheduledArrival = resultSet.getTime("ScheduledArrival");
                Time estimatedArrival = resultSet.getTime("EstimatedArrival");
                Time actualArrival = resultSet.getTime("ActualArrival");
                String status = resultSet.getString("TimeStatus");
                String created = resultSet.getString("CreatedBy");

                System.out.format("%-10d %-15s %-15s %-15s %-15s %-20s %-12tD %-20tr %-20tr %-20tr %-20tr %-20tr %-20tr " +
                                "%-12s %-20s\n", flightID, planeNum, flightNum, weather, originAirport, destAirport,
                        date, scheduledDeparture, estimatedDeparture, actualDeparture, scheduledArrival, estimatedArrival,
                        actualArrival, status, created);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*Display Airports Table*/
        query = "SELECT * FROM Airports";

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            System.out.format("\nAIRPORTS TABLE\n%-12s %-12s %-10s %-18s %-8s\n", "AirportsID", "AirportName", "TimeZone", "City", "State");
            while (resultSet.next()) {
                int airportID = resultSet.getInt("AirportsID");
                String airportName = resultSet.getString("AirportName");
                String timezone = resultSet.getString("TimeZone");
                String city = resultSet.getString("City");
                String state = resultSet.getString("State");

                System.out.format("%-12d %-12s %-10s %-18s %-8s\n", airportID, airportName, timezone, city, state);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*Display Airplanes Table*/
        query = "SELECT * FROM Airplanes";

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            System.out.format("\nAIRPLANES TABLE\n%-12s %-12s %-15s %-10s %-15s %-10s\n", "AirplanesID", "AirportsID",
                    "AirplaneType", "Location", "InMaintenance", "Capacity");
            while (resultSet.next()) {
                int airplaneID = resultSet.getInt("AirplanesID");
                int airportsID = resultSet.getInt("AirportsID");
                String airplaneType = resultSet.getString("AirplaneType");
                String location = resultSet.getString("Location");
                boolean maintenance = resultSet.getBoolean("InMaintenance");
                int capacity = resultSet.getInt("Capacity");

                System.out.format("%-12d %-12d %-15s %-10s %-15b %-10d\n", airplaneID, airportsID, airplaneType, location, maintenance, capacity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*Display People Table*/
        query = "SELECT * FROM People";

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            System.out.format("\nPEOPLE TABLE\n%-10s %-15s %-15s %-18s %-15s\n", "PeopleID", "FirstName", "LastName",
                    "EmployeeType", "PilotQualification");
            while (resultSet.next()) {
                int peopleID = resultSet.getInt("PeopleID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String type = resultSet.getString("EmployeeType");
                String qualification = resultSet.getString("PilotQualification");

                System.out.format("%-10d %-15s %-15s %-18s %-15s\n", peopleID, firstName, lastName, type, qualification);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*Display StandbyCrew Table*/
        query = "SELECT * FROM StandbyCrew";

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            System.out.format("\nSTANDBYCREW TABLE\n%-15s %-10s %-12s\n", "StandbyCrewID", "PeopleID",
                    "AirportsID");
            while (resultSet.next()) {
                int crewID = resultSet.getInt("StandbyCrewID");
                int peopleID = resultSet.getInt("PeopleID");
                int airportsID = resultSet.getInt("AirportsID");

                System.out.format("%-15d %-10d %-12d\n", crewID, peopleID, airportsID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*Display Crew Table*/
        query = "SELECT * FROM Crew";

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            System.out.format("\nCREW TABLE\n%-8s %-10s %-12s\n", "CrewID", "PeopleID",
                    "FlightsID");
            while (resultSet.next()) {
                int crewID = resultSet.getInt("CrewID");
                int peopleID = resultSet.getInt("PeopleID");
                int flightID = resultSet.getInt("FlightsID");

                System.out.format("%-8d %-10d %-12d\n", crewID, peopleID, flightID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*Display Users Table*/
        query = "SELECT * FROM Users";

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            System.out.format("\nUSERS TABLE\n%-10s %-20s %-20s\n", "UsersID", "Username", "Password");
            while (resultSet.next()) {
                int userID = resultSet.getInt("UsersID");
                String username = resultSet.getString("Username");
                String password = resultSet.getString("Password");

                System.out.format("%-10d %-20s %-20s\n", userID, username, password);
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
    }

    /*public static void main(String[] args) {
        displayDatabase();
    }*/
}