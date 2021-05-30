package com.example.mobilecw02;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class EditMovie extends AppCompatActivity {

    //https://www.youtube.com/watch?v=kvPrfp28HZg
    //https://www.youtube.com/watch?v=jNrLy_37eh8
    //https://www.youtube.com/watch?v=9t8VVWebRFM


    DbHandler db;
    String[] movies_title,movies_id;
    int[] movies_fav;
    TextView no_movies;
    RecyclerView recyclerView;
    MovieAdapterEdit adapter;
    RecyclerView.LayoutManager movielistlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);       //set ids
        no_movies = findViewById(R.id.no_movies);
        recyclerView =  findViewById(R.id.recyclr_view);
        db = new DbHandler(this);
        int count1 = 0;               //get count variable to consider about that database is empty or not
        Cursor res = db.get_movies();

        Log.d("myz", "Number of movies  "+res.getCount());
        if(res.getCount() != 0) {     //if database is not empty
            movies_title = new String[res.getCount()];
            movies_id = new String[res.getCount()];  //assaign getcount to movies title,movies id and movies fav
            movies_fav = new int[res.getCount()];

            while (res.moveToNext()) {
                movies_title[count1] = res.getString(res.getColumnIndex("title"));
                movies_id[count1] = res.getString(res.getColumnIndex("movie_id")); //get column index
                movies_fav[count1] = res.getInt(res.getColumnIndex("favourite"));
                count1++;
            }
            recycler();
        }else{
            Log.d("myz", "No Movies");
            recyclerView.setVisibility(View.GONE); //if no any movies text appears
            no_movies.setVisibility(View.VISIBLE);
        }

    }

    @SuppressLint("WrongConstant")
    public void recycler() {
        movielistlayout = new LinearLayoutManager(this, LinearLayout.VERTICAL, false);
        recyclerView.setLayoutManager(movielistlayout);
        adapter = new MovieAdapterEdit(this,movies_title,movies_id);
//        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter); //set adapter to recyclerview
    }

}