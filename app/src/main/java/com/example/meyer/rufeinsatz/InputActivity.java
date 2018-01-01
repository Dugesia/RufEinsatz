package com.example.meyer.rufeinsatz;

import android.app.DatePickerDialog;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

public class InputActivity extends AppCompatActivity {

    EntryDB entryDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        entryDB = Room.databaseBuilder(getApplicationContext(), EntryDB.class,"Daten_DB").allowMainThreadQueries().build();
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
        try {
            RufEinsatzEintrag rufEinsatzEintrag=new RufEinsatzEintrag();
            rufEinsatzEintrag.set_datum(((EditText)findViewById(R.id.etDatum)).getText().toString());
            entryDB.daoAccess().insertEntry(rufEinsatzEintrag);

            Toast.makeText(this,"gespeichert",Toast.LENGTH_SHORT);

        } catch (Exception ex)
        {
            Log.e("sme\tspeichern",ex.getMessage());
            Toast.makeText(this,ex.getMessage(),Toast.LENGTH_SHORT);
        }


    }


}
