package user_interface_manager;

import data_manager.Person;
import database_handler.DatabaseInfo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class ViewAllWorkersForm extends JFrame{
    private JPanel viewAllWorkersFPanel;
    private JTable table1;

    private String username;// Username of the account that is logged in while opening.

    // Constructor for the form to be called and displayed.
    public ViewAllWorkersForm (String title, String username) {
        setTitle(title);
        this.username = username;
    }

    // Create the user interface upon call of this form.
    public void createUI () {
        // Get the ArrayList
        DatabaseInfo databaseInfo = new DatabaseInfo();
        initializeTable(databaseInfo.getAllPersons());

        this.add(viewAllWorkersFPanel);
        setSize(1000,600);
    }

    /*
     * Method to initialize the table with all of the employees that are in the database. This is the method that will
     * be called when the form is created.
     */
    private void initializeTable(ArrayList<Person> persons) {
        //String[] columnNames = {"Employee ID", "First Name", "Last Name", "Employee Type", "Qualifications"};

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Employee ID");
        model.addColumn("First Name");
        model.addColumn("Last Name");
        model.addColumn("Employee Type");
        model.addColumn("Qualifications");

        for (int i=0; i<persons.size(); i++) {
            model.addRow(new Object[] {persons.get(i).getID(), persons.get(i).getFirstName(), persons.get(i).getLastName(),
                    persons.get(i).getEmployeeType(), persons.get(i).getQualification()});
        }
        table1.setModel(model);
    }

}
