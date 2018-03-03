package com.ratingrocker.ratingrockerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.util.Log;

import com.ratingrocker.ratingrockerapp.DataObjects.FullSongdata;
import com.ratingrocker.ratingrockerapp.DataObjects.SCsongdata;
import com.ratingrocker.ratingrockerapp.DataObjects.spotifysong;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


//import android.util.Log;

public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "songdata3.db";
    // Table Names
    //public static final String TABLE_MAIN = "main";
    //public static final String TABLE_GENRES = "genres";

    private static final String TABLE_PLAYLIST_INFO = "playlistinfo";
    private static final String TABLE_MAIN_SONG_INFO = "MainSongInfo";
    private static final String TABLE_GENERAL_INFO = "GeneralInfo";

    private static final String COLUMN_USER_NAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_USER_GENRES = "user_genres";
    private static final String COLUMN_RATING_CATEGORIES = "rating_categories";
    private static final String COLUMN_SC_LIST_DATA = "sc_list_data";
    private static final String COLUMN_FOLDERS = " user_folders";


    // Playlist info
    private static final String COLUMN_PLAYLIST_NAME = "playlistname";
    private static final String COLUMN_PLAYLIST_ID = "playlist_id";
    private static final String COLUMN_PLAYLIST_DESCRIPTON = "playlist_description";
    private static final String COLUMN_PLAYLIST_CREATED_DATE = "playlist_created_date";
    private static final String COLUMN_PLAYLIST_DELETE_DATE = "playlist_delete_date";
    private static final String COLUMN_PLAYLIST_FEATS = "playlist_feats";
    private static final String COLUMN_PLAYLIST_LOADED = "playlist_loaded";
    private static final String COLUMN_PLAYLIST_FOLDER = "playlist_folder";

    // SONG Data Table MAIN - column names

    // SONG Extra at hand vals
    // Song Column Names
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_SONGID = "songid";
    private static final String COLUMN_SONGNAME = "songname";
    private static final String COLUMN_ARTISTS = "artist";
    private static final String COLUMN_RATECOUNT = "ratecount";
    private static final String COLUMN_RATINGVAL1 = "ratingval1";
    private static final String COLUMN_RATINGVAL2 = "ratingval2";
    private static final String COLUMN_RATINGVAL3 = "ratingval3";
    private static final String COLUMN_FRESHVAL = "freshval";
    private static final String COLUMN_SCORE = "upcount";
    private static final String COLUMN_MYGENRE = "genre";
    private static final String COLUMN_EXRATINGVAL = "exratingval";//Includes Fav and Second look
    private static final String COLUMN_POPULARITY = "popularity";
    private static final String COLUMN_SONG_CONNECT = "songconnect";
    private static final String COLUMN_FAVSEC = "favsec";
    private static final String COLUMN_SPOTIFY_GENRES = "spotifygenre";
    private static final String COLUMN_SPOTIFY_DATA = "audiofeat";


    private String[] SCListData;
    private List<SCsongdata> SClist;
    private List<Float> AvgStddif;
    private List<String> usergenrelist;
    private static final String TAG = "MYDBHANDLER";

    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query2 = "CREATE TABLE " +
                TABLE_GENERAL_INFO + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_USER_NAME + " TEXT,"
                + COLUMN_PASSWORD + " TEXT,"
                + COLUMN_USER_GENRES + " TEXT,"
                + COLUMN_RATING_CATEGORIES + " TEXT,"
                + COLUMN_FOLDERS + " TEXT,"
                + COLUMN_SC_LIST_DATA + " TEXT" + ");";

        String query1 = "CREATE TABLE " +
                TABLE_PLAYLIST_INFO + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_PLAYLIST_NAME + " TEXT,"
                + COLUMN_PLAYLIST_ID + " TEXT,"
                + COLUMN_PLAYLIST_DESCRIPTON + " TEXT,"
                + COLUMN_PLAYLIST_CREATED_DATE + " TEXT,"
                + COLUMN_PLAYLIST_DELETE_DATE + " TEXT,"
                + COLUMN_PLAYLIST_FEATS + " TEXT,"
                + COLUMN_PLAYLIST_LOADED + " INTEGER,"
                + COLUMN_PLAYLIST_FOLDER + " TEXT"
                + ");";


        String query3 = "CREATE TABLE " +
                TABLE_MAIN_SONG_INFO + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_SONGID + " TEXT," // or url depending on location
                + COLUMN_SONGNAME + " TEXT,"
                + COLUMN_ARTISTS + " TEXT,"
                + COLUMN_RATINGVAL1 + " INTEGER,"
                + COLUMN_RATINGVAL2 + " INTEGER,"
                + COLUMN_RATINGVAL3 + " INTEGER,"
                + COLUMN_RATECOUNT + " INTEGER,"
                + COLUMN_FRESHVAL + " INTEGER,"
                + COLUMN_EXRATINGVAL + " INTEGER,"
                + COLUMN_MYGENRE + " TEXT,"
                + COLUMN_SCORE + " INTEGER,"
                + COLUMN_SONG_CONNECT + " TEXT,"
                + COLUMN_FAVSEC + " INTEGER,"
                + COLUMN_SPOTIFY_GENRES + " TEXT,"
                + COLUMN_POPULARITY + " INTEGER,"
                + COLUMN_SPOTIFY_DATA + " TEXT"
                +  ");";


        db.execSQL(query1);
        db.execSQL(query2);

        db.execSQL(query3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query1 = "CREATE TABLE " +
                TABLE_PLAYLIST_INFO + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_PLAYLIST_NAME + " TEXT,"
                + COLUMN_PLAYLIST_ID + " TEXT,"
                + COLUMN_PLAYLIST_DESCRIPTON + " TEXT,"
                + COLUMN_PLAYLIST_CREATED_DATE + " TEXT,"
                + COLUMN_PLAYLIST_DELETE_DATE + " TEXT,"
                + COLUMN_PLAYLIST_FEATS + " TEXT,"
                + COLUMN_PLAYLIST_LOADED + " INTEGER,"
                + COLUMN_PLAYLIST_FOLDER + " TEXT"
                + ");";


        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYLIST_INFO);

        db.execSQL(query1);
    }


//Todo make list actually ordered
// for now just going to add songs to bottom so i can test sooner


public void updateglist(){
    //ADD GENRES
}



    public void deletetable() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_MAIN_SONG_INFO);
    }





    /////////////////////////////////////////////////////
       //\\      ||  \\   ||  \\
      //  \\     ||   ||  ||   ||
     //||||\\    ||   ||  ||   ||
    //      \\   ||  //   ||  //
    /////////////////////////////////////////////////////



    ///TODO Make null case and better loop
    public void addspotifysongdata(spotifysong song) {
        SQLiteDatabase db = this.getWritableDatabase();

        String selectquery = "Select * from " + TABLE_MAIN_SONG_INFO + " WHERE " + COLUMN_SONGID + "=\"" + song.getSong_id() + "\";";
        Cursor c = db.rawQuery(selectquery, null);

        if (c.moveToFirst()) {
            ContentValues values = new ContentValues();
            String newmyval = spotgenretomygenre2(song.getSpotify_genre());
            values.put(COLUMN_MYGENRE, newmyval);
            db.update(TABLE_MAIN_SONG_INFO, values, COLUMN_ID + " = ?",
                    new String[]{String.valueOf(c.getInt(c.getPosition()))});


        } else {
            ContentValues values = new ContentValues();
            String newmyval = spotgenretomygenre2(song.getSpotify_genre());
            // values.put(COLUMN_MYGVAL, newmyval);
            // Log.e("Spot G new" , song.get_spotifygenres());
            values.put(COLUMN_SONGID, song.getSong_id());
            values.put(COLUMN_SONGNAME, song.getSong_name());
            values.put(COLUMN_ARTISTS, song.getArtist());
            values.put(COLUMN_MYGENRE, newmyval);
            //values.put(COLUMN_ADDED_DATE, song.get_added_date());
            values.put(COLUMN_SPOTIFY_GENRES, String.valueOf(song.getSpotify_genre()));
            values.put(COLUMN_POPULARITY, song.getPopularity());
           // values.put(COLUMN_RELEASEDATE, String.valueOf(song.get_releasedate()));
            values.put(COLUMN_SPOTIFY_DATA, song.getSpotify_data());
            db.insert(TABLE_MAIN_SONG_INFO, null, values);
        }
        c.close();
        db.close();
    }
    public void addnewrating(RatingData song) {
        SQLiteDatabase db = getWritableDatabase();

        String selectquery = "Select * from " + TABLE_MAIN_SONG_INFO + " WHERE " + COLUMN_SONGID + "=\"" + song.get_songid() + "\";";
        Cursor c = db.rawQuery(selectquery, null);
        int p = 0;
        if (c.moveToFirst()) {
            p = 1;
            int ratingcount = c.getInt(c.getColumnIndex(COLUMN_RATECOUNT));
            do {
                ContentValues values = new ContentValues();
                List<Integer> rvals = Arrays.asList(song.get_ratingval1(), song.get_ratingval1(), song.get_ratingval1());
                List<Integer> oldrvals = Arrays.asList(c.getInt(c.getColumnIndex(COLUMN_RATINGVAL1)), c.getInt(c.getColumnIndex(COLUMN_RATINGVAL2)), c.getInt(c.getColumnIndex(COLUMN_RATINGVAL3)));
                for (int k = 0; k < rvals.size(); k++) {
                    if (rvals.get(k) > 60) {
                        if (oldrvals.get(k) != 0) {
                            int newval1 = (rvals.get(k) + oldrvals.get(k) * ratingcount) / (ratingcount + 1);
                            values.put(COLUMN_RATINGVAL1, newval1);
                        } else {
                            values.put(COLUMN_RATINGVAL1, rvals.get(k));
                        }
                    }
                    ratingcount = ratingcount + 1;
                    values.put(COLUMN_RATECOUNT, ratingcount);
                    values.put(COLUMN_FRESHVAL, song.get_freshvalue());
                    values.put(COLUMN_MYGENRE, song.get_mygval());
                    db.update(TABLE_MAIN_SONG_INFO, values, COLUMN_ID + " = ?",
                            new String[]{String.valueOf(c.getInt(c.getPosition()))});


                }
            } while (c.moveToNext());
        }
            c.close();
            db.close();

    }
    public void addMISCdata(String id, int score, int favsec,String SongConnectlist) {
        SQLiteDatabase db = getWritableDatabase();
        String selectquery = "Select * from " + TABLE_MAIN_SONG_INFO + " WHERE " + COLUMN_ID + "=\"" + id + "\";";
        Cursor c = db.rawQuery(selectquery, null);

        if (c.moveToFirst()) {
            do {
                ContentValues values = new ContentValues();
                values.put(COLUMN_SCORE, score);
                values.put(COLUMN_FAVSEC, favsec);
                values.put(COLUMN_SONG_CONNECT, SongConnectlist);
                db.update(TABLE_MAIN_SONG_INFO, values, COLUMN_ID + " = ?",
                        new String[]{String.valueOf(c.getInt(c.getPosition()))});
                c.close();
                db.close();
            } while (c.moveToNext());
        }
    }
   /* public void addSongConnectdata(Integer id, String SongConnectlist) {
        SQLiteDatabase db = getWritableDatabase();

        String selectquery = "Select * from " + TABLE_MAIN_SONG_INFO + " WHERE " + COLUMN_ID + "=\"" + id + "\";";
        Cursor c = db.rawQuery(selectquery, null);
        if (c.moveToFirst()) {
            do {
                ContentValues values = new ContentValues();
                values.put(COLUMN_SONG_CONNECT, SongConnectlist);
                db.update(TABLE_MAIN_SONG_INFO, values, COLUMN_ID + " = ?",
                        new String[]{String.valueOf(c.getInt(c.getPosition()))});
                c.close();
                db.close();
            } while (c.moveToNext());
        }
    }*/


