package DataBaseHelper;

import Models.ModelAssessments;
import Models.ModelCourseInstructor;
import Models.ModelCourses;
import Models.ModelTerm;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DOA_DatabaseHelper extends SQLiteOpenHelper {
    //DB version
    public static final int VERSION = 1;

    //Database names
    public static final String DB_SCHOOLSCHEDULE = "SchoolSchedule.db";

    //Table names
    public static final String INSTRUCTOR_TABLE = "INSTRUCTOR_TABLE";
    public static final String TERM_TABLE = "TERM_TABLE";
    public static final String ASSESSMENT_TABLE = "ASSESSMENT_TABLE";
    public static final String COURSE_TABLE = "COURSE_TABLE";

    //Instructor table columns
    public static final String INSTRUCTOR_ID = "instructorId";
    public static final String INSTRUCTOR_NAME = "instructorName";
    public static final String PHONE_NUMBER = "phoneNumber";
    public static final String EMAIL_ADDRESS = "emailAddress";

    //Term table columns
    public static final String TERM_ID = "termId";
    public static final String TERM_TITLE = "termTitle";
    public static final String T_START_DATE = "startDate";
    public static final String T_END_DATE = "endDate";

    //Assessment table columns
    public static final String ASSESSMENT_ID = "assessmentId";
    public static final String ASSESSMENT_NAME = "assessmentTitle";
    public static final String DUE_DATE = "dueDate";
    public static final String EXAM_TYPE = "examType";

    //Course table columns
    public static final String COURSE_ID = "courseId";
    public static final String COURSE_NAME = "courseName";
    public static final String C_START_DATE = "startDate";
    public static final String C_END_DATE = "endDate";
    public static final String STATUS = "status";
    public static final String C_NOTE = "note";

    String createInstructorTable =  " CREATE TABLE if not exists " + INSTRUCTOR_TABLE + " ( " +
            INSTRUCTOR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            INSTRUCTOR_NAME + " TEXT, " +
            PHONE_NUMBER + "  INT, " +
            EMAIL_ADDRESS + " TEXT); ";

    String createTermTable =  " CREATE TABLE if not exists " + TERM_TABLE + " ( " +
            TERM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TERM_TITLE + " TEXT, " +
            T_START_DATE + "  TEXT, " +
            T_END_DATE + " TEXT); ";

    String createAssessmentTable =  " CREATE TABLE if not exists " + ASSESSMENT_TABLE + " ( " +
            ASSESSMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ASSESSMENT_NAME + " TEXT, " +
            DUE_DATE + "  TEXT, " +
            EXAM_TYPE + " TEXT," +
            INSTRUCTOR_ID + " INTEGER, " +
            COURSE_ID +" INTEGER, " +
            " FOREIGN KEY ( " + INSTRUCTOR_ID + " ) REFERENCES " + INSTRUCTOR_TABLE + " ( " + INSTRUCTOR_ID + ")," +
            " FOREIGN KEY ( " + COURSE_ID + " ) REFERENCES " + COURSE_TABLE + " ( " + COURSE_ID + ")" +

            " ); ";

    String createCourseTable = " CREATE TABLE if not exists " + COURSE_TABLE + " ( " +
            COURSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COURSE_NAME + " TEXT, " +
            C_START_DATE + "  TEXT, " +
            C_END_DATE + " TEXT, " +
            STATUS + " TEXT, "+
            C_NOTE + " TEXT, " +
            INSTRUCTOR_ID + " INTEGER, " +
            TERM_ID + " INTEGER, " +
            " FOREIGN KEY ( " + INSTRUCTOR_ID + " ) REFERENCES " + INSTRUCTOR_TABLE + " ( " + INSTRUCTOR_ID + ")," +
            " FOREIGN KEY ( " + TERM_ID + " ) REFERENCES " + TERM_TABLE + " ( " + TERM_ID + ")" +
            ");" ;


    public DOA_DatabaseHelper(@Nullable Context context) {

        super(context,DB_SCHOOLSCHEDULE,null, VERSION);

    }

    //Create New Database
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(createInstructorTable);
        db.execSQL(createTermTable);
        db.execSQL(createAssessmentTable);
        db.execSQL(createCourseTable);


    }
    @Override
    public void onOpen(SQLiteDatabase db) {
        onCreate(db);
    }
    //Called When Version of DB Changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + INSTRUCTOR_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TERM_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ASSESSMENT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + COURSE_TABLE);


        onCreate(db);
    }

    //Add instructor rows
    public boolean addNewInstructor(ModelCourseInstructor modelCourseInstructor) {
        ContentValues cv = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();


        cv.put(INSTRUCTOR_NAME, modelCourseInstructor.getInstructorName());
        cv.put(PHONE_NUMBER, modelCourseInstructor.getPhoneNumber());
        cv.put(EMAIL_ADDRESS, modelCourseInstructor.getEmailAddress());

        long result = db.insert(INSTRUCTOR_TABLE, null, cv);
        if (result == -VERSION) {
            return false;
        } else {
            return true;
        }

    }
    public boolean updateInstructor(ModelCourseInstructor modelCourseInstructor) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(INSTRUCTOR_ID, modelCourseInstructor.getInstructorName());
        cv.put(INSTRUCTOR_NAME, modelCourseInstructor.getInstructorName());
        cv.put(PHONE_NUMBER, modelCourseInstructor.getPhoneNumber());
        cv.put(EMAIL_ADDRESS, modelCourseInstructor.getEmailAddress());

        String whereClause = "instructorId=?";
        String whereArgs[] = {INSTRUCTOR_ID};
        db.update(INSTRUCTOR_TABLE, cv, whereClause, whereArgs);

        return true;
    }

    public void deleteInstructor(ModelCourseInstructor modelCourseInstructor) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = "id=?";
        String whereArgs[] = {INSTRUCTOR_ID};
        db.delete("Items", whereClause, whereArgs);
    }

    //Add term rows
    public boolean addNewTerm(ModelTerm modelTerm) {
        ContentValues cv = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();


        cv.put(TERM_TITLE, modelTerm.getTermTitle());
        cv.put(T_START_DATE, modelTerm.getTermEndDate());
        cv.put(T_END_DATE, modelTerm.getTermEndDate());

        long result = db.insert(TERM_TABLE, null, cv);
        if (result == -VERSION) {
            return false;
        } else {
            return true;
        }

    }

    //Add assessment rows
    public boolean addNewAssessment(ModelAssessments modelAssessments) {
        ContentValues cv = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();

        cv.put(ASSESSMENT_NAME, modelAssessments.getAssessmentTitle());
        cv.put(DUE_DATE, modelAssessments.getDueDate());
        cv.put(EXAM_TYPE, modelAssessments.getExamType());



        long result = db.insert(ASSESSMENT_TABLE, null, cv);
        if (result == -VERSION) {
            return false;
        } else {
            return true;
        }

    }



    //Add course rows
    public boolean addNewCourse(ModelCourses modelCourses) {
        ContentValues cv = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();

        cv.put(COURSE_NAME, modelCourses.getCourseTitle());
        cv.put(C_START_DATE, modelCourses.getStartDate());
        cv.put(C_END_DATE, modelCourses.getEndDate());
        cv.put(STATUS, modelCourses.getStatus());
        cv.put(C_NOTE, modelCourses.getCourseNote());


        long result = db.insert(COURSE_TABLE, null, cv);
        if (result == -VERSION) {
            return false;
        } else {
            return true;
        }

    }

    //List for instructors
    public List<ModelCourseInstructor> getInstructorsToList(){
        List <ModelCourseInstructor> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM "+ INSTRUCTOR_TABLE;
        SQLiteDatabase db = getReadableDatabase();

        //cursor return type
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){

            //loop through results, create new customer object foe each row
            do{

                int id = cursor.getInt(0);
                String instructorName = cursor.getString(1);
                int phoneNumber = cursor.getInt(2);
                String emailAddress = cursor.getString(3);



                ModelCourseInstructor newInstructor = new ModelCourseInstructor(id , instructorName, phoneNumber, emailAddress);
                returnList.add(newInstructor);

            }
            while (cursor.moveToNext());
        }
        else {

            //failure to add to list

        }

        cursor.close();
        db.close();
        return returnList;
    }

    //List for Terms
    public List<ModelTerm> getTermsToList(){
        List <ModelTerm> returnList = new ArrayList<>();

        String termQuery = "SELECT * FROM "+ TERM_TABLE;
        SQLiteDatabase db = getReadableDatabase();

        //cursor return type
        Cursor cursor = db.rawQuery(termQuery, null);

        if(cursor.moveToFirst()){

            //loop through results, create new customer object foe each row
            do{

                int id = cursor.getInt(0);
                String termTitle = cursor.getString(1);
                String startDate = cursor.getString(2);
                String endDate = cursor.getString(3);



                ModelTerm newTerm = new ModelTerm(id , termTitle, startDate, endDate);
                returnList.add(newTerm);

            }
            while (cursor.moveToNext());
        }
        else {

            //failure to add to list

        }

        cursor.close();
        db.close();
        return returnList;
    }

    public List<ModelAssessments> getAssessmentsList(){
        List <ModelAssessments> returnList = new ArrayList<>();

        String assessmentQuery = "SELECT * FROM "+ ASSESSMENT_TABLE;
        SQLiteDatabase db = getReadableDatabase();

        //cursor return type
        Cursor cursor = db.rawQuery(assessmentQuery, null);

        if(cursor.moveToFirst()){

            //loop through results, create new customer object foe each row
            do{

                int id = cursor.getInt(0);
                String assessmentTitle = cursor.getString(1);
                String dueDate = cursor.getString(2);
                String examType = cursor.getString(3);
                String courseTitle = cursor.getString(4);



                ModelAssessments newAssessment = new ModelAssessments(id , assessmentTitle, dueDate, examType);
                returnList.add(newAssessment);

            }
            while (cursor.moveToNext());
        }
        else {

            //failure to add to list

        }

        cursor.close();
        db.close();
        return returnList;
    }
    public List<ModelCourses> getCoursesList(){
        List <ModelCourses> returnList = new ArrayList<>();

        String courseQuery = "SELECT * FROM "+ COURSE_TABLE;
        SQLiteDatabase db = getReadableDatabase();

        //cursor return type
        Cursor cursor = db.rawQuery(courseQuery, null);

        if(cursor.moveToFirst()){

            //loop through results, create new customer object foe each row
            do{

                int id = cursor.getInt(0);
                String courseName = cursor.getString(1);
                String startDate = cursor.getString(2);
                String endDate = cursor.getString(3);
                String status = cursor.getString(4);
                String note = cursor.getString(6);




                ModelCourses newCourse = new ModelCourses(id , courseName, startDate, endDate, status, note);
                returnList.add(newCourse);

            }
            while (cursor.moveToNext());
        }
        else {

            //failure to add to list

        }

        cursor.close();
        db.close();
        return returnList;
    }
}

