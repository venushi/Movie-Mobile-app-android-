package com.example.mobilecw02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4,ed5,ed6;
    DbHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //https://www.youtube.com/watch?v=3l3GxBYKJ30&list=PLdRfLcb1DviyM-TUDiITQwnqJsGTGZRbH&index=25

        ed1=findViewById(R.id.edtitle); //set ids to edit texts
        ed2=findViewById(R.id.edyear);
        ed3=findViewById(R.id.eddir);
        ed4=findViewById(R.id.edact);
        ed5=findViewById(R.id.edrat);
        ed6=findViewById(R.id.edrev);

        db=new DbHandler(this); //craete a new object from DbHandler class


    }


    public void save(View view) {
//        int ratz=Integer.parseInt(String.valueOf(ed5.getText().toString()));
//        int age=Integer.parseInt(String.valueOf(ed5.getText().toString()));
//
//        if(ratz<10 && age>1895){

        //insert database values

            boolean insert =  db.insert_movies(ed1.getText().toString(),ed2.getText().toString(),ed3.getText().toString(),ed4.getText().toString(),Integer.parseInt(String.valueOf(ed5.getText())),ed6.getText().toString());

            Cursor movies = db.get_movies(); //get values from database
            if(insert){

                Log.d("xyz", String.valueOf(movies.getCount()));
                Log.d("xyz", "Successfully Added the Movie");
                ed1.setText(" ");
                ed2.setText(" ");
                ed3.setText(" ");
                ed4.setText(" ");
                ed5.setText(" ");
                ed5.setText(" ");
                ed6.setText(" ");

                Toast.makeText(getApplicationContext(),"Successfully Added",Toast.LENGTH_SHORT).show();
            }




    }
}