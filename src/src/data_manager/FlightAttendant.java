package data_manager;

public class FlightAttendant extends Person {

    int personID;
    String firstName;
    String lastName;
    String type;
    String qualification;

    public FlightAttendant (int personID, String firstName, String lastName, String type, String qualification) {
        this.personID = personID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
        this.qualification = qualification;
    }

}