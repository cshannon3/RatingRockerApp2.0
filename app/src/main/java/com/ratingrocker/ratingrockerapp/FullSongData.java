package com.ratingrocker.ratingrockerapp;



public class FullSongData {


    private int _id;
    private int _popularity;
    private String _genres;
    private int _ratingval1;
    private int _ratingval2;
    private int _ratingval3;
    private int _ratecount;
    private int _freshvalue;
    private String _mygval;
    private int _upcount;
    private int _downcount;
    private int _favandsec;
    private int _exratevals;
    private String _spotifygenres;
    private String _releasedate;
    private String _songname;
    private String _songid;
    private String _artist;
    private float _acousticness;
    private float _danceability;
    private float _energy;
    private float _instrumentalness;
    private float _liveness;
    private float _loudness;
    private float _tempo;
    private float _valence;
    private float _speechiness;


    public FullSongData() {

    }

    public FullSongData(String songname, String songid, String artist, String genres, int popularity, String releasedate, String spotifygenres, float acousticness, float danceability, float loudness, float liveness, float valence, float speechiness, float tempo, float instrumentalness, float energy, int ratingval1, int ratingval2, int ratingval3, int ratecount, int freshvalue, String mygval , int exratevals, int upcount, int downcount, int favandsec) {

        this._songname = songname;
        this._songid = songid;
        this._artist = artist;
        this._genres = genres;
        this._ratingval1 = ratingval1;
        this._ratingval2 = ratingval2;
        this._ratingval3 = ratingval3;
        this._ratecount = ratecount;
        this._freshvalue = freshvalue;
        this._mygval = mygval;
        this._downcount = downcount;
        this._upcount = upcount;
        this._favandsec = favandsec;
        this._exratevals = exratevals;
        this._popularity = popularity;
        this._releasedate = releasedate;
        this._spotifygenres = spotifygenres;
        this._acousticness = acousticness;
        this._danceability = danceability;
        this._energy = energy;
        this._instrumentalness = instrumentalness;
        this._liveness = liveness;
        this._loudness = loudness;
        this._tempo = tempo;
        this._valence = valence;
        this._speechiness = speechiness;


    }


    public String get_spotifygenres() {
        return _spotifygenres;
    }

    public void set_spotifygenres(String _spotifygenres) {
        this._spotifygenres = _spotifygenres;
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

    public int get_popularity() {
        return _popularity;
    }

    public void set_popularity(int _popularity) {
        this._popularity = _popularity;
    }

    public String get_genres() {
        return _genres;
    }

    public void set_genres(String _genres) {
        this._genres = _genres;
    }

    public String get_releasedate() {
        return _releasedate;
    }

    public void set_releasedate(String _releasedate) {
        this._releasedate = _releasedate;
    }

    public String get_songid() {
        return _songid;
    }

    public void set_songid(String _songid) {
        this._songid = _songid;
    }

    public String get_artist() {
        return _artist;
    }

    public void set_artist(String _artist) {
        this._artist = _artist;
    }

    public float get_acousticness() {
        return _acousticness;
    }

    public void set_acousticness(float _acousticness) {
        this._acousticness = _acousticness;
    }

    public float get_danceability() {
        return _danceability;
    }

    public void set_danceability(float _danceability) {
        this._danceability = _danceability;
    }

    public float get_energy() {
        return _energy;
    }

    public void set_energy(float _energy) {
        this._energy = _energy;
    }

    public float get_instrumentalness() {
        return _instrumentalness;
    }

    public void set_instrumentalness(float _instrumentalness) {
        this._instrumentalness = _instrumentalness;
    }

    public float get_liveness() {
        return _liveness;
    }

    public void set_liveness(float _liveness) {
        this._liveness = _liveness;
    }

    public float get_loudness() {
        return _loudness;
    }

    public void set_loudness(float _loudness) {
        this._loudness = _loudness;
    }

    public float get_tempo() {
        return _tempo;
    }

    public void set_tempo(float _tempo) {
        this._tempo = _tempo;
    }

    public float get_valence() {
        return _valence;
    }

    public void set_valence(float _valence) {
        this._valence = _valence;
    }

    public float get_speechiness() {
        return _speechiness;
    }

    public void set_speechiness(float _speechiness) {
        this._speechiness = _speechiness;
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

    public int get_exratevals() {
        return _exratevals;
    }

    public void set_exratevals(int _exratevals) {
        this._exratevals = _exratevals;
    }
}