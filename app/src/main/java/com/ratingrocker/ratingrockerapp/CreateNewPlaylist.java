
package com.ratingrocker.ratingrockerapp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Pager;
import kaaes.spotify.webapi.android.models.Playlist;
import kaaes.spotify.webapi.android.models.PlaylistTrack;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static android.view.View.GONE;

public class CreateNewPlaylist extends Activity {

    private Spinner dropdown, addedspinner, releasespinner;
    //private Button createbutton, backbutton, playlistsbutton;
    private GridLayout extraratings, inputratingsgrid;
    private ToggleButton expratings;
    MyDBHandler db;
    PlayerActivity playerActivity;
    SpotifyService spotify;
    SpotifyApi api;
    private int addedmonth, vibenum;
    private String  releaseyear;
   // private ToggleButton conf1, conf2, conf3, temp1, temp2, temp3, fit1, fit2, fit3, tempotoggle, fittoggle, conftoggle;;
    private EditText songidInput;
    private ToggleButton happy, sad, amped, verypopular, somewhatpopular, deeppopular;
    private Button addbutton, addUPbutton;
    //LoadPlaylistdata spot;
    private ListView resultsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_playlist);
        db = new MyDBHandler(this);


        vibenum = 1;
        addedmonth = 0;

        //spot = new LoadPlaylistdata();
        //spot.clientAuth();
        dropdown = findViewById(R.id.spinner);
        releasespinner = findViewById(R.id.releasespinner);
        addedspinner = findViewById(R.id.addedspinner);
        happy = findViewById(R.id.happy);

        sad = findViewById(R.id.sad);
        amped = findViewById(R.id.amped);
        verypopular = findViewById(R.id.verypopular);
        somewhatpopular = findViewById(R.id.somewhatpopular);
        deeppopular = findViewById(R.id.deeppopular);
        loadMonthSpinner();
        loadYearSpinner();
        String[] exsongarray = {"song1", "song2", "song3", "song4", "song5"};
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.song_connect_list, exsongarray);
        resultsView = (ListView) findViewById(R.id.results_list);
        resultsView.setAdapter(adapter);
        resultsView.setVisibility(GONE);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView , View view , int position ,long arg3)
            {
                vibenum = dropdown.getSelectedItemPosition();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        releasespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView , View view , int position ,long arg3)
            {
                releaseyear = String.valueOf(releasespinner.getSelectedItem());
                Log.e("Year", String.valueOf(releaseyear));
                //int playthis = dropdown2.getSelectedItemPosition();
                //String playlist = db.getPlaylistid(String.valueOf(dropdown.getSelectedItem()));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                releaseyear = "NA";
            }
        });
        addedspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView , View view , int position ,long arg3)
            {
                addedmonth = addedspinner.getSelectedItemPosition();
                Log.e("Month", String.valueOf(addedmonth));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                addedmonth = 0;
            }
        });
        addbutton = findViewById(R.id.addbutton);
        addbutton.setVisibility(View.GONE);
        songidInput = findViewById(R.id.playlistid);
        songidInput.setVisibility(View.GONE);
        //inputratingsgrid = findViewById(R.id.inputratinggrid);
        //extraratings = findViewById(R.id.extraratings);
        //expratings = (ToggleButton) findViewById(R.id.plusratingd);
        /*fittoggle = (ToggleButton) findViewById(R.id.fittogglebutton);
        tempotoggle = (ToggleButton) findViewById(R.id.tempotogglebutton);
        conftoggle = (ToggleButton) findViewById(R.id.conftogglebutton);
        conf1 = (ToggleButton) findViewById(R.id.conf_1);
        conf2 = (ToggleButton) findViewById(R.id.conf_2);
        conf3 = (ToggleButton) findViewById(R.id.conf_3);
        temp1 = (ToggleButton) findViewById(R.id.tempo_1);
        temp2 = (ToggleButton) findViewById(R.id.tempo_2);
        temp3 = (ToggleButton) findViewById(R.id.tempo_3);
        fit1 = (ToggleButton) findViewById(R.id.fit_1);
        fit2 = (ToggleButton) findViewById(R.id.fit_2);
        fit3 = (ToggleButton) findViewById(R.id.fit_3);*/
