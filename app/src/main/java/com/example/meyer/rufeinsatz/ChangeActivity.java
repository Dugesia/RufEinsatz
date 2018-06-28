package com.example.meyer.rufeinsatz;

import android.arch.persistence.room.Room;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ChangeActivity extends AppCompatActivity {

    EntryDB entryDB;
    DBAsync dbAsync;
    ItemEntry itemEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        SetupView();
    }

    public void SetupView()
    {
        Spinner spinner = findViewById(R.id.change_spinner);
        Spinner spinnerDayType = findViewById(R.id.spinnerDayType);
        EditText etAufgabe = findViewById(R.id.etTask);
        EditText etDate = findViewById(R.id.etDate);
        CheckBox cbInvoce = findViewById(R.id.checkBox);

        entryDB = Room.databaseBuilder(getApplicationContext(), EntryDB.class,"Daten_DB").build();
        dbAsync=new DBAsync(entryDB);

        String[] durationList = getResources().getStringArray(R.array.duration);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, durationList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(dataAdapter);

        String[] arrayDayType = getResources().getStringArray(R.array.arrayDayType);
        ArrayAdapter<String> daDayType = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayDayType);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerDayType.setAdapter(daDayType);

        itemEntry = dbAsync.getByID(getIntent().getExtras().getInt("ID"));

		int i= ((Arrays.asList(arrayDayType)).indexOf(itemEntry.getDayType()));

        spinner.setSelection((Arrays.asList(durationList)).indexOf(itemEntry.getDuration()));
        spinnerDayType.setSelection((Arrays.asList(arrayDayType)).indexOf(itemEntry.getDayType()));

        cbInvoce.setChecked(itemEntry.getAbgerechnet());
        etAufgabe.setText(itemEntry.getTask());
        etDate.setText(itemEntry.getDate());
        cbInvoce.setChecked(itemEntry.getAbgerechnet());
    }

    public void Speichern(View view)
    {
        try {

            itemEntry.setDate(((EditText)findViewById(R.id.etDate)).getText().toString());
            itemEntry.setDuration(((Spinner)findViewById(R.id.change_spinner)).getSelectedItem().toString());
            itemEntry.setTask(((EditText)findViewById(R.id.etTask)).getText().toString());
            itemEntry.setAbgerechnet(((CheckBox)findViewById(R.id.checkBox)).isChecked());
			itemEntry.setDayType(((Spinner)findViewById(R.id.spinnerDayType)).getSelectedItem().toString());

            dbAsync.update(itemEntry);

            setResult(0);
        } catch (Exception ex)
        {
            setResult(1);
        }
        this.finish();
    }
}
