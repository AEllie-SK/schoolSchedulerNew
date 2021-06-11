package com.example.schoolschedulernew;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import DataBaseHelper.DOA_DatabaseHelper;
import Models.ModelAssessments;

public class Assessments extends AppCompatActivity {

    EditText etAssessmentTitle,etExamType, etViewCourseTitle;
    Button  dueDatePicker, btnSubmit, btnViewAll;
    ListView lvAssessments;

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

    }
        public void openDatePicker (View view){
        }

    private void ShowCustomersOnListView(DOA_DatabaseHelper databaseHelper2) {
        customerArrayAdapter = new ArrayAdapter<ModelAssessments>(Assessments.this, android.R.layout.simple_list_item_1, databaseHelper2.getAssessmentsList());
        lvAssessments.setAdapter(customerArrayAdapter);
    }

    }

