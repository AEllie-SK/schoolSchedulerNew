package com.example.schoolschedulernew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

import DataBaseHelper.DOA_DatabaseHelper;
import Models.ModelCourses;


public class Courses extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private DatePickerDialog datePickerDialog;
    private Button startDatePicker, endDatePicker, btnSubmitCourses, btnViewCourses;
    private EditText etCourseTitle, etInstructorName, etCourseNote;
    private Spinner courseStatusSpinner;
    private ListView lvCourses;

    ArrayAdapter courseArrayAdapter;
    DOA_DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        etCourseTitle = findViewById(R.id.etCourseTitle);
        courseStatusSpinner =findViewById(R.id.courseStatusSpinner);
        etInstructorName = findViewById(R.id.etInstructorName);
        etCourseNote = findViewById(R.id.etCourseNote);
        lvCourses = findViewById(R.id.lvCourses);

        btnSubmitCourses = findViewById(R.id.btnSubmitCourse);
        btnViewCourses = findViewById(R.id.btnViewCourses);
        startDatePicker = findViewById(R.id.startDatePicker);
        endDatePicker =findViewById(R.id.endDatePicker);

        startDatePicker.setText(getTodaysDate());
        endDatePicker.setText(getTodaysDate());

        //Spinner DropDown
        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(
                this,R.array.CoursesStatus, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseStatusSpinner.setAdapter(adapter);

        courseStatusSpinner.setOnItemSelectedListener(this);


        //Create Toolbar
        Toolbar toolbar = findViewById(R.id.coursesToolbar);
        setSupportActionBar(toolbar);
//Tool Bar Not Working (Need a Miracle)


        initDatePicker();
        databaseHelper = new DOA_DatabaseHelper(Courses.this);


        //listeners
        btnSubmitCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ModelCourses modelCourses;
                try {
                    modelCourses = new ModelCourses(-1 , etCourseTitle.getText().toString(),
                            startDatePicker.getText().toString(),
                            endDatePicker.getText().toString(),
                            courseStatusSpinner.getSelectedItem().toString(),
                            etCourseNote.getText().toString());

                    Toast.makeText(Courses.this, modelCourses.toString(), Toast.LENGTH_SHORT).show();

                }
                catch (Exception e) {
                    Toast.makeText(Courses.this, "Error creating customer", Toast.LENGTH_SHORT).show();
                    modelCourses = new ModelCourses(-1, "error", "error", "error","error","error");
                }

                DOA_DatabaseHelper databaseHelper = new DOA_DatabaseHelper(Courses.this);

                boolean success = databaseHelper.addNewCourse(modelCourses);

                ShowCoursesOnListView(databaseHelper);

                Toast.makeText(Courses.this, "Success" + success, Toast.LENGTH_SHORT).show();
            }
        });
        startDatePicker.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        datePickerDialog.show();
                    }
                });
        endDatePicker.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        datePickerDialog.show();
                    }
                });
    }

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                startDatePicker.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }



    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }

    //Spinner Toast
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text =parent.getItemAtPosition(position).toString();
        Toast.makeText(this, "Selected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
        private void ShowCoursesOnListView(DOA_DatabaseHelper databaseHelper2) {
            courseArrayAdapter = new ArrayAdapter<ModelCourses>(
                    Courses.this, android.R.layout.simple_list_item_1, databaseHelper2.getCoursesList());
            lvCourses.setAdapter(courseArrayAdapter);

            lvCourses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(Courses.this, "i am tayad!", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

