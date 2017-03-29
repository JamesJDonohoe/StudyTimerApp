package com.example.james.assignment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

/**
 *  James Donohoe C13452398
 *  This class displays the stop watch. It had 3 methods for each button and it uses the inbuilt
 *  chronometer feature in android studio.
 *  https://developer.android.com/reference/android/widget/Chronometer.html
 *  I used this site as a main guide to help http://abhiandroid.com/ui/chronometer
 *  I use the chronometer as a button to save my time. In the AddData() method it sets an onClick
 *  listener on the chronometer which brings up an alert asking the user to add the data, which then
 *  gets added to its own table in the database and is then displayed in a list view.
 */
public class StopWatch extends AppCompatActivity {

    DBHelper myDB;

    // Declaring for stop watch
    long timeWhenStopped = 0;
    boolean stopClicked;
    Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);
        //calling the constructor to create database and table
        myDB = new DBHelper(this);
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        AddData();

        //Exit button
        Button ExitTime;
        ExitTime = (Button)findViewById(R.id.exit_button);
        ExitTime.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StopWatch.this, Page1.class);
                startActivity(intent);
                finish();
            }
        });//end of exit button

    }

    //Method for reset button
    public void resetButtonClick(View v) {
        //when the reset button is pressed is resets the chronometer text and the seconds text
        chronometer.setBase(SystemClock.elapsedRealtime());
        timeWhenStopped = 0;
        TextView secondsText = (TextView) findViewById(R.id.StopTimeText);
        secondsText.setText("0 seconds");
    }//end of reset method

    //Method for start button
    public void startButtonClick(View v) {
        chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
        chronometer.start();
        stopClicked = false;
    }//end of start method

    //Method for stop button which sets the text of seconds text to the stop it was stopped at
    public void stopButtonClick(View v){
        if (!stopClicked)  {
            //When it is pressed it sets the text to the time displayed in the chronometer
            TextView secondsText = (TextView) findViewById(R.id.StopTimeText);
            timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
            int seconds = (int) timeWhenStopped / 1000;
            //setting the text underneath chronometer to the time displays
            secondsText.setText( Math.abs(seconds) + " seconds");
            //stops the time
            chronometer.stop();
            stopClicked = true;
        }//end of if
    }//end of stop method

    //If the user presses the chronometer (the time in the middle) an alert box will ask the user
    //if they want to add the data or not
    public void AddData() {
        chronometer.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(StopWatch.this);
            alertDialog.setMessage("Do you want to add time to your records?").setCancelable(false)

                //Yes button to add time to database
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Inserting time into database
                        boolean TimeInsert = myDB.insertTime(chronometer.getText().toString());

                        if (TimeInsert == true)
                            Toast.makeText
                                    (StopWatch.this, "Added", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText
                                    (StopWatch.this, "ERROR, not added",
                                            Toast.LENGTH_LONG).show();
                                }
                            })//end of yes button

                    //No button to cancel
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });//end of no button
                    AlertDialog alert = alertDialog.create();
                    alert.setTitle("Add Time");
                    alert.show();
                }
            });//end of onclick
    }//emd of add data method
}//end of class