package Models;

public class ModelCourses {
    private String courseTitle;
    private String startDate;
    private String endDate;
    private String status;
    private String instructorName;
    private String courseNote;

    //Constructor

    public ModelCourses(String courseTitle, String startDate, String endDate, String status, String instructorName, String courseNote) {
        this.courseTitle = courseTitle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.instructorName = instructorName;
        this.courseNote = courseNote;
    }
//To String

    @Override
    public String toString() {
        return "ModelCourses{" +
                "courseTitle='" + courseTitle + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status='" + status + '\'' +
                ", instructorName='" + instructorName + '\'' +
                ", courseNote='" + courseNote + '\'' +
                '}';
    }

    //Getters & Setters
    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public String getCourseNote() {
        return courseNote;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public void setCourseNote(String courseNote) {
        this.courseNote = courseNote;
    }
}
