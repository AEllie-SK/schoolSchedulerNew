package com.example.schoolschedulernew;

import DataBaseHelper.DOA_DatabaseHelper;
import Models.ModelCourseInstructor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.schoolschedulernew.R;

public class CourseInstructor extends AppCompatActivity {

    Button btnAdd, btnEdit, btnDelete;
    EditText insertInstructorName, insertPhoneNumber, insertEmailText;
    ListView courseInstructorListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_instructor);

        btnAdd =findViewById(R.id.btnAdd);
        btnEdit =findViewById(R.id.btnEdit);
        btnDelete =findViewById(R.id.btnDelete);
        insertInstructorName =findViewById(R.id.courseInstructor);
        insertPhoneNumber =findViewById(R.id.insertPhoneNumber);
        insertEmailText =findViewById(R.id.insertEmailText);
        courseInstructorListView =findViewById(R.id.courseInstructorListView);

        //Onclick Listeners
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelCourseInstructor courseInstructor;
                try {
                    courseInstructor = new ModelCourseInstructor(insertInstructorName.getText().toString(),
                            Integer.parseInt(insertPhoneNumber.getText().toString()),insertEmailText.getText().toString());
                    Toast.makeText(CourseInstructor.this, courseInstructor.toString(), Toast.LENGTH_SHORT).show();

                }catch (Exception e) {
                        Toast.makeText(CourseInstructor.this, "Error Adding Instructor", Toast.LENGTH_SHORT).show();
                        courseInstructor =new ModelCourseInstructor("error",0,"error");
            }
                DOA_DatabaseHelper databaseHelper =new DOA_DatabaseHelper(CourseInstructor.this);
                boolean success = databaseHelper.addOne(courseInstructor);
                Toast.makeText(CourseInstructor.this, "Instructor Added Successfully " +success, Toast.LENGTH_SHORT).show();
            }});
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CourseInstructor.this, "Edit Button", Toast.LENGTH_SHORT).show();

            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CourseInstructor.this, "Delete Button", Toast.LENGTH_SHORT).show();
            }
        });
    }
}