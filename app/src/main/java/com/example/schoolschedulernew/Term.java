package com.example.schoolschedulernew;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Calendar;

import DataBaseHelper.DOA_DatabaseHelper;
import Models.ModelTerm;


public class Term extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private DatePickerDialog startDatePickerDialog, endDatePickerDialog;
    private Button startDatePicker, endDatePicker, btnSubmitTerm, btnViewTerms;
    private EditText etTermTitle;
    private ListView lvTerm;

//    int DATE_PICKER_TO = 0;
//    int DATE_PICKER_FROM = 1;
//    private static final int    DIALOG_DATE_PICKER  = 100;
//    private int       datePickerInput;

    ArrayAdapter termArrayAdapter;
    DOA_DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term);

        etTermTitle = findViewById(R.id.etTermTitle);

        btnSubmitTerm = findViewById(R.id.btnSubmitTerm);
        btnViewTerms = findViewById(R.id.btnViewTerms);
        startDatePicker = findViewById(R.id.startDatePicker);
        endDatePicker =findViewById(R.id.endDatePicker);
        lvTerm = findViewById(R.id.termsListView);

        startDatePicker.setText(getTodaysDate());
        endDatePicker.setText(getTodaysDate());




        //Create Toolbar
//        Toolbar toolbar = findViewById(R.id.coursesToolbar);
//        setSupportActionBar(toolbar);
//Tool Bar Not Working (Need a Miracle)


        initDatePicker();
        databaseHelper = new DOA_DatabaseHelper(Term.this);




        //listeners
        btnSubmitTerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ModelTerm modelTerm;
                try {
                    modelTerm = new ModelTerm(-1 , etTermTitle.getText().toString(),
                            startDatePicker.getText().toString(),
                            endDatePicker.getText().toString());

                    Toast.makeText(Term.this, modelTerm.toString(), Toast.LENGTH_SHORT).show();

                }
                catch (Exception e) {
                    Toast.makeText(Term.this, "Error creating customer", Toast.LENGTH_SHORT).show();
                    modelTerm = new ModelTerm(-1, "error", "error", "error");
                }

                DOA_DatabaseHelper databaseHelper = new DOA_DatabaseHelper(Term.this);

                boolean success = databaseHelper.addNewTerm(modelTerm);

                ShowTermsOnListView(databaseHelper);

                Toast.makeText(Term.this, "Success" + success, Toast.LENGTH_SHORT).show();
            }
        });
        startDatePicker.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        startDatePickerDialog.show();
                    }
                });
        endDatePicker.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        endDatePickerDialog.show();
                    }
                });

    }

    private String getTodaysDate()
    {
        Calendar today = Calendar.getInstance();
        int year = today.get(Calendar.YEAR);
        int month = today.get(Calendar.MONTH);
        month = month + 1;
        int day = today.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }
    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener startDateSetListener, endDateSetListener;
        startDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                month = month + 1;
                String date = makeDateString(dayOfMonth, month, year);
                startDatePicker.setText(date);
            }
        };
        endDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                month = month + 1;
                String date = makeDateString(dayOfMonth, month, year);
                endDatePicker.setText(date);
            }
        };





        Calendar cal = Calendar.getInstance();
        int sYear = cal.get(Calendar.YEAR);
        int sMonth = cal.get(Calendar.MONTH);
        int sDay = cal.get(Calendar.DAY_OF_MONTH);

        int sStyle = AlertDialog.THEME_HOLO_LIGHT;

        startDatePickerDialog = new DatePickerDialog(this, sStyle, startDateSetListener, sYear, sMonth, sDay);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

//        Calendar eCal = Calendar.getInstance();
        int eYear = cal.get(Calendar.YEAR);
        int eMonth = cal.get(Calendar.MONTH);
        int eDay = cal.get(Calendar.DAY_OF_MONTH);

        int eStyle = AlertDialog.THEME_HOLO_LIGHT;

        endDatePickerDialog = new DatePickerDialog(this, eStyle, endDateSetListener, eYear, eMonth, eDay);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());



//        @Override
//        protected Dialog onCreateDialog (int id){
//
//            switch (id) {
//                case DATE_PICKER_FROM:
//                    return new DatePickerDialog(this, startDateSetListener, sYear, sMonth, sDay);
//                case DATE_PICKER_TO:
//                    return new DatePickerDialog(this, endDateSetListener, eYear, eMonth, eDay);
//            }
//            return null;
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
        startDatePickerDialog.show();
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
    private void ShowTermsOnListView(DOA_DatabaseHelper databaseHelper2) {
        termArrayAdapter = new ArrayAdapter<ModelTerm>(
                Term.this, android.R.layout.simple_list_item_1, databaseHelper2.getTermsToList());
        lvTerm.setAdapter(termArrayAdapter);

        lvTerm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Term.this, "i am tayad!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}



