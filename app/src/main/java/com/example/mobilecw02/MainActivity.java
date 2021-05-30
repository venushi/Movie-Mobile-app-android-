package com.example.mobilecw02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void launch1(View view) {
        Intent intent2 = new Intent(this, Register.class);
        startActivity(intent2);
    }

    public void launch2(View view) {
        Intent intent2 = new Intent(this, DisplayMovie.class);
        startActivity(intent2);
    }

    public void launch3(View view) {
        Intent intent2 = new Intent(this, Favourites.class);
        startActivity(intent2);
    }

    public void launch4(View view) {
        Intent intent2 = new Intent(this, EditMovie.class);
        startActivity(intent2);
    }

    public void launch5(View view) {
        Intent intent2 = new Intent(this, Search.class);
        startActivity(intent2);
    }

    public void launch6(View view) {
        Intent intent2 = new Intent(this, Rating.class);
        startActivity(intent2);
    }
}