////////////////////////////////////////////////////////////////////////////
/* |||||||  ||        ||||||||  //|||||     ||||||||||
   ||   ||  ||           ||     ||              ||
   |||||||  ||           ||     \\|||\\         ||
   ||       ||           ||           ||        ||
   ||       ||||||||  ||||||||  |||||//         ||
///////////////////////////////////////////////////////////////////////////*/

    public void addnewUserPlaylist(String playlistid, String playlistname) {
        SQLiteDatabase db = getWritableDatabase();
       // String selectquery = "Select * from " + TABLE_PLAYLIST_INFO;
       // Cursor c = db.rawQuery(selectquery, null);
        String selectquery = "Select * from " + TABLE_PLAYLIST_INFO + " WHERE " + COLUMN_PLAYLIST_ID + "=\"" + playlistid + "\";";
        Cursor c = db.rawQuery(selectquery, null);
        int u = 0;
        if (c.moveToFirst()) {
            u = 1;
        }
        else{
            ContentValues values = new ContentValues();
            Date currentdate = Calendar.getInstance().getTime();
            values.put(COLUMN_PLAYLIST_ID, playlistid);
            values.put(COLUMN_PLAYLIST_NAME, playlistname);
            values.put(COLUMN_PLAYLIST_CREATED_DATE, currentdate.toString());
            values.put(COLUMN_PLAYLIST_LOADED, 1);
            db.insert(TABLE_PLAYLIST_INFO, null, values);

            //Log.e("playlist", String.valueOf(values));
            //printPlaylists();

        }
        c.close();
        db.close();
    }

    public void addUserPlaylist(String playlistid, String playlistname) {
        SQLiteDatabase db = getWritableDatabase();
        String selectquery = "Select * from " + TABLE_PLAYLIST_INFO + " WHERE " + COLUMN_PLAYLIST_ID + "=\"" + playlistid + "\";";
        Cursor c = db.rawQuery(selectquery, null);
        int u = 0;
        if (c.moveToFirst()) {
            u = 1;
        }
       else{
            ContentValues values = new ContentValues();
            Date currentdate = Calendar.getInstance().getTime();
            values.put(COLUMN_PLAYLIST_ID, playlistid);
            values.put(COLUMN_PLAYLIST_NAME, playlistname);
            values.put(COLUMN_PLAYLIST_CREATED_DATE, currentdate.toString());
            values.put(COLUMN_PLAYLIST_LOADED, 1);
            db.insert(TABLE_PLAYLIST_INFO, null, values);

            //Log.e("playlist", String.valueOf(values));
            //printPlaylists();
        }
        c.close();
        db.close();


    }


    public void addRRPlaylist(Vibeplaylist vibeplaylist) {
        SQLiteDatabase db = getWritableDatabase();
        //String selectquery = "Select * from " + TABLE_PLAYLIST_INFO ;
        //Cursor c = db.rawQuery(selectquery, null);
        String selectquery = "Select * from " + TABLE_PLAYLIST_INFO + " WHERE " + COLUMN_PLAYLIST_NAME + "=\"" + vibeplaylist.get_playlistname() + "\";";
        Cursor c = db.rawQuery(selectquery, null);
        int u = 0;
        if (c.moveToFirst()) {
            u = 1;
            db.execSQL("DELETE FROM " + TABLE_PLAYLIST_INFO + " WHERE " + COLUMN_PLAYLIST_NAME + "=\"" + vibeplaylist.get_playlistname() + "\";");
        }
        while (c.moveToNext()) ;
        //if (u != 1) {

        //TODO check for repeats
        Date currentdate = Calendar.getInstance().getTime();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PLAYLIST_NAME, vibeplaylist.get_playlistname());
        values.put(COLUMN_PLAYLIST_DESCRIPTON, vibeplaylist.get_playlistinfo());
        values.put(COLUMN_PLAYLIST_FEATS, vibeplaylist.get_playlist_mygval());
        values.put(COLUMN_PLAYLIST_ID, vibeplaylist.get_ratingmin());
        values.put(COLUMN_PLAYLIST_CREATED_DATE, currentdate.toString());
       // values.put(COLUMN_PLAYLIST_DELETE_DATE, vibeplaylist.get_freshmin());
        values.put(COLUMN_PLAYLIST_FEATS, vibeplaylist.get_exratingsmin());
        db.insert(TABLE_PLAYLIST_INFO, null, values);

        //printPlaylists()
        //}while (c.moveToNext()) ;

        //}
        c.close();
        db.close();

    }
    public List<List<String>> getListVibePlaylists() {
        List<String> vibeplaylists = new ArrayList<String>();
        List<String> playlistids = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PLAYLIST_INFO;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                if (!vibeplaylists.contains(cursor.getString(cursor.getColumnIndex(COLUMN_PLAYLIST_NAME)))){
                vibeplaylists.add(cursor.getString(cursor.getColumnIndex(COLUMN_PLAYLIST_NAME)));
                playlistids.add(cursor.getString(cursor.getColumnIndex(COLUMN_PLAYLIST_ID)));
                }
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return Arrays.asList(vibeplaylists, playlistids);
    }
   /* public List<Vibeplaylist> getallRPlaylist() {
        List<Vibeplaylist> playlists = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_PLAYLIST_INFO;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Vibeplaylist playlist = new Vibeplaylist();
                //playlist.set_id(c.getInt(c.getColumnIndex(COLUMN_ID)));
                playlist.set_vibenum(c.getInt(c.getColumnIndex(COLUMN_VIBENUM)));
                playlist.set_playlistname(c.getString(c.getColumnIndex(COLUMN_PLAYLIST_NAME)));
                playlist.set_playlistinfo(c.getString(c.getColumnIndex(COLUMN_PLAYLIST_INFO)));
                playlist.set_playlist_mygval(c.getString(c.getColumnIndex(COLUMN_MYGVAL)));
                playlist.set_ratingmin(c.getInt(c.getColumnIndex(COLUMN_PLAYLIST_RMIN)));
                playlist.set_ratingmax(c.getInt(c.getColumnIndex(COLUMN_PLAYLIST_RMAX)));
                playlist.set_freshmin(c.getInt(c.getColumnIndex(COLUMN_PLAYLIST_FMIN)));
                playlist.set_freshmax(c.getInt(c.getColumnIndex(COLUMN_PLAYLIST_FMAX)));
                playlist.set_exratingsmin(c.getInt(c.getColumnIndex(COLUMN_PLAYLIST_ERMIN)));

                playlists.add(playlist);
            } while (c.moveToNext());
        }
        c.close();

        db.close();
        return playlists;
    }*/
   public String getPlaylistid(String playlistname) {
       List<String> playlists = new ArrayList<String>();

       // Select All Query
       String selectQuery = "SELECT  * FROM " + TABLE_PLAYLIST_INFO + " WHERE " + COLUMN_PLAYLIST_NAME + "=\"" + playlistname + "\";";;

       SQLiteDatabase db = this.getReadableDatabase();
       Cursor cursor = db.rawQuery(selectQuery, null);
       String playlistid = "spotify:user:1221015148:playlist:60lC1LEUBLDKjzocrzmPRF";
       // looping through all rows and adding to list
       if (cursor.moveToFirst()) {
           do {
               playlistid = cursor.getString(1);

           } while (cursor.moveToNext());
       }

       // closing connection
       cursor.close();
       db.close();

       // returning lables
       return playlistid;
   }


    public void deletevibeplaylist(String playlistname){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PLAYLIST_INFO + " WHERE " + COLUMN_PLAYLIST_NAME + "=\"" + playlistname + "\";");
        db.close();
    }
    public void deleteuserplaylist(String playlistname){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PLAYLIST_INFO + " WHERE " + COLUMN_PLAYLIST_NAME + "=\"" + playlistname + "\";");
        db.close();
    }


    // c.close();



    ////////////////////////////////////////////////////////////////////////////
/* ||||||| ||||||||  ||    |||||||| |||||||  ||\\\
   ||         ||     ||       ||    ||       ||  ||
   |||||||    ||     ||       ||    ||||||   ||///
   ||         ||     ||       ||    ||       || \\
   ||      ||||||||  |||||||  ||    |||||||  ||  \\
///////////////////////////////////////////////////////////////////////////*/


