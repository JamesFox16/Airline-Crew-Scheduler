import data_manager.FlightScheduled;
import user_interface_manager.MainMenu;
import javax.swing.*;

public class AirlineCrewScheduler {

    public static void main (String[] args) {

        // Run UI on separate thread.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainMenu mainMenu = new MainMenu("Flight Crew Scheduler");
                mainMenu.createUI();
                mainMenu.setVisible(true);
                mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                //FlightScheduled fs = new FlightScheduled();
            }
        });
    }
}
