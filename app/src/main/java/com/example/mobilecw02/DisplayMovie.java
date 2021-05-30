package com.example.mobilecw02;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//https://www.youtube.com/watch?v=kvPrfp28HZg

//https://www.youtube.com/watch?v=1vN_wuAahqA

//https://www.youtube.com/watch?v=2ABHmrjvFmY

//https://www.youtube.com/watch?v=VQKq9RHMS_0

//https://www.youtube.com/watch?v=APZlsLTcX5g

public class DisplayMovie extends AppCompatActivity {

    DbHandler db;
    String[] movies_title,movies_id;
    int[] movies_fav;
    TextView no_movies;
    RecyclerView recyclerView;
    MovieAdpater adapter;
    RecyclerView.LayoutManager movielistlayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_movie);

       no_movies = findViewById(R.id.no_movies);
        recyclerView =  findViewById(R.id.recyclr_view);
        db = new DbHandler(this);
        int count1 = 0;  //get count variable to consider about database is empty
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
        adapter = new MovieAdpater(this,movies_title,movies_id,movies_fav);
//        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        Log.d("xyz", "Handle Recycle View");

    }

    public void add_to_fav(View view){

        db.set_fav(adapter.movie_fav, movies_id);

        Toast.makeText(getApplicationContext(),"Added to Favourite List",Toast.LENGTH_SHORT).show();
    }


}