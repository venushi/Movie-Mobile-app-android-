package com.example.mobilecw02;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Search extends AppCompatActivity {

    //https://www.youtube.com/watch?v=QY-O49a_Ags

    //https://www.youtube.com/watch?v=5_jyEGe6ZHo

    //https://www.youtube.com/watch?v=sJ-Z9G0SDhc

    DbHandler db;
    EditText keyText;
    String[] movies_title,movies_id;
    int[] movies_fav;
    TextView no_movies;
    RecyclerView recyclerView;
    MovieSearchAdapter adapter;
    RecyclerView.LayoutManager movielistlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        no_movies = findViewById(R.id.no_movies);
        recyclerView =  findViewById(R.id.result_view);
        db = new DbHandler(this);
        keyText = findViewById(R.id.key);
    }

    public void lookup(View view){
        String key = keyText.getText().toString();

        Cursor res = db.search_movies(key);

        Log.d("myz", "Number of movies  "+res.getCount());
        if(res.getCount() != 0) {
            movies_title = new String[res.getCount()];
            movies_id = new String[res.getCount()];
            movies_fav = new int[res.getCount()];
            int count1 = 0;
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
        adapter = new MovieSearchAdapter(this,movies_title,movies_id);
//        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setVisibility(View.VISIBLE);
        no_movies.setVisibility(View.GONE);
    }
}