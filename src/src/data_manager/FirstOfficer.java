package data_manager;

public class FirstOfficer extends Person {

    int id;
    String firstName;
    String lastName;
    String type;
    String qualification;

    public FirstOfficer (int id, String firstName, String lastName, String type, String qualification) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
        this.qualification = qualification;
    }
}
