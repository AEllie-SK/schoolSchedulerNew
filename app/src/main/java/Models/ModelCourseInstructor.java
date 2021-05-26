package Models;

public class ModelCourseInstructor {

    private String instructorName;
    private int phoneNumber;
    private String emailAddress;

    //Constructors

    public ModelCourseInstructor(String instructorName, int phoneNumber, String emailAddress) {
        this.instructorName = instructorName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }
    //to String Method to Print Content From Object Class

    @Override
    public String toString() {
        return "CourseInstructor{" +
                "instructorName='" + instructorName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }

    //Getters & Setters

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


