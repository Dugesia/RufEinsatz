package com.example.meyer.rufeinsatz;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import java.util.List;

public class ReportActivity extends AppCompatActivity {

	EntryDB entryDB;
	DBAsync dbAsync;
	String Html="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_report);

		entryDB = Room.databaseBuilder(getApplicationContext(), EntryDB.class,"Daten_DB").build();
		dbAsync=new DBAsync(entryDB);


		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabSend);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				SendReport();
			}
		});


		WebView wbReport=findViewById(R.id.wbReport);

		Html=Html+"<html><table>";

		List<ItemEntry> itemEntries = dbAsync.getAll();
		Html=Html+"<tr bgcolor='#dddddd'>";
		Html=Html+"<td>Datum</td>";
		Html=Html+"<td>Dauer</td>";
		Html=Html+"<td>Aufgabe</td>";
		Html=Html+"</tr>";
		for(int i=0;i<itemEntries.size();i++)
		{
			Html=Html+"<tr>";
			Html=Html+"<td>"+itemEntries.get(i).getDate()+"</td>";
			Html=Html+"<td>"+itemEntries.get(i).getDuration()+"</td>";
			Html=Html+"<td>"+itemEntries.get(i).getTask()+"</td>";
			Html=Html+"</tr>";
		}
		Html=Html+"<table><html>";

		wbReport.loadData(Html, "text/html", "UTF-8");

	}

	public void SendReport()
	{
		Intent intent = new Intent(Intent.ACTION_SENDTO);
		Uri uri = Uri.parse("mailto:");
		intent.setData(uri);
		intent.putExtra("subject", "Report");
		intent.putExtra("body", Html);
		startActivity(intent);
	}
}
