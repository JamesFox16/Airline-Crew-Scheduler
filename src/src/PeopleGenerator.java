import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database_handler.DatabaseInfo;

public class PeopleGenerator {
    private static ArrayList<ArrayList<String>> createPeople() {
        Scanner scnr;
        ArrayList<String> firstName = new ArrayList<String>();
        ArrayList<String> lastName = new ArrayList<String>();
        ArrayList<ArrayList<String>> lists = new ArrayList<ArrayList<String>>();
        try {
            scnr = new Scanner(new File("data/Names.txt"));

            while (scnr.hasNext()) {
                String input = scnr.nextLine();
                String tokens[] = input.split(" ");
                firstName.add(tokens[0]);
                lastName.add(tokens[1]);
            }
            lists.add(firstName);
            lists.add(lastName);
            scnr.close();
            return lists;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void addPeople(String firstName, String lastName, String employeeType, String qualification, int i) {
        DatabaseInfo databaseInfo = new DatabaseInfo();
        Connection conn = databaseInfo.getConnection();
        String query = "INSERT INTO People (FirstName,LastName,EmployeeType,PilotQualification,PeopleID) " +
                "VALUES (?,?,?,?,?)";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, employeeType);
            preparedStatement.setString(4, qualification);
            preparedStatement.setInt(5, i);
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

    public static void deletePeople () {
        DatabaseInfo databaseInfo = new DatabaseInfo();
        Connection conn = databaseInfo.getConnection();
        String query = "DELETE FROM StandbyCrew";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.executeUpdate();
            query = "DELETE FROM People";
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
        }
    }

    /*public static void main(String[] args) {
        ArrayList<ArrayList<String>> list = createPeople();
        ArrayList<String> firstNames = list.get(0);
        ArrayList<String> lastNames = list.get(1);

        String employeeType;
        String qualification;
        list.clear();
        for (int i = 0; i < firstNames.size(); i++) {
            if (i < 25) {
                if (i % 5 == 0) {
                    qualification = "Unqualified";
                } else {
                    qualification = "Qualified";
                }
                employeeType = "Captain";
            } else if (i < 50) {
                if (i % 5 == 0) {
                    qualification = "Unqualified";
                } else {
                    qualification = "Qualified";
                }
                employeeType = "First Officer";
            } else {
                employeeType = "Flight Attendant";
                qualification = "Unqualified";
            }
            System.out.printf("Iteration %d: %s, %s, %s, %s\n", i + 1, firstNames.get(i), lastNames.get(i), employeeType, qualification);
            addPeople(firstNames.get(i), lastNames.get(i), employeeType, qualification, i + 1);
        }
        //deletePeople();
    }*/
}