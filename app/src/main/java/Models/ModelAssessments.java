package Models;

import java.util.Date;

public class ModelAssessments {
    private String assessmentTitle;
    private Date dateScheduled;
    private String examType;
    //exam Type OA or Performance Based
    private String courseTitle;

    //to String


    @Override
    public String toString() {
        return "ModelAssessments{" +
                "assessmentTitle='" + assessmentTitle + '\'' +
                ", dateScheduled=" + dateScheduled +
                ", examType='" + examType + '\'' +
                ", courseTitle='" + courseTitle + '\'' +
                '}';
    }

    //Constructors
    public ModelAssessments(String assessmentTitle, Date dateScheduled, String examType, String courseTitle) {
        this.assessmentTitle = assessmentTitle;
        this.dateScheduled = dateScheduled;
        this.examType = examType;
        this.courseTitle = courseTitle;
    }

    //Getters & Setters

    public String getAssessmentTitle() {
        return assessmentTitle;
    }

    public void setAssessmentTitle(String assessmentTitle) {
        this.assessmentTitle = assessmentTitle;
    }

    public Date getDateScheduled() {
        return dateScheduled;
    }

    public void setDateScheduled(Date dateScheduled) {
        this.dateScheduled = dateScheduled;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }
}
