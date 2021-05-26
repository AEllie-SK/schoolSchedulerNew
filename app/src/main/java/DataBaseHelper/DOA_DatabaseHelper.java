package DataBaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import Models.ModelCourseInstructor;

public class DOA_DatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_SCHOOLSCHEDULE = "SchoolSchedule.db";
    public static final String INSTRUCTOR_TABLE = "INSTRUCTOR_TABLE";
    public static final String COLUMN_INSTRUCTOR_NAME = "instructorName";
    public static final String COLUMN_EMAIL_ADDRESS = "emailAddress";
    public static final String COLUMN_PHONE_NUMBER = "phoneNumber";

    public DOA_DatabaseHelper(@Nullable Context context) {
        super(context,DB_SCHOOLSCHEDULE,null,1);

    }

    //Create New Database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + INSTRUCTOR_TABLE + " (" + COLUMN_INSTRUCTOR_NAME + " TEXT PRIMARY KEY, " +
                COLUMN_PHONE_NUMBER + " INT, " + COLUMN_EMAIL_ADDRESS + " TEXT )";
        db.execSQL(createTable);
    }

    //Called When Version of DB Changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(ModelCourseInstructor courseInstructor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_INSTRUCTOR_NAME, courseInstructor.getInstructorName());
        cv.put(COLUMN_PHONE_NUMBER, courseInstructor.getPhoneNumber());
        cv.put(COLUMN_EMAIL_ADDRESS, courseInstructor.getEmailAddress());

        long insert = db.insert(INSTRUCTOR_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }
}