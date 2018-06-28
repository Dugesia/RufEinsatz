package com.example.meyer.rufeinsatz;

import android.annotation.TargetApi;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
	EntryDB entryDB;
	DBAsync dbAsync;
	List<ItemEntry> itemEntries;
	ArrayList<ItemEntry> itemEntryArrayList;
	ListView lvEinsatz;
	Migration[] migrations = new Migration[]{DBAsync.MIGRATION_1_2, DBAsync.MIGRATION_2_3, DBAsync.MIGRATION_3_4, DBAsync.MIGRATION_4_5, DBAsync.MIGRATION_5_6};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		InitDB();

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				OpenInputActivity();
			}
		});
	}

	@Override
	protected void onPostResume() {
		refreshDB();
		super.onPostResume();
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
		if
		(id == R.id.action_report) {
			OpenReportActivity();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	public void OpenReportActivity()
	{
		Intent intent =new Intent(this,ReportActivity.class);
		startActivity(intent);
	}

	public void SendReport(String Message)
	{
		Intent intent = new Intent(Intent.ACTION_SENDTO);
		Uri uri = Uri.parse("mailto:");
		intent.setData(uri);
		intent.putExtra("subject", "Report");
		intent.putExtra("body", Message);
		startActivity(intent);
	}


    public void InitDB()
    {
        entryDB = Room.databaseBuilder(getApplicationContext(), EntryDB.class,"Daten_DB").addMigrations(migrations).build();
        dbAsync=new DBAsync(entryDB);
        lvEinsatz=(ListView) findViewById(R.id.EinsatzListe);
        refreshDB();
        setCLickListener(lvEinsatz);
    }

    void refreshDB()
    {
        itemEntries=dbAsync.getAll();
        ItemEntryAdapter einsatzAdapter =new ItemEntryAdapter(this,R.layout.item_list,itemEntries);
        lvEinsatz.setAdapter(einsatzAdapter);
    }

    void setCLickListener(View view)
    {
		((ListView)view).setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				showPopup(view,i);
			}
		});

    }

	public void showPopup(View v, final int index) {
		PopupMenu popup = new PopupMenu(this, v);
		popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem menuItem) {
				switch (menuItem.getItemId()) {
					case R.id.popupOpen:
						popUpOpen(index);
						return true;
					case R.id.popupDelete:
						popUpDelete(index);
						return true;
					default:
						return false;
				}
			}
		});
		MenuInflater inflater = popup.getMenuInflater();

		inflater.inflate(R.menu.menu_popup, popup.getMenu());

		popup.show();
	}


	void popUpOpen(int i)
	{
		ItemEntry itemEntry = (ItemEntry) ((ListView)findViewById(R.id.EinsatzListe)).getItemAtPosition(i);
		OpenChangeActivity(itemEntry.get_id());
		refreshDB();
	}

	void popUpDelete(int i)
	{
		ItemEntry itemEntry = (ItemEntry) ((ListView)findViewById(R.id.EinsatzListe)).getItemAtPosition(i);
		try {
			dbAsync.delete(itemEntry);
			refreshDB();
		}catch (Exception ex)
		{
			Toast.makeText(this,ex.getMessage(),Toast.LENGTH_SHORT);
		}
	}

    void OpenChangeActivity(int ID)
    {
        int i=1;
        Intent intent = new Intent(this,ChangeActivity.class);
        intent.putExtra("ID",ID);
        startActivityForResult(intent,i);
        if (i==0){
            Toast.makeText(this,"changed",Toast.LENGTH_SHORT);
        }
        refreshDB();
    }

    void OpenInputActivity()
    {
        int i=1;
        Intent intent = new Intent(this,InputActivity.class);
        startActivityForResult(intent,i);
        if (i==0){
            Toast.makeText(this,"saved",Toast.LENGTH_SHORT);
        }
        refreshDB();
    }

    public void mSettings(MenuItem item) {
        Toast.makeText(this,"keine Einstellungen",Toast.LENGTH_SHORT).show();
    }
}
