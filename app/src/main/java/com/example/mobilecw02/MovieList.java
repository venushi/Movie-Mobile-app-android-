package com.example.mobilecw02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.DropBoxManager;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MovieList extends AppCompatActivity {
    DbHandler db;
    ArrayList<String> movie_names,movie_ids,movie_urls;
    TextView no_movies;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager imdblistlayout;
    IMDBResultAdapter adapter;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        title = getIntent().getStringExtra("title");
        recyclerView =  findViewById(R.id.result_view);
        subjectload();
    }

    public void subjectload(){

        String movie_url = "https://imdb-api.com/en/API/SearchTitle/k_fxig3jk0/"+title;

        final StringRequest jar = new StringRequest(Request.Method.GET,movie_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("myz" , "Response : "+response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("results");
//                            JSONArray jsonArray = new JSONArray(response);
                            if (jsonArray.length() != 0) {
                                Log.d("myz" , "JSON LENGTH : "+jsonArray.length() );

//                                cat_id[0] = "0";
                                int count = 0;
                                movie_names = new ArrayList<String>();
                                movie_ids = new ArrayList<String>();
                                movie_urls  = new ArrayList<String>();

                                while (count < jsonArray.length()) {
                                    JSONObject jo = jsonArray.getJSONObject(count);
//                                    if("Inception".equals(jo.getString("title"))) {
                                    Log.d("myz" , "Equal");
                                    movie_names.add(jo.getString("title"));
                                    movie_ids.add(jo.getString("id"));
                                    movie_urls.add(jo.getString("image"));
//                                    }
                                    count++;
                                }

                                recycler();

                            } else {

                                Toast.makeText(MovieList.this, "No Results, Go Back and try again", Toast.LENGTH_LONG).show();
                            }
                            Log.d("xyz" , "no error :");
                        } catch (JSONException e) {
                            Toast.makeText(MovieList.this, "Something Wrong, Go Back and try again", Toast.LENGTH_LONG).show();

                            Log.d("xyz" , "json error :" + e.getMessage() );
                        }
                    }

                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
//                Log.d("aa" , "errer "+ error);
                Log.d("myz" , "Error: "+error);

            }
        }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                return params;
            }
        };
        MySingleton.getInstance().addRequest(jar);
        //  paperload = true;
    }

    @SuppressLint("WrongConstant")
    public void recycler() {
        imdblistlayout = new LinearLayoutManager(this, LinearLayout.VERTICAL, false);
        recyclerView.setLayoutManager(imdblistlayout);
        adapter = new IMDBResultAdapter(this,movie_names,movie_ids,movie_urls);
        recyclerView.setAdapter(adapter);
    }
}