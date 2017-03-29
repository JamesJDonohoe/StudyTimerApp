package com.example.james.assignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 *  James Donohoe C13452398
 *  The AddActivity class takes the data from the table and adds it to the database.
 *  Data is also deleted and updated in this class.
 *  Unfortunately I could not get delete and update working by clicking the data in the list view.
 * To delete data the user must enter the number beside the data in the list view.
 * To update data the user must enter the number and then make the nessesary change they want.
 */

public class AddActivity extends AppCompatActivity {
    DBHelper myDB;

    EditText studyText, UpdateText;
    Button EnterButton, deleteButton, updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add1);
        //calling the constructor to create database and table
        myDB = new DBHelper(this);

        //Getting the ID of each button or edit text in the xml
        studyText = (EditText)findViewById(R.id.studyText);
        UpdateText = (EditText)findViewById(R.id.updateText);
        EnterButton = (Button)findViewById(R.id.EnterButton);
        updateButton = (Button)findViewById(R.id.btnUpdate);
        deleteButton = (Button)findViewById(R.id.btnDelete);
        //Calling my methods
        AddData();
        UpdateData();
        DeleteData();


        //Button to go back to main menu
        Button GoToMainActivity;
        GoToMainActivity = (Button) findViewById(R.id.BackButton);
        GoToMainActivity.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this, Page1.class);
                startActivity(intent);
                finish();
            }
        });//End of intent
    }//end of on create

    //Lets the user know if the data is deleted or not, if the id is not in the list the error message will be displayed
    public void DeleteData(){
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    Integer deletedRows = myDB.deleteData(UpdateText.getText().toString());
                    if(deletedRows > 0)
                        Toast.makeText(AddActivity.this, "Data Deleted",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(AddActivity.this, "Data not Deleted",Toast.LENGTH_LONG).show();
                }
            }
        });//end of on click listener
    }//end of Delete Data method

    //Lets the user know if the data has been updated or not
    public void UpdateData(){
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdated = myDB.updateData(UpdateText.getText().toString(),
                        studyText.getText().toString());
                if(isUpdated)
                    Toast.makeText(AddActivity.this, "Data Updated",Toast.LENGTH_LONG).show();
                    else
                    Toast.makeText(AddActivity.this,"ERROR, Data Not Updated", Toast.LENGTH_LONG).show();
            }
        });//end of onClick listener
    }//end of updateData method

    //Lets the user know if the data has been added or not
    public void AddData(){
        EnterButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                boolean isInserted = myDB.insertData(studyText.getText().toString());

                if (isInserted)
                    Toast.makeText(AddActivity.this, "Added",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(AddActivity.this,"ERROR, not added", Toast.LENGTH_LONG).show();
            }
        });//end of onclick listener
    }//end of add data method
}//end of class
