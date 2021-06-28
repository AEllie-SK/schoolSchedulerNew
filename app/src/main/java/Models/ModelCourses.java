package Models;

public class ModelCourses {
    private int id;
    private String courseTitle;
    private String startDate;
    private String endDate;
    private String status;
    private String courseNote;

    //Constructor

    public ModelCourses(int id, String courseTitle, String startDate, String endDate, String status, String courseNote) {
        this.id = id;
        this.courseTitle = courseTitle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.courseNote = courseNote;
    }



//To String

    @Override
    public String toString() {
        return id +
                " Title= " + courseTitle + '\n' +
                " startDate= " + startDate + '\n' +
                " endDate= " + endDate + '\n' +
                " status= " + status + '\n' +
                " courseNote= " + courseNote ;
    }

    public ModelCourses() {
    }
    //Getters & Setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getCourseNote() {
        return courseNote;
    }

    public void setCourseNote(String courseNote) {
        this.courseNote = courseNote;
    }
}
