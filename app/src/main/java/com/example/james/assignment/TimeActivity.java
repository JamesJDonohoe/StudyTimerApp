package com.example.james.assignment;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * James Donohoe C13452398
 * This class displays the time saved by the user into a list view where is can be deleted
 * (Cannot get the delete working)
 *
 */

public class TimeActivity extends AppCompatActivity {
    DBHelper myDB;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        //Button to go back to page1
        Button GoToMainActivity;
        GoToMainActivity = (Button) findViewById(R.id.MainButton2);
        GoToMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TimeActivity.this, Page1.class);
                startActivity(intent);
                finish();
            }
        });//End Intent

        //Getting id of my listview
        final ListView listView = (ListView) findViewById(R.id.timeList);
        myDB = new DBHelper(this);

        //Getting my array list
        final ArrayList<String> ListTime = new ArrayList<>();
        final Cursor dataTime = myDB.getTime();

        //If no records are found it prints a message
        if (dataTime.getCount() == 0) {
            Toast.makeText(TimeActivity.this, "No Records Found, Go back and " +
                    "enter Data", Toast.LENGTH_LONG).show();
        } else {

            while (dataTime.moveToNext()) {

                ListTime.add(dataTime.getString(0));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                        ListTime);
                listView.setAdapter(listAdapter);
            } //End While
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, final long id) {
                    if(position >=0){
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(TimeActivity.this);

                        //Alert box asking the user if they want to exit the app or not
                        alertDialog.setMessage("Do you want to delete time?").setCancelable(false)

                                //If the user presses yes the app will close
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //myDB.deleteTime(id);
                                        Integer deletedRows = myDB.deleteTime(id);
                                        if(deletedRows > 0)
                                            Toast.makeText(TimeActivity.this, "Data Deleted",Toast.LENGTH_LONG).show();
                                        else
                                            Toast.makeText(TimeActivity.this, "Data not Deleted",Toast.LENGTH_LONG).show();
                                    }//End on click for delete

                                })//End Yes button
                                //If the user presses no the dialog box will close
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });//End no button

                        AlertDialog alert = alertDialog.create();
                        alert.setTitle("Delete Times?");
                        alert.show();
                    }//End if for position
                }//End on item click
            });//End list on click listener
        }//End else
    }//End on create
}//class
