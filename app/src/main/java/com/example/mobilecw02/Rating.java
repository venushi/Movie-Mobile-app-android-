package com.example.mobilecw02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.os.DropBoxManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Rating extends AppCompatActivity {

    //https://developer.android.com/training/volley/simple

    DbHandler db;
    String[] movies_title,movies_id;
    int[] movies_fav;
    TextView no_movies;
    RecyclerView recyclerView;
    RatingsAdapter adapter;
    RecyclerView.LayoutManager movielistlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        no_movies = findViewById(R.id.no_movies);
        recyclerView =  findViewById(R.id.recyclr_view);
        db = new DbHandler(this);
        int count1 = 0;
        Cursor res = db.get_movies();

        Log.d("myz", "Number of movies  "+res.getCount());
        if(res.getCount() != 0) {
            movies_title = new String[res.getCount()];
            movies_id = new String[res.getCount()];
            movies_fav = new int[res.getCount()];

            while (res.moveToNext()) {
                movies_title[count1] = res.getString(res.getColumnIndex("title"));
                movies_id[count1] = res.getString(res.getColumnIndex("movie_id"));
                movies_fav[count1] = res.getInt(res.getColumnIndex("favourite"));
                count1++;
            }
            recycler();
        }else{
            Log.d("myz", "No Movies");
            recyclerView.setVisibility(View.GONE);
            no_movies.setVisibility(View.VISIBLE);
        }

    }

    @SuppressLint("WrongConstant")
    public void recycler() {
        movielistlayout = new LinearLayoutManager(this, LinearLayout.VERTICAL, false);
        recyclerView.setLayoutManager(movielistlayout);
        adapter = new RatingsAdapter(this,movies_title,movies_id);
//        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }
}