public List<Basicsong> getfilterlist(List<List<String>> filtgenres, List<List<Integer>>maininputs, List<List<Integer>>hitlist) {
    SQLiteDatabase db = getReadableDatabase();
    // main values --- popularity (min max), rating(min max), fresh(min max)
    List<String> sortingcolumns = Arrays.asList("COLUMN_RATINGVAL1", "COLUMN_RATINGVAL2", "COLUMN_RATINGVAL3", "COLUMN_FRESHVAL", "popularity");
    //This would check to see if any user rating reqs
    List<Basicsong> songs = new ArrayList<>();
    Cursor c;
    Log.e(TAG, "in");
   if (hitlist.get(1).size() > 1) {
       try {
           c = db.query(TABLE_MAIN_SONG_INFO, null, null,
                   null, null, null, sortingcolumns.get(hitlist.get(1).get(1) / 2) + " DESC", null);
       }catch (Exception e){
           String selectquery = "Select * from " + TABLE_MAIN_SONG_INFO;
           c = db.rawQuery(selectquery, null);}
    } else if (hitlist.get(0).contains(0)) { //Check Popularity
        if (100 < (maininputs.get(0).get(0) + maininputs.get(0).get(1))) {
            c = db.query(TABLE_MAIN_SONG_INFO, null, null,
                    null, null, null, sortingcolumns.get(4) + " DESC", null);
        } else {
            c = db.query(TABLE_MAIN_SONG_INFO, null, null, null, null, null, sortingcolumns.get(4) + " ASC", null);
        }
    } else {
        String selectquery = "Select * from " + TABLE_MAIN_SONG_INFO;
        c = db.rawQuery(selectquery, null);
    }
    try {
        while (c.moveToNext()) {

           // Log.e(TAG, c.getString(c.getColumnIndex(COLUMN_SONGNAME)));
            boolean alive = true;
            String[] songSpotifyData = c.getString(c.getColumnIndex(COLUMN_SPOTIFY_DATA)).split("\\.");
          //  Log.e(TAG, c.getString(c.getColumnIndex(COLUMN_SPOTIFY_DATA)));
            for (int p = 0; p < hitlist.size(); p++) {
                if (hitlist.get(p).size() > 1) {
                    for (int g = 1; g < hitlist.get(p).size(); g++) {
                        if (p==0){ //Added Month
                            if (hitlist.get(p).get(g)==2) {
                                String[] date = songSpotifyData[0].split("-");
                               // Log.e(TAG, date[1]);
                                if (maininputs.get(p).get(hitlist.get(p).get(g)) > Integer.parseInt(date[1])
                                        || maininputs.get(p).get(hitlist.get(p).get(g) + 1) < Integer.parseInt(date[1])) {
                                    g = hitlist.get(p).size();
                                    //p = hitlist.size();
                                    alive = false;
                                }
                            }else if (hitlist.get(p).get(g)==4){ //release year
                                String[] date = songSpotifyData[1].split("-");
                              //  Log.e(TAG, date[0]);
                                if (maininputs.get(p).get(hitlist.get(p).get(g)) > Integer.parseInt(date[0])
                                        || maininputs.get(p).get(hitlist.get(p).get(g) + 1) < Integer.parseInt(date[0])) {
                                    g = hitlist.get(p).size();
                                    //p = hitlist.size();
                                    alive = false;}
                            }else {
                                Log.e("Popularity", String.valueOf(c.getInt(c.getColumnIndex(COLUMN_POPULARITY))));
                                if (maininputs.get(p).get(hitlist.get(p).get(g)) > c.getInt(c.getColumnIndex(COLUMN_POPULARITY))
                                        || maininputs.get(p).get(hitlist.get(p).get(g) + 1) < c.getInt(c.getColumnIndex(COLUMN_POPULARITY))) {
                                    g = hitlist.get(p).size();
                                    //p = hitlist.size();
                                    alive = false;
                                }
                            }
                        }
                        else if (p == 1) { //User Ratings
                            try{
                            if (maininputs.get(p).get(hitlist.get(p).get(g)) > c.getInt(c.getColumnIndex(sortingcolumns.get(hitlist.get(p).get(g) / 2)))
                                    || maininputs.get(p).get(hitlist.get(p).get(g)+1) < c.getInt(c.getColumnIndex(sortingcolumns.get(hitlist.get(p).get(g) / 2)))) {
                                g = hitlist.get(p).size();
                               // p = hitlist.size();
                                alive = false;
                            } }catch (Exception e){}
                        }
                        else { // Spotify Ratings
                            try{
                                Log.e(c.getString(c.getColumnIndex(COLUMN_SONGNAME)), songSpotifyData[(hitlist.get(p).get(g) / 2) + 2]);
                            if (maininputs.get(p).get(hitlist.get(p).get(g)) > Integer.parseInt(songSpotifyData[(hitlist.get(p).get(g) / 2) + 2])
                                    || maininputs.get(p).get(hitlist.get(p).get(g)+1) < Integer.parseInt(songSpotifyData[(hitlist.get(p).get(g) / 2) + 2])) {
                                g = hitlist.get(p).size();

                                alive = false;
                            }}catch (Exception e){}
                        }
                    } if (!alive){ p = hitlist.size();}
                }
            }
            if (alive) {
                Log.e(TAG, "Still Alive");
                try {
                    //TODO MAKE sure empty list works here
                    for (int w = 0; w < filtgenres.size(); w++) {
                        Log.e("In g filter", c.getString(c.getColumnIndex(COLUMN_MYGENRE)));
                        if (c.getString(c.getColumnIndex(COLUMN_MYGENRE)).contains(filtgenres.get(w).get(0))) {
                            Log.e("contains g", c.getString(c.getColumnIndex(COLUMN_SONGNAME)));
                            try {
                                if (filtgenres.get(w).get(1) != "NA" || filtgenres.get(w).get(1) != " ") {
                                    String[] subs = filtgenres.get(w).get(1).split("\\s*,\\s*");
                                    for (int b = 1; b < subs.length; b++) {
                                        if (c.getString(c.getColumnIndex(COLUMN_MYGENRE)).contains(subs[b])) {
                                            b = subs.length + 1;
                                            w = filtgenres.size() + 1;
                                        }
                                    }
                                } else {
                                    w = filtgenres.size() + 1;
                                }
                            }catch (Exception e){

                            }
                        } else if (w == filtgenres.size()-1) {
                            alive = false;}
                    }
                } catch (Exception e) {
                    Log.e("mydb", "uh oh");
                }
            }
            if (alive) {
                Basicsong song = new Basicsong();
                song.set_songname(c.getString(c.getColumnIndex(COLUMN_SONGNAME)));
                song.set_song_id(c.getString(c.getColumnIndex(COLUMN_SONGID)));
                song.set_id(c.getInt(0));
               Log.e("mydb", song.get_songname());
                songs.add(song);
            }

        }
    } finally {
        c.close();
        db.close();
    }
    return songs;
}

/*

       //TODO change formating before saving in filter frag
        String monthaddedstr;
        if (monthadded< 10 ) {
            monthaddedstr = "-0" + String.valueOf(monthadded) + "-";
        }else{
            monthaddedstr = "-" + String.valueOf(monthadded) + "-";
        }
        List<Basicsong> songs = new ArrayList<>();

    }*/








    //TODO this 'while' seems off







////////////////////////////////////////////////////////////////////////////
/* //|||||  |||||||   ||     |||||||   //|||||  ||||||||||
   ||       ||        ||        ||     ||           ||
   \\|||||  ||        ||        ||     \\|||\\      ||
        ||  ||        ||        ||           ||     ||
   ||||//   ||||||||  |||||| ||||||||   |||||//     ||
///////////////////////////////////////////////////////////////////////////*/


    //Song connect w/o user data
    public List<SCsongdata> getsongconnectlist1(  List<List<String>> genres, String song_spot_feat) {
        SQLiteDatabase db = getReadableDatabase();
        SCListData = setSCListData();
        if (SCListData == null) {
            SCListData = getSCListData();
        }
        String selectquery = "Select * from " + TABLE_MAIN_SONG_INFO;
        Cursor c = db.rawQuery(selectquery, null);
        SClist = new ArrayList<>();
        List<SCsongdata> Laglist = new ArrayList<>();
        String[] spotifyfeats = song_spot_feat.split("\\.");
        float maxval = 0;
        for (int w = 0; w < genres.size(); w++) {
            maxval += 100 / Float.valueOf(SCListData[1]);
            if (genres.get(w).size() > 1) {
                for (int b = 1; b < genres.get(w).size(); b++) {
                    maxval += 100 / Float.valueOf(SCListData[2]);
                }
            }
        }
        for (int p = 3; p < SCListData.length; p++) {
            maxval += 100 / Float.valueOf(SCListData[p]);
        }
        // SC_DATA would have GENRE_MULT/SUBGENRE_MULT/TEMPO_RANGE/ENERGY_RANGE/VALENCE_RANGE/DANCEABILITY_RANGE/ACOUSTICNESS_RANGE/
        //LIVENESS_RANGE, LOUDNESS_RANGE, INSTRUMENTALNESS_RANGE/POPULARITY_RANGE/RATING_1_MULT/RATING_2MULT/RATING_3MULT/FRESH_MULT
        //String[] SCListData;
        //Add genre
        // Log.e("Song genres", savedsong.get_genres());
        AvgStddif = Arrays.asList(0f, 0f, 0f);
        boolean first = true;
        List<String> columnnames = Arrays.asList("popularity", "ratingval1", "ratingval2", "ratingval3", "freshval");
        try{
        while (c.moveToNext()) {
            float connectscore = 0f;
            float maxleft = maxval;
            Log.e("1", String.valueOf(connectscore));
            for (int w = 0; w < genres.size(); w++) {//GENRES
                if (c.getString(c.getColumnIndex(COLUMN_MYGENRE)).contains(genres.get(w).get(0))) {
                    Log.e("2", String.valueOf(connectscore));
                    connectscore += 100 / Float.valueOf(SCListData[1]);
                    if (genres.get(w).size() > 1) {
                        for (int b = 1; b < genres.get(w).size(); b++) {
                            if (c.getString(c.getColumnIndex(COLUMN_MYGENRE)).contains(genres.get(w).get(b))) {
                                connectscore += 100 / Float.valueOf(SCListData[2]);
                            }
                            maxleft -= 100 / Float.valueOf(SCListData[2]);
                        }
                    }
                }
                maxleft -= 100 / Float.valueOf(SCListData[1]);
            }
            int ok = 2;
            int otro = 0;
            String[] Spotdata = c.getString(c.getColumnIndex(COLUMN_SPOTIFY_DATA)).split("\\.");
    try{//maxleft > AvgStddif.get(0) &&
            while ( ok<10) {
                //NEED to check to make sure spot data and features are aligned
                Log.e("3", String.valueOf(connectscore));
                if (ok < 9) {
                    if (Math.abs(Float.valueOf(Spotdata[ok]) - Float.valueOf(spotifyfeats[ok])) < Float.valueOf(SCListData[ok])) {
                        connectscore += 100 / Float.valueOf(SCListData[ok]);
                    }
                    maxleft -= 100 / Float.valueOf(SCListData[ok]);
                }/* else { //TODO ADD BACK POPULARITY AND GENREetc
                    if (Math.abs(Float.valueOf(Spotdata[ok]) - c.getFloat(c.getColumnIndex(columnnames.get(otro)))) < Float.valueOf(SCListData[ok])) {
                        connectscore += 100 / Float.valueOf(SCListData[ok]);
                    }
                    maxleft -= 100 / Float.valueOf(SCListData[ok]);
                    otro += 1;
                }*/
                ok ++;
            }}catch(Exception e){}

            if (!first &&connectscore > SClist.get(SClist.size()-1).getSC_score()) {
                SCsongdata newsong = new SCsongdata(c.getInt(c.getColumnIndex(COLUMN_ID)),connectscore, c.getString(c.getColumnIndex(COLUMN_SONGNAME)),
                        c.getString(c.getColumnIndex(COLUMN_ARTISTS)), c.getString(c.getColumnIndex(COLUMN_SONGID)));
                if (SClist.size() < 29) {
                    Log.e(c.getString(c.getColumnIndex(COLUMN_SONGNAME)),String.valueOf(connectscore) );
                    addValueBinary(newsong);

                } else if (SClist.size() == 30) { //when list gets to 30 songs
                    //INITIAL SORT
                    addValue(newsong);
                    getstdevSort();
                 } else {
                    addValue(newsong);
                    Log.e(c.getString(c.getColumnIndex(COLUMN_SONGNAME)), String.valueOf(connectscore) + 1);
                }
            }else{
                SCsongdata newsong = new SCsongdata(c.getInt(c.getColumnIndex(COLUMN_ID)),connectscore, c.getString(c.getColumnIndex(COLUMN_SONGNAME)),
                        c.getString(c.getColumnIndex(COLUMN_ARTISTS)), c.getString(c.getColumnIndex(COLUMN_SONGID)));
                    SClist.add(newsong);
                AvgStddif.set(0, connectscore);
                first = false;
            }

        }} finally{
            c.close();
            db.close();
        }

        // Log.e("Check end", String.valueOf(exratvals));
        return SClist;
    }
