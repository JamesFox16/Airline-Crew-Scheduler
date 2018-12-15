package data_manager;

public class Person {

    int id;
    String firstName;
    String lastName;
    String employeeType;
    String qualification;

     public Person (int id, String firstName, String lastName, String employeeType, String qualification) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeType = employeeType;
        this.qualification = qualification;
    }

    public Person () {

    }

    public int getID () {
         return this.id;
    }

    public void setID (int id) {
         this.id = id;
    }

    public String getFirstName () {
         return this.firstName;
    }

    public void setFirstName (String firstName) {
         this.firstName = firstName;
    }

    public String getLastName () {
         return this.lastName;
    }

    public void setLastName (String lastName) {
         this.lastName = lastName;
    }

    public String getEmployeeType () {
         return this.employeeType;
    }

    public void setEmployeeType (String employeeType) {
         this.employeeType = employeeType;
    }

    public String getQualification () {
         return this.qualification;
    }

    public void setQualification (String qualification) {
         this.qualification = qualification;
    }
}
