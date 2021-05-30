package com.example.mobilecw02;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapterEdit extends RecyclerView.Adapter<MovieAdapterEdit.ViewHolder> {

    private List<String> movie_names;
    private List<String> movie_id;
    public int[] movie_fav;
    private LayoutInflater mInflater;
    private EditMovie context;
    private MovieAdpater.ItemClickListener mClickListener;



    // data is passed into the constructor
    public MovieAdapterEdit(Context context, String[] movie_names,String[] movie_ids) {

        this.context = (EditMovie)context;
        this.movie_names = new ArrayList<>(Arrays.asList(movie_names));
        this.movie_id = new ArrayList<>(Arrays.asList(movie_ids));
        this.mInflater = LayoutInflater.from(context);

    }

    // inflates the cell layout from xml when needed
    @Override
    public MovieAdapterEdit.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.row_edit_movie, parent, false);
        return new MovieAdapterEdit.ViewHolder(view);
    }

    // binds the data to the textview in each cell
    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    @Override
    public void onBindViewHolder(final MovieAdapterEdit.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.movie_name.setText(movie_names.get(position));
        holder.movie_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(context, Edit.class);
                intent2.putExtra("id", movie_id.get(position));
                context.startActivity(intent2);
            }
        });

    }


    // total number of cells
    @Override
    public int getItemCount() {
        return movie_names.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView movie_name;

        ViewHolder(View itemView) {
            super(itemView);
            movie_name =  itemView.findViewById(R.id.title);
        }

    }



}

