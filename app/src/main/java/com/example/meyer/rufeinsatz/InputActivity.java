package com.example.meyer.rufeinsatz;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class InputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        getInformation();
    }



    void getInformation()
    {
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd  HH:mm");
        EditText etDate=(EditText)findViewById(R.id.etDatum);

        etDate.setText(simpleDateFormat.format(Calendar.getInstance().getTime()));
    }

    public void DateClick(View view) {

    }



    public void Speichern(View view) {

    }
}
