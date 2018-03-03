package com.ratingrocker.ratingrockerapp;
public class Vibeplaylist {
    private int _id;
    private int _vibenum;
    private String _playlistname;
    private String _playlistinfo;
    private String _playlist_mygval;
    private String _playlist_spotgval;
    private int _added_month;
    private String _release_year;
    private int _ratingmin;
    private int _ratingmax;
    private int _freshmin;
    private int _freshmax;
    private int _exratingsval;

    public Vibeplaylist() {

    }

    public Vibeplaylist(int vibenum, String playlistname,  String playlistinfo, String playlist_mygval, int ratingmin, int ratingmax, int freshmin, int freshmax, int exratingsval, String playlist_spotgval, String release_year, int added_month) {
        this._vibenum = vibenum;
        this._playlistname = playlistname;
        this._playlistinfo = playlistinfo;
        this._ratingmin = ratingmin;
        this._ratingmax = ratingmax;
        this._playlist_mygval = playlist_mygval;
        this._freshmin = freshmin;
        this._freshmax = freshmax;
        this._exratingsval = exratingsval;
        this._playlist_spotgval= playlist_spotgval;
        this._added_month = added_month;
        this._release_year = release_year;

    }

    public int get_added_month() {
        return _added_month;
    }

    public void set_added_month(int _added_month) {
        this._added_month = _added_month;
    }

    public String get_release_year() {
        return _release_year;
    }

    public void set_release_year(String _release_year) {
        this._release_year = _release_year;
    }

    public String get_playlist_mygval() {
        return _playlist_mygval;
    }

    public void set_playlist_mygval(String _playlist_mygval) {
        this._playlist_mygval = _playlist_mygval;
    }
    public String get_playlist_spotgval() {
        return _playlist_spotgval;
    }

    public void set_playlist_spotgval(String _playlist_spotgval) {
        this._playlist_spotgval = _playlist_spotgval;
    }

    public int get_ratingmin() {
        return _ratingmin;
    }

    public void set_ratingmin(int _ratingmin) {
        this._ratingmin = _ratingmin;
    }

    public int get_ratingmax() {
        return _ratingmax;
    }

    public void set_ratingmax(int _ratingmax) {
        this._ratingmax = _ratingmax;
    }

    public int get_freshmin() {
        return _freshmin;
    }

    public void set_freshmin(int _freshmin) {
        this._freshmin = _freshmin;
    }

    public int get_freshmax() {
        return _freshmax;
    }

    public void set_freshmax(int _freshmax) {
        this._freshmax = _freshmax;
    }

    public int get_exratingsmin() {
        return _exratingsval;
    }

    public void set_exratingsmin(int _exratingsmin) {
        this._exratingsval = _exratingsmin;
    }

    public int get_vibenum() {
        return _vibenum;
    }

    public void set_vibenum(int _vibenum) {
        this._vibenum = _vibenum;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }


    public String get_playlistname() {
        return _playlistname;
    }

    public void set_playlistname(String _playlistname) {
        this._playlistname = _playlistname;
    }


    public String get_playlistinfo() {
        return _playlistinfo;
    }

    public void set_playlistinfo(String _playlistinfo) {
        this._playlistinfo = _playlistinfo;
    }
}


