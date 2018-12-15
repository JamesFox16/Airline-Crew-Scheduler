package user_interface_manager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoggedInMenu extends JFrame {
    private JButton changeFlightButton;
    private JButton addFlightButton;
    private JButton cancelFlightButton;
    private JButton editCrewButton;
    private JPanel loggedInMenuPanel;
    private JButton logoutButton;
    private JLabel welcomeText;
    private JButton addStandbyCrewButton;
    private JButton addWorkerButton;
    private JButton viewAllWorkersButton;
    private JButton editWorkersButton;
    private JButton addCrewButton;


    public LoggedInMenu (String title, String username) {
        setTitle(title);
        welcomeText.setText(welcomeText.getText()+ " " + username);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        changeFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeFlightForm changeFlightForm = new ChangeFlightForm("Change Flight", username);
                changeFlightForm.createUI();
                changeFlightForm.setVisible(true);
            }
        });
        cancelFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CancelFlightForm cancelFlightForm = new CancelFlightForm("Cancel Flight", username);
                cancelFlightForm.createUI();
                cancelFlightForm.setVisible(true);
            }
        });
        addFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddFlightForm addFlightForm = new AddFlightForm("Add Flight", username);
                addFlightForm.createUI();
                addFlightForm.setVisible(true);
            }
        });
        editCrewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditCrewForm editCrewForm = new EditCrewForm("Edit Crew", username);
                editCrewForm.createUI();
                editCrewForm.setVisible(true);
            }
        });
        addStandbyCrewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddStandbyCrew addStandbyCrew = new AddStandbyCrew("Add Standby Crew", username);
                addStandbyCrew.createUI();
                addStandbyCrew.setVisible(true);
            }
        });

        addWorkerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddWorkerForm addWorkerForm = new AddWorkerForm("Add Employee", username);
                addWorkerForm.createUI();
                addWorkerForm.setVisible(true);
            }
        });

        viewAllWorkersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewAllWorkersForm viewAllWorkersForm = new ViewAllWorkersForm("View All Workers", username);
                viewAllWorkersForm.createUI();
                viewAllWorkersForm.setVisible(true);
            }
        });

        editWorkersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditWorkersForm editWorkersForm = new EditWorkersForm("Edit Workers", username);
                editWorkersForm.createUI();
                editWorkersForm.setVisible(true);
            }
        });

        addCrewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddCrewForm addCrewForm = new AddCrewForm("Add Crew", username);
                addCrewForm.createUI();
                addCrewForm.setVisible(true);
            }
        });
    }

    public void createUI () {
        this.add(loggedInMenuPanel);
        setSize(1000,600);
    }

    private void msgbox (String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
