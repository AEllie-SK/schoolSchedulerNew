package com.example.schoolschedulernew;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.database.sqlite.SQLiteDatabase;
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
import Models.ModelCourseInstructor;
import Models.ModelTerm;

public class Term extends AppCompatActivity
{
    public static SQLiteDatabase SchoolSchedule;

    private DatePickerDialog datePickerDialog;
    private Button termStartDate, termEndDate, btnSubmitTerm, btnViewAll;
    private EditText etSelectTerm;
    private ListView termsListView;


    ArrayAdapter termArrayAdapter;
    DOA_DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term);




        etSelectTerm = findViewById(R.id.selectTermtxt);
        termStartDate = findViewById(R.id.startDatePicker);
        termStartDate.setText(getTodaysDate());
        termEndDate = findViewById(R.id.endDatePicker);
        termEndDate.setText(getTodaysDate());
        btnViewAll = findViewById(R.id.btnViewTerms);
        btnSubmitTerm = findViewById(R.id.btnSubmitTerm);
        termsListView = findViewById(R.id.termsListView);

        initDatePicker();

//        SQLiteDatabase db;
//        db = openOrCreateDatabase("SchoolSchedule.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
//        submitButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                // Inserting known Languages
//                Log.d("Insert: ", "Inserting ..");
//                db.insert();
//                db.insert(new values("value2"));
//                db.insert(new values("value3"));
//                db.insert(new values("value4"));
//                Log.d("Insert", "DataBase Successfully Updated");
//            }
//        });


        btnSubmitTerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ModelTerm modelTerm;
                try {
                    modelTerm = new ModelTerm(-1 , etSelectTerm.getText().toString(),termStartDate.getText().toString(),
                            termEndDate.getText().toString() );
                    Toast.makeText(Term.this, modelTerm.toString(), Toast.LENGTH_SHORT).show();

                }
                catch (Exception e) {
                    Toast.makeText(Term.this, "Error creating customer", Toast.LENGTH_SHORT).show();
                    modelTerm = new ModelTerm(-1, "error", "error", "error");
                }

                DOA_DatabaseHelper databaseHelper = new DOA_DatabaseHelper(Term.this);

                boolean success = databaseHelper.addNewTerm(modelTerm);

                ShowTermOnListView(databaseHelper);

                Toast.makeText(Term.this, "Success" + success, Toast.LENGTH_SHORT).show();
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
                termStartDate.setText(date);
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

    private void ShowTermOnListView(DOA_DatabaseHelper databaseHelper2) {
        termArrayAdapter = new ArrayAdapter<ModelTerm>(Term.this, android.R.layout.simple_list_item_1, databaseHelper2.getTermsToList());
        termsListView.setAdapter(termArrayAdapter);
    }
}