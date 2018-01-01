package com.example.meyer.rufeinsatz;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EntryDB entryDB;
    List<RufEinsatzEintrag> EinsatzListe;
    ListView lvEinsatz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        entryDB = Room.databaseBuilder(getApplicationContext(), EntryDB.class,"Daten_DB").allowMainThreadQueries().build();
        lvEinsatz=(ListView) findViewById(R.id.EinsatzListe);
        GetData();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenInputActivity(view);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void OpenInputActivity( View view)
    {
        Intent intent = new Intent(this,InputActivity.class);
        startActivity(intent);
    }

    void GetData()
    {
        ArrayList<RufEinsatzEintrag> temp=new ArrayList<RufEinsatzEintrag>();

        try {
            EinsatzListe = entryDB.daoAccess().getAll();
            temp=ConvertToList(EinsatzListe);
            Log.e("sme","erfolg");

        } catch (Exception ex)
        {
            Log.e("sme",ex.getMessage().toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.item_list,R.id.Datum,temp);
        
    }

    public ArrayList<RufEinsatzEintrag> ConvertToList(List<RufEinsatzEintrag> Liste)
    {
        ArrayList<RufEinsatzEintrag> tmp=new ArrayList<RufEinsatzEintrag>();
        for (RufEinsatzEintrag item:Liste) {
            tmp.add(item);
        }
        return tmp;
    }


}
