package com.example.meyer.rufeinsatz;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by meyer on 01.01.2018.
 */

public class ItemEntryAdapter extends ArrayAdapter<ItemEntry> {

    public ItemEntryAdapter(@NonNull Context context, int resource, @NonNull List<ItemEntry> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ItemEntry Einsatz = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list, parent, false);
        }
        TextView tvDatum = convertView.findViewById(R.id.Datum);
        TextView tvDuration = convertView.findViewById(R.id.Dauer);
        TextView tvInfo =convertView.findViewById(R.id.Info);

        tvDatum.setText(Einsatz.getDate());
        tvDuration.setText(Einsatz.getDuration());
        tvInfo.setText(Einsatz.getTask());

        if(Einsatz.getAbgerechnet())
		{
            tvInfo.setTextColor(Color.LTGRAY);
		}
		else
		{
			tvInfo.setTextColor(Color.BLACK);
		}


		return convertView;
    }
}
