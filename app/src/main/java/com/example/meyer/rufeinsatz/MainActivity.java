package com.example.meyer.rufeinsatz;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EntryDB entryDB;
    List<ItemEntry> itemEntries;
    ArrayList<ItemEntry> itemEntryArrayList;
    ListView lvEinsatz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initDB();

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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void initDB()
    {
        entryDB = Room.databaseBuilder(getApplicationContext(), EntryDB.class,"Daten_DB").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        lvEinsatz=(ListView) findViewById(R.id.EinsatzListe);
        refreshDB();
        setLongClickListeners(lvEinsatz);
        setCLickListener(lvEinsatz);
    }

    void refreshDB()
    {
        itemEntryArrayList=new ArrayList<ItemEntry>();
        try {
            itemEntries = entryDB.daoAccess().getAll();
            itemEntryArrayList=ConvertToList(itemEntries);
        } catch (Exception ex)
        {
            Log.e("sme",ex.getMessage().toString());
        }
        ItemEntryAdapter einsatzAdapter =new ItemEntryAdapter(this,R.layout.item_list,itemEntryArrayList);
        lvEinsatz.setAdapter(einsatzAdapter);
    }

    void setLongClickListeners(View view){
        ((ListView)view).setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long id) {
                ListView lv= (ListView)findViewById(R.id.EinsatzListe);
                ItemEntry itemEntry=(ItemEntry)lv.getItemAtPosition(pos);
                try {
                    entryDB.daoAccess().deleteEntry(itemEntry);
                    refreshDB();
                }catch (Exception ex){
                    Log.e("sme",ex.getMessage());
                }
                return false;
            }
        });
    }

    void setCLickListener(View view)
    {
        ((ListView)view).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ItemEntry itemEntry = (ItemEntry) ((ListView)findViewById(R.id.EinsatzListe)).getItemAtPosition(i);
                Toast.makeText(view.getContext(),itemEntry.getTask(),Toast.LENGTH_LONG).show();
            }
        });
    }

    void OpenInputActivity(View view)
    {
        int i=1;
        Intent intent = new Intent(this,InputActivity.class);
        startActivityForResult(intent,i);
        if (i==0){
            Toast.makeText(this,"saved",Toast.LENGTH_SHORT);
        }
        refreshDB();
    }

    public ArrayList<ItemEntry> ConvertToList(List<ItemEntry> Liste)
    {
        ArrayList<ItemEntry> tmp=new ArrayList<ItemEntry>();
        for (ItemEntry item:Liste) {
            tmp.add(item);
        }
        return tmp;
    }

    void deleteItems(View view){
        Toast.makeText(this,"Delete",Toast.LENGTH_SHORT);
    }
}
