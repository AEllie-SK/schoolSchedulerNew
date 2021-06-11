package Models;

public class ModelCourseInstructor {

    private int id;
    private String instructorName;
    private int phoneNumber;
    private String emailAddress;

    //Constructors

    public ModelCourseInstructor(int id, String instructorName, int phoneNumber, String emailAddress) {
        this.id = id;
        this.instructorName = instructorName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }
    //to String Method to Print Content From Object Class

    public ModelCourseInstructor(){
    }

    @Override
    public String toString() {
        return id +
                ") " + instructorName + '\'' +
                ", Tel:" + phoneNumber +
                ", Email:'" + emailAddress + '\'' +
                '}';
    }

    //Getters & Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}


