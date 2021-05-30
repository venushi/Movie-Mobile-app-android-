package com.example.mobilecw02;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class MovieAdpater extends RecyclerView.Adapter<MovieAdpater.ViewHolder> {

    private List<String> movie_names;
    private List<String> movie_id;
    public int[] movie_fav;
    private LayoutInflater mInflater;
    private Context context;
    private ItemClickListener mClickListener;

    //https://www.youtube.com/watch?v=0XUhYLdcG1E

    //https://www.youtube.com/watch?v=_5KjRgxt28s

    //https://developer.android.com/guide/topics/ui/layout/recyclerview

    //https://developer.android.com/reference/android/view/LayoutInflater

    //https://www.youtube.com/watch?v=3l3GxBYKJ30&list=PLdRfLcb1DviyM-TUDiITQwnqJsGTGZRbH&index=25


    ////https://www.youtube.com/watch?v=gKPpePHlpMU&list=PLdRfLcb1DviyM-TUDiITQwnqJsGTGZRbH&index=28



    // data is passed to constructor
    public MovieAdpater(Context context, String[] movie_names,String[] movie_ids,int[] movie_fav) {

        this.context = context;
        this.movie_names = new ArrayList<>(Arrays.asList(movie_names));
        this.movie_id = new ArrayList<>(Arrays.asList(movie_ids));
        this.movie_fav = movie_fav;
        this.mInflater = LayoutInflater.from(context);

    }

    // inflates the xml needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.single_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data
    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.movie_name.setText(movie_names.get(position));

        if(movie_fav[position] == 1){
            holder.checkBox.setChecked(true);
        }

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((CompoundButton) view).isChecked()){
                    System.out.println("Checked");
                    movie_fav[position] = 1;
                } else {
                    movie_fav[position] = 0;
                    System.out.println("Un-Checked");
                }
            }
        });

    }

    public  void onActivityResult(int requestCode, int resultCode) {
        this.notifyDataSetChanged();
        Log.d("myz" , "Changes happen on complete adapter");
    }



    @Override
    public int getItemCount() {
        return movie_names.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView movie_name;
        CheckBox checkBox;

        ViewHolder(View itemView) {
            super(itemView);
            movie_name =  itemView.findViewById(R.id.title);
            checkBox = itemView.findViewById(R.id.CheckBox);
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
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}



