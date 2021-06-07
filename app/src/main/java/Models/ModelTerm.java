package Models;

import java.util.Date;

public class ModelTerm {
    private int id;
    private String termTitle;
    private String termStartDate;
    private String termEndDate;

    //Constructors


    public ModelTerm(int id, String termTitle, String termStartDate, String termEndDate) {
        this.id = id;
        this.termTitle = termTitle;
        this.termStartDate = termStartDate;
        this.termEndDate = termEndDate;
    }

    //toString Used for Printing Objects

    @Override
    public String toString() {
        return "ModelTerm{" +
                "id=" + id +
                ", termTitle='" + termTitle + '\'' +
                ", termStartDate=" + termStartDate +
                ", termEndDate=" + termEndDate +
                '}';
    }

    //Getters &Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTermTitle() {
        return termTitle;
    }

    public void setTermTitle(String termTitle) {
        this.termTitle = termTitle;
    }

    public String getTermStartDate() {
        return termStartDate;
    }

    public void setTermStartDate(String termStartDate) {
        this.termStartDate = termStartDate;
    }

    public String getTermEndDate() {
        return termEndDate;
    }

    public void setTermEndDate(String termEndDate) {
        this.termEndDate = termEndDate;
    }
}
