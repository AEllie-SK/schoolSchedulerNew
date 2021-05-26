package Models;

import java.util.Date;

public class ModelCourses {
    private String courseTitle;
    private Date startDate;
    private Date endDate;
    private String status;
    private String instructorName;
    private String courseNote;

    //Constructor

    public ModelCourses(String courseTitle, Date startDate, Date endDate, String status, String instructorName,String courseNote) {
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
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

    public String getCourseNotee() {
        return courseNote;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public void setCourseNote(String courseNote) {
        this.courseNote = courseNote;
    }
}