///USE FUNCTION ONLY AFTER LIST GETS TO 30
    private void addValue(SCsongdata song) {
        float val = song.getSC_score();
        float avg = AvgStddif.get(0);
        float stdev = AvgStddif.get(1);
        float del = val - avg;
        Log.e("4", String.valueOf(val));
        //SO s^2 = 29+ SUM(x-xavg)^2 keep track of s change through SUM Val
        float avgnew = (avg * 30 - SClist.get(SClist.size() - 1).getSC_score() + val) / 30;
        SClist.remove(29);

        if (del > 0) {
            if (del < stdev / 2) {
                int y = SClist.size() / 2;
                while (val < SClist.get(y).getSC_score()) {
                    float newsum = AvgStddif.get(2) - ((SClist.get(y).getSC_score() - avg) * (SClist.get(y).getSC_score() - avg) +
                            ((SClist.get(y).getSC_score() - avgnew) * (SClist.get(y).getSC_score() - avgnew)));
                    AvgStddif.set(2, newsum);
                    y++;
                } SClist.add(y, song);
            } else if (del < stdev) {
                int y = SClist.size() / 3;
                while (val < SClist.get(y).getSC_score()) {
                    float newsum = AvgStddif.get(2) - ((SClist.get(y).getSC_score() - avg) * (SClist.get(y).getSC_score() - avg) +
                            ((SClist.get(y).getSC_score() - avgnew) * (SClist.get(y).getSC_score() - avgnew)));
                    AvgStddif.set(2, newsum);
                    y++;
                } SClist.add(y, song);
            } else { //In case of problems + when 2 stdev away
                int y = SClist.size()-1;
                while (val < SClist.get(y).getSC_score()) {
                    float newsum = AvgStddif.get(2) - ((SClist.get(y).getSC_score() - avg) * (SClist.get(y).getSC_score() - avg) +
                            ((SClist.get(y).getSC_score() - avgnew) * (SClist.get(y).getSC_score() - avgnew)));
                    AvgStddif.set(2, newsum);
                    y--;
                } SClist.add(y, song);
            }
        } else if (del < 0) {
            if (del < (-stdev / 2)) {
                int y = SClist.size() / 2;
                while (val > SClist.get(y).getSC_score()) {
                    float newsum = AvgStddif.get(2) - ((SClist.get(y).getSC_score() - avg) * (SClist.get(y).getSC_score() - avg) +
                            ((SClist.get(y).getSC_score() - avgnew) * (SClist.get(y).getSC_score() - avgnew)));
                    AvgStddif.set(2, newsum);
                    y--;
                } SClist.add(y, song);
            } else if (del > -stdev) {
                int y = SClist.size() / 3;
                while (val > SClist.get(y).getSC_score()) {
                    float newsum = AvgStddif.get(2) - ((SClist.get(y).getSC_score() - avg) * (SClist.get(y).getSC_score() - avg) +
                            ((SClist.get(y).getSC_score() - avgnew) * (SClist.get(y).getSC_score() - avgnew)));
                    AvgStddif.set(2, newsum);
                    y--;
                } SClist.add(y, song);
            } else {
                int y = 0;
                while (val > SClist.get(y).getSC_score()) {
                    float newsum = AvgStddif.get(2) - ((SClist.get(y).getSC_score() - avg) * (SClist.get(y).getSC_score() - avg) +
                            ((SClist.get(y).getSC_score() - avgnew) * (SClist.get(y).getSC_score() - avgnew)));
                    AvgStddif.set(2, newsum);
                    y++;
                } SClist.add(y, song);
            }
        }
        float newstdev = (float) Math.sqrt(AvgStddif.get(2)/29);
        AvgStddif.set(1, newstdev);
        AvgStddif.set(0, avgnew);


    }
    private void addValueBinary(SCsongdata song) {
        float val = song.getSC_score();
        float avg = AvgStddif.get(0);
        Log.e("5", String.valueOf(val));
        List<SCsongdata> SClistfilt = SClist;
        int key = SClistfilt.size()/2;
        int index = 0;
        while(key>=1){
            if(val>SClistfilt.get(key).getSC_score()){
                index+=key;
                SClistfilt =SClistfilt.subList(key, SClistfilt.size());
                key = (SClistfilt.size())/2;
            }else {
                SClistfilt =SClistfilt.subList(0, key);
                key = (SClistfilt.size())/2;
            }
        }SClist.add(song);
        AvgStddif.set(0, avg+ 1/(SClist.size()));


    }

private void getstdevSort(){
        float stdevpt = 0;
        float avg = AvgStddif.get(0);
        for (int j = 0; j<SClist.size();j++){
            stdevpt+= (SClist.get(j).getSC_score()-avg)*(SClist.get(j).getSC_score()-avg);
        }
        float stdev = (float) Math.sqrt( stdevpt/29);
    AvgStddif.set(1, stdev);
    AvgStddif.set(2, stdevpt);

        //PROBABLY DONT NEED THIS AND CAN USE A SORTING CLASS THAT I MAKE

}



    public String[] getSCListData(){
        SQLiteDatabase db = getReadableDatabase();
        String[] SCLISTDATA;
        String selectquery = "Select * from " + TABLE_GENERAL_INFO;
        Cursor c = db.rawQuery(selectquery, null);
        if (c.moveToNext()) {
            SCLISTDATA = c.getString(c.getColumnIndex(COLUMN_SC_LIST_DATA)).split("\\.");
            return SCLISTDATA;
        }
        return null;
    }
    public String[] setSCListData(){
        SQLiteDatabase db = getReadableDatabase();
        String SCLISTDATA = ".20.30.30.40.30.20.30.30.40.60.70.70.30.90.90.90.90";
                // GENRE_MULT/SUBGENRE_MULT/POPULARITY_RANGE/TEMPO_RANGE/ENERGY_RANGE/VALENCE_RANGE/DANCEABILITY_RANGE/ACOUSTICNESS_RANGE/
        //LIVENESS_RANGE, LOUDNESS_RANGE, INSTRUMENTALNESS_RANGE/RATING_1_MULT/RATING_2MULT/RATING_3MULT/FRESH_MULT
        String selectquery = "Select * from " + TABLE_GENERAL_INFO;
        Cursor c = db.rawQuery(selectquery, null);
        if (c.moveToNext()) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_SC_LIST_DATA, SCLISTDATA);
            db.update(TABLE_GENERAL_INFO, values, COLUMN_ID + " = ?",
                    new String[]{String.valueOf(c.getInt(c.getPosition()))});

        }
        return  SCLISTDATA.split("\\.");
    }






