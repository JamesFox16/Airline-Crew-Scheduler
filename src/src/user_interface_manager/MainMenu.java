package user_interface_manager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame{
    private JPanel panel1;
    private JButton viewFlightsButton;
    private JButton signInButton;


    public MainMenu (String title) {
        setTitle(title);
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create
                LoginForm loginForm = new LoginForm("Crew Scheduler Login");
                loginForm.createUI();
                loginForm.setVisible(true);

                // Hide the current JFrame so the users computer doesn't get cluttered.
                //setVisible(false);
            }
        });
        viewFlightsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewFlightsForm viewFlightsForm = new ViewFlightsForm("View Flights");
                viewFlightsForm.createUI();
                viewFlightsForm.setVisible(true);
            }
        });
    }

    public void createUI () {
        this.add(panel1);
        setSize(1000,600);
    }
}
