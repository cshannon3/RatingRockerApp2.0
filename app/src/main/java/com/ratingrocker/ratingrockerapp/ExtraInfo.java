package com.ratingrocker.ratingrockerapp;


public class ExtraInfo {
    private int _id;
    private int _upcount;
    private int _downcount;
    private int _favandsec;
    private String _songid;
    private String _songname;
    private int _gval;
    private String _mygval;
    private int _rating1;
    private int _rating2;
    private int _rating3;
    private int _fval;

    public ExtraInfo() {

    }

    public ExtraInfo(String songid, String songname, int upcount, int downcount, int favandsec, int gval, String mygval, int rating1, int rating2,int rating3, int fval) {

        this._upcount = upcount;
        this._songid = songid;
        this._songname = songname;
        this._downcount = downcount;
        this._favandsec = favandsec;
        this._gval= gval;
        this._mygval = mygval;
        this._rating1 = rating1;
        this._rating2 = rating2;
        this._rating3 = rating3;
        this._fval = fval;


    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_upcount() {
        return _upcount;
    }

    public void set_upcount(int _upcount) {
        this._upcount = _upcount;
    }

    public int get_downcount() {
        return _downcount;
    }

    public void set_downcount(int _downcount) {
        this._downcount = _downcount;
    }

    public int get_favandsec() {
        return _favandsec;
    }

    public void set_favandsec(int _favandsec) {
        this._favandsec = _favandsec;
    }

    public String get_songid() {
        return _songid;
    }

    public void set_songid(String _songid) {
        this._songid = _songid;
    }

    public String get_songname() {
        return _songname;
    }

    public void set_songname(String _songname) {
        this._songname = _songname;
    }

    public int get_gval() {
        return _gval;
    }

    public void set_gval(int _gval) {
        this._gval = _gval;
    }

    public String get_mygval() {
        return _mygval;
    }

    public void set_mygval(String _mygval) {
        this._mygval = _mygval;
    }

    public int get_rating1() {
        return _rating1;
    }

    public void set_rating1(int _rating1) {
        this._rating1 = _rating1;
    }

    public int get_rating2() {
        return _rating2;
    }

    public void set_rating2(int _rating2) {
        this._rating2 = _rating2;
    }

    public int get_rating3() {
        return _rating3;
    }

    public void set_rating3(int _rating3) {
        this._rating3 = _rating3;
    }

    public int get_fval() {
        return _fval;
    }

    public void set_fval(int _fval) {
        this._fval = _fval;
    }
}
