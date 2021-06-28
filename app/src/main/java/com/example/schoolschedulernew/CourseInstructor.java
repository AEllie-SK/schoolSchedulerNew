package com.example.schoolschedulernew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import DataBaseHelper.DOA_DatabaseHelper;
import Models.ModelCourseInstructor;

public class CourseInstructor extends AppCompatActivity {
    //reference to controls on layout (member variables)
    Button btn_Add, btn_ViewAll;
    EditText insertPhoneNumber, insertInstructorName,insertEmailText;
    ListView courseInstructorListView;

    ArrayAdapter instructorArrayAdapter;
    DOA_DatabaseHelper databaseHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_instructor);


        btn_Add = findViewById(R.id.btnAdd);
        btn_ViewAll = findViewById(R.id.btn_view_all);
        insertInstructorName = findViewById(R.id.insertInstructorName);
        insertPhoneNumber = findViewById(R.id.insertPhoneNumber);
        insertEmailText = findViewById(R.id.insertEmailText);
        courseInstructorListView = findViewById(R.id.courseInstructorListView);

        databaseHelper = new DOA_DatabaseHelper(CourseInstructor.this);


        //listeners
        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ModelCourseInstructor modelCourseInstructor;
                try {
                    modelCourseInstructor = new ModelCourseInstructor(-1 , insertInstructorName.getText().toString(),
                            Integer.parseInt(insertPhoneNumber.getText().toString()),insertEmailText.getText().toString() );
                    Toast.makeText(CourseInstructor.this, modelCourseInstructor.toString(), Toast.LENGTH_SHORT).show();

                }
                catch (Exception e) {
                    Toast.makeText(CourseInstructor.this, "Error creating customer", Toast.LENGTH_SHORT).show();
                    modelCourseInstructor = new ModelCourseInstructor(-1, "error", 0, "error");
                }

                DOA_DatabaseHelper databaseHelper = new DOA_DatabaseHelper(CourseInstructor.this);

                boolean success = databaseHelper.addNewInstructor(modelCourseInstructor);


                Toast.makeText(CourseInstructor.this, "Success" + success, Toast.LENGTH_SHORT).show();
            }
        });
        btn_ViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DOA_DatabaseHelper databaseHelper = new DOA_DatabaseHelper(CourseInstructor.this);

                ShowInstructorOnListView(databaseHelper);

            }
        });

        Toolbar toolbar = findViewById(R.id.instructorToolbar);
        setSupportActionBar(toolbar);

        registerForContextMenu(courseInstructorListView);



    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.list_view_menu, menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.lv_delete:
                DOA_DatabaseHelper databaseHelper = new DOA_DatabaseHelper(CourseInstructor.this);
                ModelCourseInstructor modelCourseInstructor = new ModelCourseInstructor();
                boolean success =  databaseHelper.deleteInstructor(modelCourseInstructor);
                Toast.makeText(this, "Delete selected" + success, Toast.LENGTH_SHORT).show();
                return true;
            case  R.id.lv_edit:
                Toast.makeText(this,"Edit selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.lv_view_details:
                Toast.makeText(this,"View details selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
        
    }

    private void ShowInstructorOnListView(DOA_DatabaseHelper databaseHelper2) {
        instructorArrayAdapter = new ArrayAdapter<ModelCourseInstructor>(CourseInstructor.this, android.R.layout.simple_list_item_1, databaseHelper2.getInstructorsToList());
        courseInstructorListView.setAdapter(instructorArrayAdapter);


    }


}