////////////////////////////////////////////////////////////////////////////
/* //|||||  ||||||||   ||\   ||    ///\\\
   ||       ||    ||   ||\\  ||   //
   \\|||||  ||    ||   || \\ ||   ||  ||\\
        ||  ||    ||   ||  \\||   \\    ||
   ||||//   ||||||||   ||   \||    \\||//
///////////////////////////////////////////////////////////////////////////*/


    public void databaseToString2() {
        //String dbString = "";
        SQLiteDatabase db = getWritableDatabase();

        String query = "Select * from " + TABLE_MAIN_SONG_INFO;

        Cursor c = db.rawQuery(query, null);
        Log.e("TAG", "Database1");
        if (c.moveToFirst()) {
            do {
                String songname = c.getString(c.getColumnIndex(COLUMN_SONGNAME));
                Log.e("Song Name", c.getString(c.getColumnIndex(COLUMN_SONGNAME)));
            } while (c.moveToNext());
        }
        c.close();
        db.close();
    }
    public FullSongdata getCurrentSong2(String songuri){
        SQLiteDatabase db = getReadableDatabase();
        String selectquery = "Select * from " + TABLE_MAIN_SONG_INFO + " WHERE " + COLUMN_SONGID + "=\"" + songuri + "\";";
        Cursor c = db.rawQuery(selectquery, null);
        FullSongdata song = new FullSongdata();
        if (c.moveToFirst()) {

            song.setSong_id(c.getString(c.getColumnIndex(COLUMN_SONGID)));
            song.setSong_name(c.getString(c.getColumnIndex(COLUMN_SONGNAME)));
            song.setRatecount(c.getInt(c.getColumnIndex(COLUMN_RATECOUNT)));
            song.setExratings(c.getInt(c.getColumnIndex(COLUMN_EXRATINGVAL)));
            song.setMygenres(c.getString(c.getColumnIndex(COLUMN_MYGENRE)));
            song.setFreshval(c.getInt(c.getColumnIndex(COLUMN_FRESHVAL)));
            // Log.e("ratingvalsgetsong", String.valueOf(c.getInt(c.getColumnIndex(COLUMN_RATINGVAL1))) + " - " + String.valueOf(c.getInt(c.getColumnIndex(COLUMN_RATINGVAL2))) + " - " + String.valueOf(c.getInt(c.getColumnIndex(COLUMN_RATINGVAL3))));
            song.setRatingval1(c.getInt(c.getColumnIndex(COLUMN_RATINGVAL1)));
            song.setRatingval2(c.getInt(c.getColumnIndex(COLUMN_RATINGVAL2)));
            song.setRatingval3(c.getInt(c.getColumnIndex(COLUMN_RATINGVAL3)));
            song.setScore(c.getInt(c.getColumnIndex(COLUMN_SCORE)));
            song.setFavsec(c.getInt(c.getColumnIndex(COLUMN_FAVSEC)));
            song.setPopularity(c.getInt(c.getColumnIndex(COLUMN_POPULARITY)));
            song.setSpotify_data(c.getString(c.getColumnIndex(COLUMN_SPOTIFY_DATA)));


            // }
            c.close();
            db.close();
        }
        return song;

    }

    public FullSongdata getCurrentSongbyName(String songname){
        SQLiteDatabase db = getReadableDatabase();
        FullSongdata song = new FullSongdata();
        String selectquery = "Select * from " + TABLE_MAIN_SONG_INFO + " WHERE " + COLUMN_SONGNAME + "=\"" + songname + "\";";
        Cursor c = db.rawQuery(selectquery, null);

        if (c.moveToFirst()) {

            song.setSong_id(c.getString(c.getColumnIndex(COLUMN_SONGID)));
            song.setSong_name(c.getString(c.getColumnIndex(COLUMN_SONGNAME)));
            song.setRatecount(c.getInt(c.getColumnIndex(COLUMN_RATECOUNT)));
            song.setExratings(c.getInt(c.getColumnIndex(COLUMN_EXRATINGVAL)));
            song.setMygenres(c.getString(c.getColumnIndex(COLUMN_MYGENRE)));
            song.setFreshval(c.getInt(c.getColumnIndex(COLUMN_FRESHVAL)));
            // Log.e("ratingvalsgetsong", String.valueOf(c.getInt(c.getColumnIndex(COLUMN_RATINGVAL1))) + " - " + String.valueOf(c.getInt(c.getColumnIndex(COLUMN_RATINGVAL2))) + " - " + String.valueOf(c.getInt(c.getColumnIndex(COLUMN_RATINGVAL3))));
            song.setRatingval1(c.getInt(c.getColumnIndex(COLUMN_RATINGVAL1)));
            song.setRatingval2(c.getInt(c.getColumnIndex(COLUMN_RATINGVAL2)));
            song.setRatingval3(c.getInt(c.getColumnIndex(COLUMN_RATINGVAL3)));
            song.setScore(c.getInt(c.getColumnIndex(COLUMN_SCORE)));
            song.setFavsec(c.getInt(c.getColumnIndex(COLUMN_FAVSEC)));
            song.setPopularity(c.getInt(c.getColumnIndex(COLUMN_POPULARITY)));
            song.setSpotify_data(c.getString(c.getColumnIndex(COLUMN_SPOTIFY_DATA)));
            c.close();
            db.close();
        }
            return song;


    }
    public boolean deletesongfromdb(String songid){
        SQLiteDatabase db = getWritableDatabase();

        return db.delete(TABLE_MAIN_SONG_INFO, COLUMN_SONGID + "=" + songid, null) > 0;
    }








    ////////////////////////////////////////////////////////////////////////////
/* |||||||  \\   // ||||||  |||||\   ||       |||||||| |||||||  ||\\\
   ||        \\ //    ||    ||  ||       ||  ||
   |||||||    |||     ||    ||||\  ||||||   ||///
   ||        // \\    ||    ||  \\     ||    ||       || \\
   |||||||  //   \\   ||    ||   \\  ||    |||||||  ||  \\
///////////////////////////////////////////////////////////////////////////*/

public void setUserGenres(){
    String mainlist = "rap rock pop edm indie";
    String relist = "indie pop edm hip hop alternative classic modern funk soul house tropical dance chillwave dance downbeat folk";
    usergenrelist = Arrays.asList(mainlist, relist);
}


    /*public String spotgenretomygenre(String spotgenre){
    StringBuilder mygenreval = new StringBuilder(50);
    updateglist();
    for (int y=0; y<gtestlist.size(); y++){
        if (spotgenre.contains(gtestlist.get(y))&& !mygenreval.toString().contains(gtestlist.get(y))){
            mygenreval.append(gtestlist.get(y)+",") ;
        }
    }
    try {
        mygenreval.deleteCharAt(mygenreval.lastIndexOf(","));
    }catch(Exception e){

    }
    Log.e("MyGenreVal", mygenreval.toString());
    return mygenreval.toString();
}*/
    public String spotgenretomygenre2(String spotgenre) {
        //StringBuilder mygenreval = new StringBuilder(50);
        if (spotgenre.length() != 0) {
            // genlist = Arrays.asList("Rap", "Rock", "Pop", "Edm", "Indie");
            List<String> spotsplit = new ArrayList<>();
            List<String> genreseperated = Arrays.asList("", "", "", "", "", "");
            try {
                spotsplit = Arrays.asList(spotgenre.split(",")); //Splits spotify genres into list
                String mainlist = "rap rock pop edm indie house hop";
                String relist = "indie pop edm hip hop alternative classic modern funk soul house tropical dance chillwave dance downbeat folk";

                Log.e("initial split", String.valueOf(spotsplit));
                // List<String> genreseperated = Arrays.asList("", "", "", "", "", "");
                for (int y = 0; y < spotsplit.size(); y++) { //starts with  going through each genre
                    try {
                        List<String> front = Arrays.asList(spotsplit.get(y).split(" ")); //splits
                        Log.e("second split", String.valueOf(front));
                        Log.e("second split2", front.get(front.size() - 1));
                        if (mainlist.contains(front.get(front.size() - 1))) { //sees if the last word is same as the main genres
                            for (int o = 0; o < 3; o++) { //goes back through and sees if any genres match subgenres
                                if (genreseperated.get(o).length() == 0 || genreseperated.get(o).contentEquals(front.get(front.size() - 1))) {

                                   // if (spotsplit.get(y).contains("hip hop")){ genreseperated.set(o, "rap");genreseperated.set(o+3, "-hiphop");
                                  //  }
                                     if (spotsplit.get(y).contains("house")){ genreseperated.set(o, "edm");genreseperated.set(o+3, "-house");}
                                    else {
                                        genreseperated.set(o, front.get(front.size() - 1));//Sets genre list to include genre in next availible spot

                                    try {
                                        for (int l = 0; l < front.size() - 1; l++) {
                                            if (front.get(l).length() > 2 && relist.contains(front.get(l))) { //Skips spaces
                                                //mygenreval.append("-" + front.get(l));
                                                String p = genreseperated.get(o + 3) + "-" + front.get(l);
                                                genreseperated.set(o + 3, p);
                                            }}
                                    } catch (Exception e) {}}
                                    o = 3;}}}
                    } catch (Exception e) {}
                }
            } catch (Exception e) {
                Log.e("insideerror", "o");
            }
            StringBuilder gval = new StringBuilder();
            boolean isfound = false;
            for (int g = 0; g < 3; g++) {
                if (genreseperated.get(g).length() > 0) {
                    if (isfound) {
                        gval.append(",");
                    }
                    gval.append(" " + genreseperated.get(g) + genreseperated.get(g + 3));
                    isfound = true;
                } else {
                    g = 3;
                }
            }
            Log.e("all", gval.toString());
            return gval.toString();
        }else{
            return "";
        }
    }
    public String spotgenretomygenre3(String spotgenre) {
        //StringBuilder mygenreval = new StringBuilder(50);
        if (spotgenre.length() != 0) {
            // genlist = Arrays.asList("Rap", "Rock", "Pop", "Edm", "Indie");
            List<String> spotsplit = new ArrayList<>();
            List<String> genreseperated = Arrays.asList("", "", "", "", "", "");
            try {
                spotsplit = Arrays.asList(spotgenre.split(",")); //Splits spotify genres into list
                String mainlist = "rap rock pop edm indie";
                String relist = "indie pop edm hip hop alternative classic modern funk soul house tropical dance chillwave dance downbeat folk";
                //List<List<String>> splitlist = Arrays.asList(Arrays.asList("rap hip hop);

                Log.e("initial split", String.valueOf(spotsplit));
                if (spotsplit.contains("hip hop")){ genreseperated.set(0, "hiphop");}
                if (spotsplit.contains("house")){ genreseperated.set(0, "house-edm");}
                // List<String> genreseperated = Arrays.asList("", "", "", "", "", "");
                for (int y = 0; y < spotsplit.size(); y++) { //starts with  going through each genre
                    try {
                        List<String> front = Arrays.asList(spotsplit.get(y).split(" ")); //splits
                       // Log.e("second split", String.valueOf(front));
                       // Log.e("second split2", front.get(front.size() - 1));
                        if (mainlist.contains(front.get(front.size() - 1))) { //sees if the last word is same as the main genres
                            for (int o = 0; o < 3; o++) { //goes back through and sees if any genres match subgenres
                                if (genreseperated.get(o).length() == 0 || genreseperated.get(o).contentEquals(front.get(front.size() - 1))) {
                                    genreseperated.set(o, front.get(front.size() - 1)); //Sets genre list to include genre in next availible spot
                                    try {
                                        for (int l = 0; l < front.size() - 1; l++) {
                                            if (front.get(l).length() > 2 && relist.contains(front.get(l))) { //Skips spaces
                                                //mygenreval.append("-" + front.get(l));
                                                String p = genreseperated.get(o + 3) + "-" + front.get(l);
                                                genreseperated.set(o + 3, p);
                                            }}
                                    } catch (Exception e) {}
                                    o = 3;}}}
                    } catch (Exception e) {}
                }
            } catch (Exception e) {
                Log.e("insideerror", "o");
            }
            StringBuilder gval = new StringBuilder();
            boolean isfound = false;
            for (int g = 0; g < 3; g++) {
                if (genreseperated.get(g).length() > 0) {
                    if (isfound) {
                        gval.append(",");
                    }
                    gval.append(" " + genreseperated.get(g) + genreseperated.get(g + 3));
                    isfound = true;
                } else {
                    g = 3;
                }
            }
            Log.e("all", gval.toString());
            return gval.toString();
        }else{
            return "";
        }
    }

    //TODO update the db to have all spotify extras in 1 string
