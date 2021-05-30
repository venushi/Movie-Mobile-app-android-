package com.example.mobilecw02;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "movies_db";

    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

//    https://www.youtube.com/watch?v=76tm4INTJZo&list=PLdRfLcb1DviyM-TUDiITQwnqJsGTGZRbH&index=24

    //https://www.youtube.com/watch?v=dYt763QgaTg

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table if not exists movies" + " (movie_id INTEGER PRIMARY KEY AUTOINCREMENT,title text, year text,director text,actors text,rating INTEGER,review text,favourite int default 0)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS movies");
        onCreate(db);
    }

    //www.youtube.com/watch?v=mEuGvreqFZM&list=PLdRfLcb1DviyM-TUDiITQwnqJsGTGZRbH&index=26


    public boolean insert_movies(String title, String year, String director, String actors, int rating, String review) {

        SQLiteDatabase db = this.getWritableDatabase();

        //   db.insert("paper_complete",null,contentValues);
        String insert_movie = "INSERT INTO movies (title, year, director,actors,rating,review) VALUES (\"" + title + "\",\"" + year + "\",\"" + director + "\",\"" + actors + "\",\"" + rating + "\",\"" + review + "\")";
        db.execSQL(insert_movie);
        return true;
    }

    public boolean edit_movie(String id, String title, String year, String director, String actors, int rating, String review) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE movies SET title = \""+title+"\",  year = \""+year+"\",  director = \""+director+"\",  actors = \""+actors+"\",  rating = \""+rating+"\",  review = \""+review+"\" where movie_id = " + Integer.parseInt(id));
        //   db.insert("paper_complete",null,contentValues);
//        String insert_movie = "INSERT INTO movies (title, year, director,actors,rating,review) VALUES (\"" + title + "\",\"" + year + "\",\"" + director + "\",\"" + actors + "\",\"" + rating + "\",\"" + review + "\")";

        return true;
    }

    //https://www.youtube.com/watch?v=A8p0E_yRSNI&list=PLdRfLcb1DviyM-TUDiITQwnqJsGTGZRbH&index=27

    public Cursor get_movies(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select *" +
                " from movies ORDER BY title ASC",null);
        return res;
    }

    public Cursor get_fav_movies(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select *" +
                " from movies where favourite = "+ 1,null);
        return res;
    }

    public Cursor get_movie_edit(String id){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select *" +
                " from movies where movie_id = "+ Integer.parseInt(id),null);
        return res;
    }

    public void set_fav(int[] movie_fav,String[] movie_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("xyz", "Handle Error 1");

        for(int a=0;a<movie_fav.length;a++) {
            try {
                if(movie_fav[a] == 0) {
                    Log.d("xyz", "Favourite");

                    db.execSQL("UPDATE movies SET favourite = 0 where movie_id = " + Integer.parseInt(movie_id[a]));
                }else{
                    Log.d("xyz", "Not Favourite");

                    db.execSQL("UPDATE movies SET favourite = 1 where movie_id = " + Integer.parseInt(movie_id[a]));
                }
            } catch (SQLException e) {
                Log.d("xyz", "Handle Error");
            }
        }
    }


    public Cursor search_movies(String key) {

        //https://www.youtube.com/watch?v=gKPpePHlpMU&list=PLdRfLcb1DviyM-TUDiITQwnqJsGTGZRbH&index=28

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select *" +
                " from movies where title like '%"+key+"%' OR title like '"+key+"%' OR title like '%"+key+"' OR title like '"+key+"'" +
                " OR director like '%"+key+"%' OR director like '"+key+"%' OR director like '%"+key+"' OR director like '"+key+"'",null);

        return res;
    }
}
