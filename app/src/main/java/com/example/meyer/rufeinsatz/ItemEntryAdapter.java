package com.example.meyer.rufeinsatz;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
        TextView tvDatum=(TextView)convertView.findViewById(R.id.Datum);
        tvDatum.setText(Einsatz.getDate());
        TextView tvDuration =(TextView)convertView.findViewById(R.id.Dauer);
        tvDuration.setText(Einsatz.getDuration());
        TextView tvInfo =(TextView)convertView.findViewById(R.id.Info);
        tvInfo.setText(Einsatz.getTask());

        return convertView;

    }
}