/*
 public List<String> getsongconnectlist1(FullSongData savedsong) {
        SQLiteDatabase db = getReadableDatabase();
        SCListData = setSCListData();
        if (SCListData==null){
            SCListData = getSCListData();
        }
        String selectquery = "Select * from " + TABLE_MAIN_SONG_INFO;
        Cursor c = db.rawQuery(selectquery, null);
        List<SCsongdata>  Leadlist= new ArrayList<>();
        List<SCsongdata>  Laglist= new ArrayList<>();
        String[] spotifyfeats = savedsong
        TreeMap<Integer, String> connect = new TreeMap<>();
       // Map< Integer, String> newMap = new TreeMap(Collections.reverseOrder());
        //newMap.putAll(connect);
        // SC_DATA would have GENRE_MULT/SUBGENRE_MULT/POPULARITY_RANGE/TEMPO_RANGE/ENERGY_RANGE/VALENCE_RANGE/DANCEABILITY_RANGE/ACOUSTICNESS_RANGE/
        //LIVENESS_RANGE, LOUDNESS_RANGE, INSTRUMENTALNESS_RANGE/RATING_1_MULT/RATING_2MULT/RATING_3MULT/FRESH_MULT
        String[] SCListData;
        try {
            boolean nogenre = false;
            List<String> genreList = new ArrayList<>();
            if (savedsong.get_genres()!=null) {
                genreList = Arrays.asList(savedsong.get_genres().split("\\s*,\\s*"));
            }else {
                nogenre =true;
            }
           // Log.e("Song genres", savedsong.get_genres());
            while (c.moveToNext()) {
                int connectscore = 0;
                if (!nogenre && c.getString(c.getColumnIndex(COLUMN_SPOTIFY_GENRES))!=null){
                for (int j=0; j<genreList.size(); j++){
                    if (c.getString(c.getColumnIndex(COLUMN_SPOTIFY_GENRES)).contains(genreList.get(j))){
                        connectscore+=1;
                       // Log.e("G hit: ", c.getString(c.getColumnIndex(COLUMN_SONGNAME)));
                    }
                }}

                if ((savedsong.get_energy()-.2)<c.getInt(c.getColumnIndex(COLUMN_ENERGY))&& c.getInt(c.getColumnIndex(COLUMN_ENERGY)) < (savedsong.get_energy()+.2)) {
                    connectscore += 1;
                }
                if ((savedsong.get_tempo()-20)<c.getInt(c.getColumnIndex(COLUMN_TEMPO))&& c.getInt(c.getColumnIndex(COLUMN_TEMPO)) < (savedsong.get_tempo()+20)) {
                    connectscore += 1;
                }
                if ((savedsong.get_valence()-.2)<c.getInt(c.getColumnIndex(COLUMN_VALENCE))&& c.getInt(c.getColumnIndex(COLUMN_VALENCE)) < (savedsong.get_valence()+.2)) {
                    connectscore += 1;
                }
                if ((savedsong.get_danceability()-.2)<c.getInt(c.getColumnIndex(COLUMN_DANCEABILITY))&& c.getInt(c.getColumnIndex(COLUMN_DANCEABILITY)) < (savedsong.get_danceability()+.2)) {
                    connectscore += 1;
                }
                if ((savedsong.get_popularity()-.2)<c.getInt(c.getColumnIndex(COLUMN_POPULARITY))&& c.getInt(c.getColumnIndex(COLUMN_POPULARITY)) < (savedsong.get_popularity()+.2)) {
                    connectscore += 1;
                }
                if ((savedsong.get_acousticness()-.2)<c.getInt(c.getColumnIndex(COLUMN_ACOUSTICNESS))&& c.getInt(c.getColumnIndex(COLUMN_ACOUSTICNESS)) < (savedsong.get_acousticness()+.2)) {
                    connectscore += 1;
                }

                if (connectscore>1 && !c.getString(c.getColumnIndex(COLUMN_SONGNAME)).equals(savedsong.get_songname())){
                    //connect.put(connectscore, c.getString(c.getColumnIndex(COLUMN_SONGNAME)));
                    c2.add(String.valueOf(connectscore)+ " - "+ c.getString(c.getColumnIndex(COLUMN_SONGNAME))+ "-" + c.getString(c.getColumnIndex(COLUMN_ARTISTS))+"/"+c.getString(c.getColumnIndex(COLUMN_SONGID)) );
                    //Log.e("Song", c.getString(c.getColumnIndex(COLUMN_SONGNAME)));
                }

            }
        } finally {
            c.close();
            db.close();
        }

        // Log.e("Check end", String.valueOf(exratvals));
        return c2;
    }
public List<Basicsong> getslimrequestlistgen1(List<String> mygval, int popularmin, int popularmax, int mood, int monthadded, String releaseyear) {
        SQLiteDatabase db = getReadableDatabase();

        String selectquery = "Select * from " + TABLE_MAIN_SONG_INFO;
        Cursor c = db.rawQuery(selectquery, null);
        updateglist();
        String monthaddedstr;
        if (monthadded< 10 ) {
            monthaddedstr = "-0" + String.valueOf(monthadded) + "-";
        }else{
            monthaddedstr = "-" + String.valueOf(monthadded) + "-";
        }
        List<Basicsong> songs = new ArrayList<>();
        try {
            while (c.moveToNext()) {
                if (popularmin < c.getInt(c.getColumnIndex(COLUMN_POPULARITY)) && c.getInt(c.getColumnIndex(COLUMN_POPULARITY)) < popularmax && (monthadded == 0 || c.getString(c.getColumnIndex(COLUMN_ADDED_DATE)).contains(monthaddedstr)) &&
                        (releaseyear.contentEquals("NA") || c.getString(c.getColumnIndex(COLUMN_RELEASEDATE)).contains(releaseyear))) {

//TODO add mood filter
                    if ((mood==0)|| (mood==1 && c.getDouble(c.getColumnIndex(COLUMN_ENERGY))>.800 && c.getInt(c.getColumnIndex(COLUMN_TEMPO))>100)||(mood==2 && c.getDouble(c.getColumnIndex(COLUMN_VALENCE))>.80) ||(mood==3 && c.getDouble(c.getColumnIndex(COLUMN_VALENCE))<.30 &&c.getDouble(c.getColumnIndex(COLUMN_ENERGY))<.30 )){
                        int i = 0;
                            while (i < mygval.size()) {
                                if (c.getString(c.getColumnIndex(COLUMN_MYGVAL)).contains(mygval.get(i))) {
                                    Basicsong song = new Basicsong();
                                    song.set_songname(c.getString(c.getColumnIndex(COLUMN_SONGNAME)));
                                    song.set_song_id(c.getString(c.getColumnIndex(COLUMN_SONGID)));
                                    song.set_id(c.getInt(0));
                                    // adding to todo list
                                    songs.add(song);
                                    i = mygval.size();
                                } else {
                                    i += 1;
                                }
                            } }}}}finally {
                        c.close();
                        db.close();
                    }
        return songs;
    }
    public List<Basicsong> getslimrequestlistgen2(int popularmin, int popularmax, int monthadded, String releaseyear) {
        SQLiteDatabase db = getReadableDatabase();

        String selectquery = "Select * from " + TABLE_MAIN_SONG_INFO;
        Cursor c = db.rawQuery(selectquery, null);
        updateglist();
        String monthaddedstr;
        if (monthadded< 10 ) {
            monthaddedstr = "-0" + String.valueOf(monthadded) + "-";
        }else{
            monthaddedstr = "-" + String.valueOf(monthadded) + "-";
        } List<Basicsong> songs = new ArrayList<>();
        try {
            while (c.moveToNext()) {
                if (popularmin < c.getInt(c.getColumnIndex(COLUMN_POPULARITY)) && c.getInt(c.getColumnIndex(COLUMN_POPULARITY)) < popularmax && (monthadded == 0 || c.getString(c.getColumnIndex(COLUMN_ADDED_DATE)).contains(monthaddedstr)) &&
                        (releaseyear.contentEquals("NA") || c.getString(c.getColumnIndex(COLUMN_RELEASEDATE)).contains(releaseyear))) {
//TODO add mood filter
                   // if ((mood==0)|| (mood==1 && c.getDouble(c.getColumnIndex(COLUMN_ENERGY))>.800 && c.getInt(c.getColumnIndex(COLUMN_TEMPO))>100)||(mood==2 && c.getDouble(c.getColumnIndex(COLUMN_VALENCE))>.80) ||(mood==3 && c.getDouble(c.getColumnIndex(COLUMN_VALENCE))<.30 &&c.getDouble(c.getColumnIndex(COLUMN_ENERGY))<.30 )){
                        int i = 0;
                                Basicsong song = new Basicsong();
                                song.set_songname(c.getString(c.getColumnIndex(COLUMN_SONGNAME)));
                                song.set_song_id(c.getString(c.getColumnIndex(COLUMN_SONGID)));
                                song.set_id(c.getInt(0));
                                // adding to todo list
                                songs.add(song);
                      // }
            }}}finally {
            c.close();
            db.close();
        }
        return songs;
    }

    public List<Basicsong> getpostupdateSC(FullSongData savedsong) {
        SQLiteDatabase db = getReadableDatabase();
//TODO make this into a hashmap with
        String selectquery = "Select * from " + TABLE_MAIN_SONG_INFO;
        Cursor c = db.rawQuery(selectquery, null);

        List<Basicsong> c2 = new ArrayList<>();
        int top20score = 0;
        // Map< Integer, String> newMap = new TreeMap(Collections.reverseOrder());
        //newMap.putAll(connect);
//TODO Not sure about this genre setup

        try {
            boolean nogenre = false;
            List<String> genreList = new ArrayList<>();
            if (savedsong.get_genres()!=null) {
                genreList = Arrays.asList(savedsong.get_genres().split("\\s*,\\s*"));
            }else {
                nogenre =true;
            }
            // Log.e("Song genres", savedsong.get_genres());

        String currentlimits = "10.20.30.16.12.56";
        List<String> limits= Arrays.asList(currentlimits.split("\\."));
            String hypCurrentsongspotval = "80.20.52.16.12.56";
        List<String> hypsongcurrent= Arrays.asList(hypCurrentsongspotval.split("\\."));
            while (c.moveToNext()) {

                int connectscore = 0;

                if (!nogenre && c.getString(c.getColumnIndex(COLUMN_SPOTIFY_GENRES))!=null){
                    for (int j=0; j<genreList.size(); j++){
                        if (c.getString(c.getColumnIndex(COLUMN_SPOTIFY_GENRES)).contains(genreList.get(j))){
                            connectscore+=1;
                            // Log.e("G hit: ", c.getString(c.getColumnIndex(COLUMN_SONGNAME)));
                        }
                    }}


                String hypotheticalSpotdata = "100.24.56.66.12.56";
                List<String> hypsongfromdb = Arrays.asList(hypotheticalSpotdata.split("\\."));
                for (int o = 0; o< hypsongcurrent.size();o++){
                    if ((connectscore + hypsongcurrent.size() - o)> top20score){
                        if (Math.abs(Integer.parseInt(hypsongcurrent.get(o))-Integer.parseInt(hypsongfromdb.get(o)))<Integer.parseInt(limits.get(o)){
                            connectscore+=1;
                        }
                    }else{
                        o=hypsongcurrent.size();
                    }
                }
                if (connectscore>top20score){
                    if (c2.size()>20){

                    }
                if (connectscore>2 && !c.getString(c.getColumnIndex(COLUMN_SONGNAME)).equals(savedsong.get_songname())){
                    Basicsong song = new Basicsong();

                    song.set_id(c.getInt(0));
                    song.set_songname(c.getString(c.getColumnIndex(COLUMN_SONGNAME)));
                    song.set_song_id(c.getString(c.getColumnIndex(COLUMN_SONGID)));
                    song.set_ratingval(connectscore);
                    c2.add(song);
                }

            }
        } finally {
            c.close();
            db.close();
        }

        // Log.e("Check end", String.valueOf(exratvals));
        return c2;
    }
    */
 /*
    public List<Basicsong> getslimrequestlistex(int tablenum, int rvalmin, int rvalmax, int fvalmin, int fvalmax, int gval, int exratvals) {
        SQLiteDatabase db = getReadableDatabase();
        Log.e("Check1 2", String.valueOf(exratvals));
        String tableq;
        if (tablenum == 2) {
            tableq = TABLE_VIBE2;
            // Log.e("Vibe", "Party");
        } else if (tablenum == 3) {
            tableq = TABLE_VIBE3;
            //Log.e("Vibe", "Study");
        } else {
            tableq = TABLE_VIBE1;
            //Log.e("Vibe", "Chill");
        }
        //String selectquery = "Select * from " + TABLE_VIBEONE + " SQL ORDER BY " + COLUMN_RATINGVAL + " DESC ", new  {};
        Cursor c = db.query(tableq, null, null,
                null, null, null, COLUMN_RATINGVAL + " DESC", null);
        List<Integer> gtestlist = new ArrayList<>();
        gtestlist.add(3);
        gtestlist.add(5);
        gtestlist.add(7);
        gtestlist.add(11);
        gtestlist.add(13);
        gtestlist.add(17);
        gtestlist.add(19);
        gtestlist.add(23);
        gtestlist.add(29);
        List<Basicsong> songs = new ArrayList<>();
        try {
            while (c.moveToNext()) {
                if ((rvalmin < c.getInt(c.getColumnIndex(COLUMN_RATINGVAL))) && (c.getInt(c.getColumnIndex(COLUMN_RATINGVAL)) < rvalmax) && (fvalmin < c.getInt(c.getColumnIndex(COLUMN_FRESHVAL))) && (c.getInt(c.getColumnIndex(COLUMN_FRESHVAL)) < fvalmax)) {
                    int i = 0;
                    // Log.e("Check jjj", String.valueOf(exratvals));
                    while (i < gtestlist.size()) {
                        if ((gval % gtestlist.get(i) == 0) && (c.getInt(c.getColumnIndex(COLUMN_GVAL)) % gtestlist.get(i) == 0)) {
                            //  Log.e("Check zz", String.valueOf(exratvals));

                            Basicsong song = new Basicsong();
                            if (exratvals % 19 != 0 || exratvals % 23 != 0 || exratvals % 29 != 0 || exratvals != 1) {
                                int b = 0;
                                // while (b < 3) {
                                // Log.e("Check pp", String.valueOf(exratvals));
                                //check to make sure they arent

                                //This checks the rating matters
                                //first if checks for 'off' value which adds all, or the all on value and adds the song
                                /*if (exratvals%gtestlist.get(b+6)==0 || (exratvals%(gtestlist.get(b)^2)==0 && exratvals%gtestlist.get(b+3)!=0)) {
                                    song.set_id(c.getInt(0));
                                    song.set_songname(c.getString(c.getColumnIndex(COLUMN_SONGNAME)));
                                    song.set_ratingval(c.getInt(c.getColumnIndex(COLUMN_SONGNAME)));
                                    b = 3;
                                }*/
    //if it does, check cases when song would be omitted
    //start with if song is 1, if div by 11 it is omitted
                                 /*  if (c.getInt(c.getColumnIndex(COLUMN_EXRATINGVAL)) % 3 != 0 && exratvals % 11 != 0) {
                                        //else if (exratvals%gtestlist.get(b+3)==0){
                                        b = 3;
                                      //  Log.e("Check full", String.valueOf(exratvals));
                                    }

                                //This checks for when song is 3 and request omits 3
                                if (c.getInt(c.getColumnIndex(COLUMN_EXRATINGVAL)) % (9) == 0 && (exratvals % (9) != 0 || exratvals % (121) != 0)) {

                                    // Log.e("Check 3 omit", String.valueOf(c.getInt(c.getColumnIndex(COLUMN_EXRATINGVAL))));
                                    // Log.e("Check 3 omit", String.valueOf(gtestlist.get(b) ^ 2));
                                    b=3;

                                }
                                //checks for 2
                                else if (c.getInt(c.getColumnIndex(COLUMN_EXRATINGVAL)) % 3 == 0 && (exratvals % 3 != 0 || exratvals % (121) == 0)) {
                                    b=3;
                                    //Log.e("Check 2 omit", String.valueOf(exratvals));
                                }
                            /*    else if (c.getInt(c.getColumnIndex(COLUMN_EXRATINGVAL)) % 5 != 0 && exratvals % 13 != 0) {
                                    //else if (exratvals%gtestlist.get(b+3)==0){
                                    b = 3;
                                    //Log.e("Check full", String.valueOf(exratvals));
                                }


                                //This checks for when song is 3 and request omits 3
                                else if (c.getInt(c.getColumnIndex(COLUMN_EXRATINGVAL)) % (25) == 0 && (exratvals % (25) != 0 || exratvals % (169) != 0)) {
                                    b=3;
                                    // Log.e("Check 3 omit", String.valueOf(c.getInt(c.getColumnIndex(COLUMN_EXRATINGVAL))));
                                    //Log.e("Check 3 omit", String.valueOf(gtestlist.get(b) ^ 2));

                                }
                                //checks for 2
                                else if (c.getInt(c.getColumnIndex(COLUMN_EXRATINGVAL)) % 5 == 0 && (exratvals % 5 != 0 || exratvals % (169) == 0)) {
                                    b=3;
                                    // Log.e("Check 2 omit", String.valueOf(exratvals));
                                }
                              /*  else if (c.getInt(c.getColumnIndex(COLUMN_EXRATINGVAL)) % 7 != 0 && exratvals % 17 != 0) {
                                    //else if (exratvals%gtestlist.get(b+3)==0){
                                    b = 3;
                                   // Log.e("Check full", String.valueOf(exratvals));
                                }

                                //This checks for when song is 3 and request omits 3
                                else if (c.getInt(c.getColumnIndex(COLUMN_EXRATINGVAL)) % (49) == 0 && (exratvals % (49) != 0 || exratvals % (289) != 0)) {
                                    b=3;
                                    //  Log.e("Check 3 omit", String.valueOf(c.getInt(c.getColumnIndex(COLUMN_EXRATINGVAL))));
                                    // Log.e("Check 3 omit", String.valueOf(gtestlist.get(b) ^ 2));

                                }
                                //checks for 2
                                else if (c.getInt(c.getColumnIndex(COLUMN_EXRATINGVAL)) % 7 == 0 && (exratvals % 7 != 0 || exratvals % (289) == 0)) {
                                    b=3;
                                    // Log.e("Check 2 omit", String.valueOf(exratvals));
                                }
                                //Only adds if checks each extra rating to make sure not omitted
                                else  {
                                    //Log.e("Check ooo", String.valueOf(exratvals));
                                    song.set_id(c.getInt(0));
                                    song.set_songname(c.getString(c.getColumnIndex(COLUMN_SONGNAME)));
                                    song.set_ratingval(c.getInt(c.getColumnIndex(COLUMN_RATINGVAL)));
                                    songs.add(song);
                                    //Log.e("Song", String.valueOf(song));
                                    //Log.e("extra val", String.valueOf(exratvals));
                                }
                                     else {
                                         Log.e("Check through", String.valueOf(exratvals));
                                         b = b + 1;

                                    }

                                    else if (gval % (gtestlist.get(b)^2)!=0 && c.getColumnIndex(COLUMN_GVAL)%(gtestlist.get(b)^2) == 0){
                                        song.set_id(c.getInt(0));
                                        song.set_songname(c.getString(c.getColumnIndex(COLUMN_SONGNAME)));
                                        song.set_ratingval(c.getInt(c.getColumnIndex(COLUMN_SONGNAME)));
                                        b = 3;
                                    }

                                }
                                //checks if only low on and will only add those
                                else if (exratvals%gtestlist.get(b)!=0 && c.getInt(c.getColumnIndex(COLUMN_PLAYLIST_ERMIN))%gtestlist.get(b)!=0){
                                    song.set_id(c.getInt(0));
                                    song.set_songname(c.getString(c.getColumnIndex(COLUMN_SONGNAME)));
                                    song.set_ratingval(c.getInt(c.getColumnIndex(COLUMN_SONGNAME)));
                                    b=3;
                                    }
                                   // checks if
                                else if (exratvals%gtestlist.get(b+3)==0 && c.getInt(c.getColumnIndex(COLUMN_PLAYLIST_ERMIN))%gtestlist.get(b)==0){
                                    song.set_id(c.getInt(0));
                                    song.set_songname(c.getString(c.getColumnIndex(COLUMN_SONGNAME)));
                                    song.set_ratingval(c.getInt(c.getColumnIndex(COLUMN_SONGNAME)));
                                    b = 3;
                                }
                                else if (exratvals%(gtestlist.get(b+3)^2)==0 && c.getInt(c.getColumnIndex(COLUMN_PLAYLIST_ERMIN))%gtestlist.get(b)==0){
                                    song.set_id(c.getInt(0));
                                    song.set_songname(c.getString(c.getColumnIndex(COLUMN_SONGNAME)));
                                    song.set_ratingval(c.getInt(c.getColumnIndex(COLUMN_SONGNAME)));
                                    b = 3;
                                }



                                //int id = c.getInt(0);
                                //int ratingval = c.getInt(c.getColumnIndex(COLUMN_RATINGVAL));
                                //String songid = c.getString(c.getColumnIndex(COLUMN_SONGID));
                                //String songname = c.getString(c.getColumnIndex(COLUMN_SONGNAME));
                                //int ratecount = c.getInt(c.getColumnIndex(COLUMN_RATECOUNT));
                                //song.set_songname(c.getString(c.getColumnIndex(COLUMN_SONGNAME)));
                                // song.set_ratingval(c.getInt(c.getColumnIndex(COLUMN_RATINGVAL)));
                                // song.set_id(c.getInt(0));

                                // adding to todo list

                                i = gtestlist.size();
                            }else {
                                song.set_id(c.getInt(0));
                                song.set_songname(c.getString(c.getColumnIndex(COLUMN_SONGNAME)));
                                song.set_ratingval(c.getInt(c.getColumnIndex(COLUMN_SONGNAME)));
                                songs.add(song);
                                Log.e("Song", String.valueOf(song));
                            }

                        } else {
                            i += 1;
                        }

                    }
                }
            }
        } finally {
            c.close();
            db.close();
        }
        Log.e("Check end", String.valueOf(exratvals));
        return songs;
    }
    */

   /* public void addspotifysongsdata2(List<SpotifyData> spotsongs) {
        SQLiteDatabase db = this.getWritableDatabase();

        int songnum;
        for (songnum = 0; songnum < spotsongs.size(); songnum++) {

            final SpotifyData song = spotsongs.get(songnum);
            final Handler handler2 = new Handler();
            handler2.postDelayed(new Runnable() {
                @Override
                public void run() {
            addspotifysongdata(song);
        }
    }, 1000);
        }}
    public void addspotifysongsdata(List<SpotifyData> spotsongs) {
        SQLiteDatabase db = this.getWritableDatabase();

        int songnum;
        for (songnum = 0; songnum < spotsongs.size(); songnum++) {

            SpotifyData song = spotsongs.get(songnum);
            String selectquery = "Select * from " + TABLE_MAIN_SONG_INFO + " WHERE " + COLUMN_SONGID + "=\"" + song.get_songid() + "\";";
            Cursor c = db.rawQuery(selectquery, null);
            int p = 0;
            if (c.moveToFirst()) {
                p = 1;
                if (c.getString(c.getColumnIndex(COLUMN_SPOTIFY_GENRES)) == null) {
                    ContentValues values = new ContentValues();

                    values.put(COLUMN_SPOTIFY_GENRES, String.valueOf(song.get_spotifygenres()));
                   // Log.e("Spot G" , song.get_spotifygenres());
                    if (c.getString(c.getColumnIndex(COLUMN_MYGENRE)) == null){
                        String newmyval = spotgenretomygenre(song.get_spotifygenres());
                        values.put(COLUMN_MYGENRE, newmyval);
                    }
                    values.put(COLUMN_POPULARITY, String.valueOf(song.get_popularity()));
                    values.put(COLUMN_RELEASEDATE, String.valueOf(song.get_releasedate()));
                    values.put(COLUMN_ADDED_DATE, song.get_added_date());

                    db.update(TABLE_MAIN_SONG_INFO, values, COLUMN_ID + " = ?",
                            new String[]{String.valueOf(c.getInt(c.getPosition()))});


                }
            } else {
                ContentValues values = new ContentValues();
                String newmyval = spotgenretomygenre(song.get_spotifygenres());
               // values.put(COLUMN_MYGVAL, newmyval);
               // Log.e("Spot G new" , song.get_spotifygenres());
                values.put(COLUMN_MYGVAL, newmyval);
                values.put(COLUMN_SPOTIFY_GENRES, String.valueOf(song.get_spotifygenres()));
                values.put(COLUMN_POPULARITY, String.valueOf(song.get_popularity()));
                values.put(COLUMN_RELEASEDATE, String.valueOf(song.get_releasedate()));
                values.put(COLUMN_SONGID, song.get_songid());
                values.put(COLUMN_SONGNAME, song.get_songname());
                values.put(COLUMN_ARTISTS, song.get_artist());
                values.put(COLUMN_ACOUSTICNESS, song.get_acousticness());
                values.put(COLUMN_ENERGY, song.get_energy());
                values.put(COLUMN_INSTRUMENTALNESS, song.get_instrumentalness());
                values.put(COLUMN_LIVENESS, song.get_liveness());
                values.put(COLUMN_LOUDNESS, song.get_loudness());
                values.put(COLUMN_DANCEABILITY, song.get_danceability());
                values.put(COLUMN_TEMPO, song.get_tempo());
                values.put(COLUMN_VALENCE, song.get_valence());

                db.insert(TABLE_MAIN_SONG_INFO, null, values);


            }
            c.close();


        }
        db.close();
    }*/
      /* public List<Basicsong> getsongconnectlist() {
        SQLiteDatabase db = getReadableDatabase();

        String selectquery = "Select * from " + TABLE_MAIN_SONG_INFO ;
        Cursor c = db.rawQuery(selectquery, null);

        List<Basicsong> songs = new ArrayList<>();

        try {
            while (c.moveToNext()) {

                            Basicsong song = new Basicsong();

                                song.set_id(c.getInt(0));
                                song.set_songname(c.getString(c.getColumnIndex(COLUMN_SONGNAME)));
                                song.set_song_id(c.getString(c.getColumnIndex(COLUMN_SONGID)));
                                songs.add(song);
                               // Log.e("Song", String.valueOf(song));
            }
        } finally {
            c.close();
            db.close();
        }
        return songs;
    }*/
       /*
public FullSongData getCurrentSong(String songuri){
    SQLiteDatabase db = getReadableDatabase();
    String selectquery = "Select * from " + TABLE_MAIN_SONG_INFO + " WHERE " + COLUMN_SONGID + "=\"" + songuri + "\";";
    Cursor c = db.rawQuery(selectquery, null);
    FullSongData song = new FullSongData();
    if (c.moveToFirst()) {

            song.set_songid(c.getString(c.getColumnIndex(COLUMN_SONGID)));
            song.set_songname(c.getString(c.getColumnIndex(COLUMN_SONGNAME)));
            //if (c.getInt(c.getColumnIndex(COLUMN_RATECOUNT))!=0) {
            song.set_exratevals(c.getInt(c.getColumnIndex(COLUMN_EXRATINGVAL)));
            song.set_mygval(c.getString(c.getColumnIndex(COLUMN_MYGVAL)));
            song.set_freshvalue(c.getInt(c.getColumnIndex(COLUMN_FRESHVAL)));
           // Log.e("ratingvalsgetsong", String.valueOf(c.getInt(c.getColumnIndex(COLUMN_RATINGVAL1))) + " - " + String.valueOf(c.getInt(c.getColumnIndex(COLUMN_RATINGVAL2))) + " - " + String.valueOf(c.getInt(c.getColumnIndex(COLUMN_RATINGVAL3))));
            song.set_ratingval1(c.getInt(c.getColumnIndex(COLUMN_RATINGVAL1)));
            song.set_ratingval2(c.getInt(c.getColumnIndex(COLUMN_RATINGVAL2)));
            song.set_ratingval3(c.getInt(c.getColumnIndex(COLUMN_RATINGVAL3)));
             song.set_upcount(c.getInt(c.getColumnIndex(COLUMN_UPCOUNT)));
             song.set_downcount(c.getInt(c.getColumnIndex(COLUMN_DOWNCOUNT)));
             song.set_favandsec(c.getInt(c.getColumnIndex(COLUMN_FAVANDSEC)));

            //}
            //if (c.getInt(c.getColumnIndex(COLUMN_POPULARITY))!=0) {
            song.set_popularity(c.getInt(c.getColumnIndex(COLUMN_POPULARITY)));
            song.set_genres(c.getString(c.getColumnIndex(COLUMN_SPOTIFY_GENRES)));
            song.set_acousticness(c.getFloat(c.getColumnIndex(COLUMN_ACOUSTICNESS)));
            song.set_danceability(c.getFloat(c.getColumnIndex(COLUMN_DANCEABILITY)));
            song.set_energy(c.getFloat(c.getColumnIndex(COLUMN_ENERGY)));
            // song.set_mygval(c.getString(c.getColumnIndex(COLUMN_MYGVAL)));
            song.set_instrumentalness(c.getFloat(c.getColumnIndex(COLUMN_INSTRUMENTALNESS)));
            song.set_liveness(c.getFloat(c.getColumnIndex(COLUMN_LIVENESS)));
            song.set_loudness(c.getFloat(c.getColumnIndex(COLUMN_LOUDNESS)));
            song.set_speechiness(c.getFloat(c.getColumnIndex(COLUMN_SPEECHINESS)));
            song.set_tempo(c.getFloat(c.getColumnIndex(COLUMN_TEMPO)));
            song.set_valence(c.getFloat(c.getColumnIndex(COLUMN_VALENCE)));
            //song.set_ratingval1(0);
            //song.set_ratingval2(0);
            //song.set_ratingval3(0);
            //song.set_freshvalue(0);

            // }
            c.close();
            db.close();
        }
        return song;

}*/

}
