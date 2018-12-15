package user_interface_manager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddWorkerForm extends JFrame{
    private JPanel addWorkerPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JButton backButton;
    private JButton createEmployeeButton;
    private JComboBox comboBox1;
    private JComboBox comboBox2;

    public AddWorkerForm (String title, String username) {
        setTitle(title);
    }

    public void createUI () {

        createEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = textField1.getText();
                String lastName = textField2.getText();
                String employeeType = comboBox1.getSelectedItem().toString();
                String isPilot = comboBox2.getSelectedItem().toString();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        this.add(addWorkerPanel);
        setSize(1000,600);
    }
}