// hide until its title is clicked
//        expratings.setOnClickListener(this);
    //    extraratings.setVisibility(View.GONE);
    }


        /*public void onClick(View view) {
            /*if(extraratings.isShown()){
               // Fx.slide_up(this, extraratings);
                extraratings.setVisibility(View.GONE);
            }
            else if (!(extraratings.isShown())){
                extraratings.setVisibility(View.VISIBLE);
               // Fx.slide_down(this, extraratings);
                if (!tempotoggle.isChecked()){
                    temp1.setVisibility(GONE);
                    temp2.setVisibility(GONE);
                    temp3.setVisibility(GONE);
                }
                if (!fittoggle.isChecked()){
                    fit1.setVisibility(GONE);
                    fit2.setVisibility(GONE);
                    fit3.setVisibility(GONE);
                }
                if (!conftoggle.isChecked()){
                    conf1.setVisibility(GONE);
                    conf2.setVisibility(GONE);
                    conf3.setVisibility(GONE);
                }
            }
        }*/


    public String urltouri(String url) {
        int s = 0;
        String uri = url.substring(25, url.length());
        //int uripartstart = 0;
        //int partend = 25;
        String newuri = "spotify:";

        while (s<3){
            String uripart = uri.substring(0, uri.indexOf("/")) + ":";

           // uripartstart = uri.indexOf("/");
            uri = uri.substring((uri.indexOf("/")+1), uri.length());
            newuri = newuri + uripart;

            s+=1;

        }

        newuri = newuri + uri.substring(0, uri.indexOf("?"));
        Log.e("uri new", newuri);
        return newuri;
        //char[] urlcharArray = url.toCharArray();
        /*if (url.length() > 80) {

            String enduri = "?";
            while (!enduri.equals(String.valueOf(url.indexOf(s)))) {
                s = s + 1;
            }

            String uri = url.substring(25, s);
            Log.e("URI", uri);
            return uri;

        }  else { return url;}
*/
    }

    public void onLoadButtonClicked(View view){

        /*
        db.deletevibeplaylist("Mellow Vibe");
        db.deletevibeplaylist("Mellow alt/indie");


        Vibeplaylist newp1 = new Vibeplaylist(1, "Fresh Chill", ">70 fresh", 4849845,60,100,70,100,1,2);
        db.addVibePlaylist(newp1);
        Vibeplaylist newp2 = new Vibeplaylist(1, "Chill Rap", "all chill Rap", 3,60,100,0,100,1,2);
        db.addVibePlaylist(newp2);
        Vibeplaylist newp3 = new Vibeplaylist(1, "Chill EDM", "EDM", 13,60,100,0,100,1,2);
        db.addVibePlaylist(newp3);
        Vibeplaylist newp4 = new Vibeplaylist(1, "Chill Pop", "Pop", 7,60,100,0,100,1,2);
        db.addVibePlaylist(newp4);
        Vibeplaylist newp5 = new Vibeplaylist(1, "Chill Beach", "Beach", 19,60,100,0,100,1,2);
        db.addVibePlaylist(newp5);
        Vibeplaylist newp6 = new Vibeplaylist(1, "Chill Other", "Other genres", 935,60,100,0,100,1,2);
        db.addVibePlaylist(newp6);
        Vibeplaylist newp7 = new Vibeplaylist(1, "Top Chill", ">85 Rating", 4849845,85,100,0,100,1,2);
        db.addVibePlaylist(newp7);
        Vibeplaylist newp8 = new Vibeplaylist(1, "Old Chill Favs", "<50 fresh, >80 rating", 4849845,80,100,0,50,1,2);
        db.addVibePlaylist(newp8);
        Vibeplaylist newp9 = new Vibeplaylist(2, "Party Rap", "rap", 3,60,100,0,100,1,2);
        db.addVibePlaylist(newp9);
        */

    }
    public void onCreateButtonClicked(View view){

       Vibeplaylist newplaylist = new Vibeplaylist();
       /* EditText vibemax = findViewById(R.id.vibemax);
        EditText freshmin = findViewById(R.id.freshmin);
        EditText freshmax = findViewById(R.id.freshmax);
        EditText vibemin = (EditText) findViewById(R.id.vibemin);*/
        int popmin = 0;
        int popmax = 100;
        //genrefrag gfrag = (genrefrag) getFragmentManager().findFragmentById(R.id.fragment4);
       // String mygval = gfrag.Updategenreval(view);
       // List<String> mygvallist = Arrays.asList(mygval.split("\\s*,\\s*"));
        EditText playlistname = (EditText) findViewById(R.id.newplaylistname);
        if (verypopular.isChecked()|| somewhatpopular.isChecked()|| deeppopular.isChecked()){
            if (!deeppopular.isChecked()){
                if (!somewhatpopular.isChecked()) {
                    popmin = 66;
                }else{popmin=33;}
            }
            if (!verypopular.isChecked()){
                if (!somewhatpopular.isChecked()) {
                    popmax = 33;
                }else{popmax=66;}
            }
        }
       // gfrag.isHidden();

       /* int newvibemin = Integer.parseInt(vibemin.getText().toString());
        int newvibemax = Integer.parseInt(vibemax.getText().toString());
        int newfreshmin = Integer.parseInt(freshmin.getText().toString());
        int newfreshmax = Integer.parseInt(freshmax.getText().toString());*/
        String newplaylistname = playlistname.getText().toString();
        //int extrasval = exratenewval();
        int mood = 0;
    ToggleButton amped = findViewById(R.id.amped);
        ToggleButton happy = findViewById(R.id.happy);
        ToggleButton sad = findViewById(R.id.sad);
        if (amped.isChecked()){
            mood = 1;
        }else if (happy.isChecked()){
            mood =2;
        }else if (sad.isChecked()){
            mood = 3;
        }
        Log.e("mood", String.valueOf(mood));
       // newplaylist.set_playlist_mygval(mygval);
       /* if (addedmonth!=0) {
            newplaylist.set_added_month(addedmonth);
            addedmonth = 0;
        }
       /* if (!releaseyear.contains("NA")){
            newplaylist.set_release_year(releaseyear);
            releaseyear = "NA";
        }*/
        //db.addVibePlaylist(newplaylist);
      //  List<Basicsong> reqsongs = db.getslimrequestlistgen1(mygvallist, popmin, popmax, mood, addedmonth, releaseyear);
        List<String> songreqlist = new ArrayList<>();
       /* if (reqsongs.size() != 0) {
            int p = 0;
            while (p < reqsongs.size()) {
                String songnm = String.valueOf(reqsongs.get(p).get_songname());
               // String rating = String.valueOf(reqsongs.get(p).get_ratingval());
                songreqlist.add(songnm);
                p = p + 1;
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    R.layout.spinner_item, songreqlist);
            resultsView.setAdapter(adapter);

            resultsView.setVisibility(View.VISIBLE);
        }*/

    }
    public void onAddUPButtonClicked(View view){
        if(inputratingsgrid.isShown()){
            //Fx.slide_up(this, extraratings);
            inputratingsgrid.setVisibility(View.GONE);
            addbutton.setVisibility(View.VISIBLE);
            songidInput.setVisibility(View.VISIBLE);

        }
        else if (!(inputratingsgrid.isShown())){
            inputratingsgrid.setVisibility(View.VISIBLE);
            addbutton.setVisibility(View.GONE);
            songidInput.setVisibility(View.GONE);
            //Fx.slide_down(this, extraratings);
        }

        //List<SpotifyData> psongs = spot.saveplaylisttracks("37i9dQZF1E9G8oeYG9uL66", "spotify");

        //db.addspotifysongsdata(psongs);


    }

    public void onAddButtonClicked(View view){
        EditText playlistname = (EditText) findViewById(R.id.newplaylistname);
        EditText playlistid = (EditText) findViewById(R.id.playlistid);


            String newplaylistname = playlistname.getText().toString();
            String newplaylistid = playlistid.getText().toString();
           String newplaylisturi = urltouri(newplaylistid);
            //TODO have it check to see uri vs url

       if (!newplaylistname.isEmpty() && !newplaylistid.isEmpty()) {
            db.addUserPlaylist(newplaylisturi, newplaylistname);
        }
            inputratingsgrid.setVisibility(View.VISIBLE);
            addbutton.setVisibility(View.GONE);
            songidInput.setVisibility(View.GONE);
            //Fx.slide_down(this, extraratings);

    }
    public void onDUPButtonClicked(View view){
        EditText playlistname = (EditText) findViewById(R.id.newplaylistname);
            String deleteplaylistname = playlistname.getText().toString();
        if (!deleteplaylistname.isEmpty()) {
            db.deleteuserplaylist(deleteplaylistname);
        }
    }
    public void onDVibeButtonClicked(View view){
        EditText playlistname = (EditText) findViewById(R.id.newplaylistname);
        //if (playlistname!=null ) {
            String deleteplaylistname = playlistname.getText().toString();
            db.deletevibeplaylist(deleteplaylistname);
       // }
    }

    public void onBackedButtonClicked(View view) {
       finish();
    }
   /* public void onPlaylistsButtonClicked(View view) {
        Intent i = new Intent(this, Main2Activity.class);
        startActivity(i);

    }*/
  /*  public int exratenewval(){
        int tempval;
        int fitval = 1;
        int confval = 1;
        if (tempotoggle.isChecked()){
            if(temp1.isChecked()){
                tempval = 1;
            }else {tempval = 11;}
            if (temp2.isChecked()){
                tempval = tempval*3;
            }else{ tempval=tempval*11;}
            if (temp3.isChecked()){ tempval = tempval*3;}
            else if (tempval ==121){
                tempval = 19;
            }
        }else {tempval=19;}
        if (fittoggle.isChecked()){
            if(fit1.isChecked()){
                fitval = 1;
            }else {fitval = 13;}
            if (fit2.isChecked()){
                fitval = fitval*5;
            }else{ fitval=fitval*13;}
            if (fit3.isChecked()){ fitval = fitval*5;}
            else if (fitval ==169){
                fitval = 23;
            }
        }else {fitval=23;}
        if (conftoggle.isChecked()){
            if(conf1.isChecked()){
                confval = 1;
            }else {confval = 17;}
            if (conf2.isChecked()){
                confval = confval*7;
            }else{ confval=confval*17;}
            if (conf3.isChecked()){ confval = confval*7;}
            else if (confval ==289){
                confval = 29;
            }
        }else {confval=29;}
        int extravals = tempval*confval*fitval;
        Log.e("Extravals", String.valueOf(extravals));
        return extravals;
    }*/
    private void loadMonthSpinner() {

        List<String> Months = Arrays.asList("NA","January","February", "March", "April", "May", "June", "July", "August", "September", "October","November", "December");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.spinner_item, Months);

        addedspinner.setAdapter(dataAdapter);
    }

    private void loadYearSpinner() {

        List<String> Years = Arrays.asList("NA","2018","2017", "2016","2015","2014");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.spinner_item, Years);
        releasespinner.setAdapter(dataAdapter);


    }

}
