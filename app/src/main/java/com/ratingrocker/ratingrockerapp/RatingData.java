package com.ratingrocker.ratingrockerapp;

public class RatingData {

    private int _id;
    private int _ratingval1;
    private int _ratingval2;
    private int _ratingval3;
    private int _ratecount;
    private int _freshvalue;
    private String _songid;
    private String _songname;
    private String _mygval;
    private int _upcount;
    private int _downcount;
    private int _favandsec;
    private int _exratevals;
    private String _songconnects;


    public RatingData() {

    }

    public RatingData(int ratingval1, int ratingval2, int ratingval3, String songid, String songname, int ratecount, int freshvalue, String mygval , int exratevals, int upcount, int downcount, int favandsec, String songconnect) {

        this._ratingval1 = ratingval1;
        this._ratingval2 = ratingval2;
        this._ratingval3 = ratingval3;
        this._songid = songid;
        this._songname = songname;
        this._ratecount = ratecount;
        this._freshvalue = freshvalue;
        this._mygval = mygval;
        this._downcount = downcount;
        this._upcount = upcount;
        this._favandsec = favandsec;
        this._exratevals = exratevals;
        this._songconnects = songconnect;

    }
    /*
        public Vibeonedata(int _id, String _songid, String _songname, int _ratingval, int _ratecount, int _freshvalue) {
            this._id = _id;
            this._songid = _songid;
            this._ratingval = _ratingval;
            this._ratecount = _ratecount;
            this._freshvalue = _freshvalue;
            this._songname = _songname;
        }
    */



    public String get_songname() {
        return _songname;
    }

    public void set_songname(String _songname) {
        this._songname = _songname;
    }

    public String get_songid() {
        return _songid;
    }

    public void set_songid(String _songid) {
        this._songid = _songid;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_ratecount() {
        return _ratecount;
    }

    public void set_ratecount(int _ratecount) {
        this._ratecount = _ratecount;
    }

    public int get_freshvalue() {
        return _freshvalue;
    }

    public void set_freshvalue(int _freshvalue) {
        this._freshvalue = _freshvalue;
    }

    public int get_exratevals() {
        return _exratevals;
    }

    public void set_exratevals(int _exratevals) {
        this._exratevals = _exratevals;
    }

    public int get_ratingval1() {
        return _ratingval1;
    }

    public void set_ratingval1(int _ratingval1) {
        this._ratingval1 = _ratingval1;
    }

    public int get_ratingval2() {
        return _ratingval2;
    }

    public void set_ratingval2(int _ratingval2) {
        this._ratingval2 = _ratingval2;
    }

    public int get_ratingval3() {
        return _ratingval3;
    }

    public void set_ratingval3(int _ratingval3) {
        this._ratingval3 = _ratingval3;
    }

    public String get_mygval() {
        return _mygval;
    }

    public void set_mygval(String _mygval) {
        this._mygval = _mygval;
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

    public String get_songconnects() {
        return _songconnects;
    }

    public void set_songconnects(String _songconnects) {
        this._songconnects = _songconnects;
    }
}


