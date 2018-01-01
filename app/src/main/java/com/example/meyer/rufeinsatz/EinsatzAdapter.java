package com.example.meyer.rufeinsatz;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by meyer on 01.01.2018.
 */

public class EinsatzAdapter extends ArrayAdapter<RufEinsatzEintrag> {

    public EinsatzAdapter(@NonNull Context context, int resource, @NonNull List<RufEinsatzEintrag> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        RufEinsatzEintrag Einsatz = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list, parent, false);
        }
        TextView tvDatum=(TextView)convertView.findViewById(R.id.Datum);
        tvDatum.setText(Einsatz.get_datum());
        TextView tvInfo =(TextView)convertView.findViewById(R.id.Info);
        tvInfo.setText(Einsatz.get_einsatz());

        return convertView;

    }
}
