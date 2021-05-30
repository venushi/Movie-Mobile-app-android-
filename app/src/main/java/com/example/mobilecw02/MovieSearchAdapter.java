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

public class MovieSearchAdapter extends RecyclerView.Adapter<MovieSearchAdapter.ViewHolder> {

    private List<String> movie_names;
    private List<String> movie_id;
    public int[] movie_fav;
    private LayoutInflater mInflater;
    private Search context;
    private MovieAdpater.ItemClickListener mClickListener;



    // data is passed into the constructor
    public MovieSearchAdapter(Context context, String[] movie_names, String[] movie_ids) {

        this.context = (Search)context;
        this.movie_names = new ArrayList<>(Arrays.asList(movie_names));
        this.movie_id = new ArrayList<>(Arrays.asList(movie_ids));
        this.mInflater = LayoutInflater.from(context);

    }

    // inflates the cell layout from xml when needed
    @Override
    public MovieSearchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.row_edit_movie, parent, false);
        return new MovieSearchAdapter.ViewHolder(view);
    }

    // binds the data to the textview in each cell
    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    @Override
    public void onBindViewHolder(final MovieSearchAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.movie_name.setText(movie_names.get(position));

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
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView movie_name;

        ViewHolder(View itemView) {
            super(itemView);
            movie_name =  itemView.findViewById(R.id.title);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {


            if (mClickListener != null) {

                mClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }
    // convenience method for getting data at click position
    public String getItem(int id) {
        return movie_id.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(MovieAdpater.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
