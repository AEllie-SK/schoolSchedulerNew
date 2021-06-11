package com.example.schoolschedulernew;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import DataBaseHelper.DOA_DatabaseHelper;
import Models.ModelAssessments;

public class Assessments extends AppCompatActivity {

    EditText etAssessmentTitle,etExamType, etViewCourseTitle;
    Button  dueDatePicker, btnSubmit, btnViewAll;
    ListView lvAssessments;

    DatePickerDialog datePickerDialog;

    ArrayAdapter customerArrayAdapter;
    DOA_DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessments);

        etAssessmentTitle = findViewById(R.id.etAssessmentTitle);
        etExamType = findViewById(R.id.etExamType);
        etViewCourseTitle = findViewById(R.id.etViewCourseTitle);
        dueDatePicker = findViewById(R.id.startDatePicker);
        btnSubmit = findViewById(R.id.btnSubmitAss);
        btnViewAll = findViewById(R.id.btnViewAll);
        lvAssessments = findViewById(R.id.lvAssessments);


        databaseHelper = new DOA_DatabaseHelper(Assessments.this);

        initDatePicker();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ModelAssessments modelAssessments;
                try {
                    modelAssessments = new ModelAssessments( -1, etAssessmentTitle.getText().toString(),
                            dueDatePicker.getText().toString(),
                            etExamType.getText().toString());
                    Toast.makeText(Assessments.this, modelAssessments.toString(), Toast.LENGTH_SHORT).show();

                }
                catch (Exception e) {
                    Toast.makeText(Assessments.this, "Error creating customer", Toast.LENGTH_SHORT).show();
                    modelAssessments = new ModelAssessments(0, "error", "error", "error");
                }

                DOA_DatabaseHelper databaseHelper = new DOA_DatabaseHelper(Assessments.this);

                boolean success = databaseHelper.addNewAssessment(modelAssessments);

                ShowCustomersOnListView(databaseHelper);

                Toast.makeText(Assessments.this, "Success" + success, Toast.LENGTH_SHORT).show();
            }
        });

        dueDatePicker.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // show the material date picker with
                        // supportable fragment manager to
                        // interact with dialog material date
                        // picker dialog fragments
                        datePickerDialog.show();
                    }
                });

    }
//        public void openDatePicker (View view){
//        }

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
                dueDatePicker.setText(date);
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


    private void ShowCustomersOnListView(DOA_DatabaseHelper databaseHelper2) {
        customerArrayAdapter = new ArrayAdapter<ModelAssessments>(Assessments.this, android.R.layout.simple_list_item_1, databaseHelper2.getAssessmentsList());
        lvAssessments.setAdapter(customerArrayAdapter);
    }

    }

