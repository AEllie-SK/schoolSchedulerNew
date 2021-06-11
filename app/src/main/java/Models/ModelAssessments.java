package Models;

import java.util.Date;

public class ModelAssessments {
    private int id;
    private String assessmentTitle;
    private String dueDate;
    private String examType;
    //exam Type OA or Performance Based

    //to String


    @Override
    public String toString() {
        return id + '\'' +
                ") Title: " + assessmentTitle + "\n" +
                " Due date: " + dueDate + "\n" +
                " Exam type: " + examType + "\n" ;
    }

    public ModelAssessments(int id, String assessmentTitle, String dueDate, String examType) {
        this.id = id;
        this.assessmentTitle = assessmentTitle;
        this.dueDate = dueDate;
        this.examType = examType;
    }


    //Getters & Setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAssessmentTitle() {
        return assessmentTitle;
    }

    public void setAssessmentTitle(String assessmentTitle) {
        this.assessmentTitle = assessmentTitle;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }


}
