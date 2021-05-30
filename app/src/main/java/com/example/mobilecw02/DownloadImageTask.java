package com.example.mobilecw02;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadImageTask extends AsyncTask<String,Void, Bitmap> {

    //IIT Tutorial7

    @Override
    protected Bitmap doInBackground(String... strings){

        String s1=strings[0];
        InputStream in;

        try {
            URL myUrl=new URL(s1);
            HttpURLConnection myConn = (HttpURLConnection) myUrl.openConnection();
            myConn.setReadTimeout(10000);
            myConn.setConnectTimeout(20000);
            myConn.setRequestMethod("GET");
            myConn.connect();

            in=myConn.getInputStream();

            Bitmap myMap = BitmapFactory.decodeStream(in);

            return myMap;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap){
        TotalRating.image.setImageBitmap(bitmap);
    }



}
