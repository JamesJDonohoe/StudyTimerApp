package com.example.james.assignment;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 *  James Donohoe C13452398
 *  This class creates a custom adapter which I can then use in my list.
 *  This reads in from another class, GetCustomData where ID and singleData are assigned data from
 *  a string array which gets its data from the DBHelper class.
 */

class customAdapter extends ArrayAdapter<GetCustomData>{


    public customAdapter(Context context, ArrayList<GetCustomData> ListData) {
        super(context,0 ,ListData);
    }

    @Override
    public View getView(int position,View convertView, ViewGroup parent)
    {


        // Get the data item for this position
        GetCustomData listData = getItem(position);

        // Check if an existing view if not it will inflate the view
        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row, parent, false);
        }


        //TextView for id
        TextView listID = (TextView) convertView.findViewById(R.id.listID);
        TextView txtContent = (TextView) convertView.findViewById(R.id.txtContent);

        //Populating the data
        listID.setText(listData.singleData);
        txtContent.setText(listData.id);
        return convertView;
    }
}
