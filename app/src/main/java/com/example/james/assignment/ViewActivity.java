package com.example.james.assignment;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 *  James Donohoe C13452398
 *  This class displays the data entered by the user in a list view using a custom adapter.
 *  This uses a custom adapter to display both the unique ID of the data and the data itself.
 *  The ID is needed to delete/ update the data in the database.
 *  I used these 2 sites to help mostly with the custom adapter.
 *  http://androidexample.com/How_To_Create_A_Custom_Listview_-_Android_Example/index.php?view=article_discription&aid=67
 *  https://dzone.com/articles/listview-data-sqlitedatabase
 */

public class ViewActivity extends AppCompatActivity
{

    DBHelper myDB;
    private ListAdapter Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        //Main menu button
        Button GoToMainActivity;
        GoToMainActivity = (Button) findViewById(R.id.MainButton1);
        GoToMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewActivity.this, Page1.class);
                startActivity(intent);
                finish();
            }

        });//end of menu button


        final ListView listView = (ListView)findViewById(R.id.listDB);
        myDB = new DBHelper(this);

        //Setting the arraylist to the GetCustomData class so it can populate the array
        final ArrayList<GetCustomData> arrayofData = new ArrayList<>();
        customAdapter myCustomAdapter =  new customAdapter(this,arrayofData);

        listView.setAdapter(myCustomAdapter);

        //Getting Data from cursor in database
        final Cursor data = myDB.getList();

        //This will get overwritten
        GetCustomData newData = new GetCustomData("android", "1234");

        //If there is no data it will print the message on screen else it will show all the data
        if(data.getCount() == 0){
            Toast.makeText(ViewActivity.this,"No Records Found, Go back and " +
                    "enter Data", Toast.LENGTH_LONG).show();
        }else while (data.moveToNext())
        {
            //getting the position of id and myData which is the id and data entered
            String id =  data.getString(0);
            String myData = data.getString(1);

            newData = new GetCustomData(id , myData) ;

            myCustomAdapter.add(newData);

        }//end of else while

        //OnItemClickListener letting all the data no matter the position to go to the stop watch class
        //Once its clicked it will go to the StopWatch Activity
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position >=0){
                    Intent intent = new Intent(ViewActivity.this, StopWatch.class);
                    startActivity(intent);
                    finish();
                }//end if
            }
        });//end onclick listener
    }//end on create
}//end class
