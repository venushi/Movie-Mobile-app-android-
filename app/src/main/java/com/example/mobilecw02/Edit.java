package com.example.mobilecw02;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Edit extends AppCompatActivity {

    //https://www.youtube.com/watch?v=kvPrfp28HZg
    //https://www.youtube.com/watch?v=jNrLy_37eh8
    //https://www.youtube.com/watch?v=9t8VVWebRFM

    EditText editText1, editText2, editText3, editText4, editText5, editText6;
    DbHandler db;
    String id;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        db = new DbHandler(this);  //create an object
        id = getIntent().getStringExtra("id");  //getting string and intent to id
        Log.d("myz" , "Edit ID "+id);

        Cursor res = db.get_movie_edit(id);
        Log.d("myz" , "Edit ID "+res.getCount());
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);
        editText5 = findViewById(R.id.editText5);
        editText6 = findViewById(R.id.editText6);  //if database is not null

        if( res != null && res.moveToFirst() ) {
            String movie_title = res.getString(res.getColumnIndex("title"));
            String movie_year = res.getString(res.getColumnIndex("year"));
            String movie_director = res.getString(res.getColumnIndex("director"));
            String movie_actors = res.getString(res.getColumnIndex("actors"));
            String movie_rating = res.getString(res.getColumnIndex("rating"));
            String movie_review = res.getString(res.getColumnIndex("review"));
            Log.d("myz" , "Edit ID "+movie_review);

            editText1.setText(movie_title);
            editText2.setText(movie_year);
            editText3.setText(movie_director);
            editText4.setText(movie_actors);
            editText5.setText(movie_rating);
            editText6.setText(movie_review);

        }



    }

    //edit jk

    public void edit(View view){
        boolean insert =  db.edit_movie(id,editText1.getText().toString(),editText2.getText().toString(),editText3.getText().toString(),editText4.getText().toString(),Integer.parseInt(String.valueOf(editText5.getText())),editText6.getText().toString());
        Cursor movies = db.get_movies();  //get movies to db

        Toast.makeText(getApplicationContext(),"Successfully Updated",Toast.LENGTH_SHORT).show();

    }
}