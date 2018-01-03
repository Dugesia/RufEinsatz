package com.example.meyer.rufeinsatz;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class InputActivity extends AppCompatActivity {

    EntryDB entryDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        entryDB = Room.databaseBuilder(getApplicationContext(), EntryDB.class,"Daten_DB").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        getInformation();

        Spinner spinner =(Spinner)findViewById(R.id.input_spinner);
        String[] durationList = getResources().getStringArray(R.array.duration);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, durationList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(dataAdapter);
    }

    void getInformation()
    {
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd  HH:mm");
        EditText etDate=(EditText)findViewById(R.id.etDate);
        etDate.setText(simpleDateFormat.format(Calendar.getInstance().getTime()));
    }

    public void Speichern(View view) {
        try {
            ItemEntry itemEntry=new ItemEntry();
            itemEntry.setDate(((EditText)findViewById(R.id.etDate)).getText().toString());
            itemEntry.setDuration(((Spinner)findViewById(R.id.input_spinner)).getSelectedItem().toString());
            itemEntry.setTask(((EditText)findViewById(R.id.etTask)).getText().toString());
            entryDB.daoAccess().insertEntry(itemEntry);
            setResult(0);
        } catch (Exception ex)
        {
            Log.e("sme",ex.getMessage());
            setResult(1);
        }
        Intent intent = new Intent(this,MainActivity.class);
        this.startActivity(intent);
    }
}
