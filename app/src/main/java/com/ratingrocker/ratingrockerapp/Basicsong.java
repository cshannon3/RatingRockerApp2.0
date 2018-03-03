package com.ratingrocker.ratingrockerapp;



public class Basicsong {
    private int _id;
    private int _ratingval;

    private String _song_id;
    private String _songname;



    public Basicsong() {

    }

    public Basicsong(int ratingval, String songname, String song_id ) {

        this._ratingval = ratingval;

        this._songname = songname;
        this._song_id = song_id;

    }

    public String get_song_id() {
        return _song_id;
    }

    public void set_song_id(String _song_id) {
        this._song_id = _song_id;
    }

    public String get_songname() {
        return _songname;
    }

    public void set_songname(String _songname) {
        this._songname = _songname;
    }


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_ratingval() {
        return _ratingval;
    }

    public void set_ratingval(int _ratingval) {
        this._ratingval = _ratingval;
    }


}

