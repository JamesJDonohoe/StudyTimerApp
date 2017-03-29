package com.example.james.assignment;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 *  James Donohoe C13452398
 *  This is the main activity class which allows the user to navigate through the app.
 *  This class is all buttons which have intents which go to different activities.
 */

public class Page1 extends AppCompatActivity {
    DBHelper myDB;
    Button ExitActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);
        //calling the constructor to create database and table
        myDB = new DBHelper(this);

        //Creating a Listener and intent for addButton
        //This will go to the AddActivity Page to add to the database
        Button GoToAddActivity;
        GoToAddActivity = (Button) findViewById(R.id.addButton);
        GoToAddActivity.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(Page1.this, AddActivity.class);
                startActivity(intent);
                finish();
            }
        });//End of add button

        //This will go the ViewActivity page to view what was added in list form
        Button GoToViewActivity;
        GoToViewActivity = (Button) findViewById(R.id.viewButton);
        GoToViewActivity.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(Page1.this, ViewActivity.class);
                startActivity(intent);
                finish();
            }
        });//end of view button

        //This will go to TimeActivity and show me the times for each activity
        Button GoToTimeActivity;
        GoToTimeActivity = (Button) findViewById(R.id.viewTimeButton);
        GoToTimeActivity.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(Page1.this, TimeActivity.class);
                startActivity(intent);
                finish();
            }
        });//end of time button


    }//end of oncreate

    //When exit button is pressed a dialog box will open asking the user to exit or not
    public void dialogexit(View view) {
        //This will exit the app and print a message
        ExitActivity = (Button) findViewById(R.id.exitButton1);
        ExitActivity.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Page1.this);
                //Alert box asking the user if they want to exit the app or not
                alertDialog.setMessage("Do you want to exit?").setCancelable(false)
                        //If the user presses yes the app will close
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })//end of yes button
                        //If the user presses no the dialog box will close
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });//end of no button
                AlertDialog alert = alertDialog.create();
                alert.setTitle("Exit Application");
                alert.show();
            }//end of on click
        });//end of listener
    }//end of dialog method
}//end of class
