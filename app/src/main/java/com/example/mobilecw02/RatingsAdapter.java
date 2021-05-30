package com.example.mobilecw02;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RatingsAdapter extends RecyclerView.Adapter<RatingsAdapter.ViewHolder> {

    //https://www.youtube.com/watch?v=y2xtLqP8dSQ
    //https://www.youtube.com/watch?v=e3MDW87mbR8


    private List<String> movie_names;
    private List<String> movie_id;
    public int[] movie_fav;
    private LayoutInflater mInflater;
    private Rating context;
    private MovieAdpater.ItemClickListener mClickListener;

    // data is passed into the constructor
    public RatingsAdapter(Context context, String[] movie_names, String[] movie_ids) {

        this.context = (Rating)context;
        this.movie_names = new ArrayList<>(Arrays.asList(movie_names));
        this.movie_id = new ArrayList<>(Arrays.asList(movie_ids));
        this.mInflater = LayoutInflater.from(context);

    }

    // inflates the cell layout from xml when needed
    @Override
    public RatingsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.row_edit_movie, parent, false);
        return new RatingsAdapter.ViewHolder(view);
    }

    // binds the data to the textview in each cell
    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    @Override
    public void onBindViewHolder(final RatingsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.movie_name.setText(movie_names.get(position));
        holder.movie_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(context, MovieList.class);
//               intent2.putExtra("title", movie_names.get(position));
                intent2.putExtra("title", movie_names.get(position));

                context.startActivity(intent2);
            }
        });

    }

    public  void onActivityResult(int requestCode, int resultCode) {
        this.notifyDataSetChanged();
        Log.d("myz" , "Changes happen on complete adapter");
    }


    // total number of cells
    @Override
    public int getItemCount() {
        return movie_names.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView movie_name;

        ViewHolder(View itemView) {
            super(itemView);
            movie_name =  itemView.findViewById(R.id.title);

        }

    }

}
