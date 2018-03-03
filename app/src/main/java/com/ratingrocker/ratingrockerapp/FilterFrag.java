/*package com.ratingrocker.ratingrockerapp;


import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class FilterFrag extends Fragment {

    private Spinner dropdown, addedspinner, releasespinner;
    //private Button createbutton, backbutton, playlistsbutton;
    private GridLayout spotifyfilters, extraratings, inputratingsgrid;
    private ToggleButton expratings;
    MyDBHandler db;
    private int addedmonth, vibenum;
    private String  releaseyear;
    // private ToggleButton conf1, conf2, conf3, temp1, temp2, temp3, fit1, fit2, fit3, tempotoggle, fittoggle, conftoggle;;
    private EditText songidInput;
    private ToggleButton happy, sad, amped, verypopular, somewhatpopular, deeppopular;
    private Button addbutton, addUPbutton;
    //LoadPlaylistdata spot;
    private ListView resultsView;
    public List<String> songreqids;



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.filter_frag, container, false);

        db = new MyDBHandler(getActivity());
        vibenum = 1;
        addedmonth = 0;

        releasespinner = view.findViewById(R.id.releasespinner);
        addedspinner = view.findViewById(R.id.addedspinner);
        happy = view.findViewById(R.id.happy);
        sad = view.findViewById(R.id.sad);
        amped = view.findViewById(R.id.amped);
        verypopular = view.findViewById(R.id.verypopular);
        somewhatpopular = view.findViewById(R.id.somewhatpopular);
        deeppopular = view.findViewById(R.id.deeppopular);
        loadMonthSpinner();
        loadYearSpinner();
        String[] exsongarray = {"song1", "song2", "song3", "song4", "song5"};
        spotifyfilters = (GridLayout)  view.findViewById(R.id.spotifyfilters);
        resultsView = (ListView) view.findViewById(R.id.results_list);

       resultsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("Item", String.valueOf(i));
                //View rowview;
                //resultsView.setBackgroundColor(Color.BLUE);
                //rowview = resultsView.getChildAt(i);
               // rowview.setBackgroundColor(Color.GREEN);
                ((PlayerActivity)getActivity()).playsong(songreqids.get(i));
                if (i==0){
                    resultsView.setVisibility(GONE);
                    spotifyfilters.setVisibility(View.VISIBLE);
                }

            }
        });
        resultsView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("Item", String.valueOf(i));

                ((PlayerActivity)getActivity()).queuesong(songreqids.get(i));
            return true;
            }
        });

        resultsView.setVisibility(GONE);
        spotifyfilters.setVisibility(View.VISIBLE);
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


        return view;
    }


    public String urltouri(String url) {
        int s = 0;
        String uri = url.substring(25, url.length());

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

    }



    public void filterresultsFunc(View view, String mygval){
        Vibeplaylist newplaylist = new Vibeplaylist();

        int popmin = 0;
        int popmax = 100;

        List<String> mygvallist = Arrays.asList(mygval.split("\\s*,\\s*"));
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

        int mood = 0;

        if (amped.isChecked()){
            mood = 1;
        }else if (happy.isChecked()){
            mood =2;
        }else if (sad.isChecked()){
            mood = 3;
        }
        Log.e("mood", String.valueOf(mood));
        newplaylist.set_playlist_mygval(mygval);
        songreqids = new ArrayList<>();
        List<Basicsong> reqsongs = db.getslimrequestlistgen1(mygvallist, popmin, popmax, mood, addedmonth, releaseyear);
        List<String> songreqlist = new ArrayList<>();

        if (reqsongs.size() != 0) {

            for(int p=0;p < reqsongs.size(); p++) {

                // String rating = String.valueOf(reqsongs.get(p).get_ratingval());
                songreqlist.add(reqsongs.get(p).get_songname());
                songreqids.add(reqsongs.get(p).get_song_id());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                    R.layout.spinner_item, songreqlist);
            resultsView.setAdapter(adapter);
            spotifyfilters.setVisibility(View.GONE);
            resultsView.setVisibility(View.VISIBLE);
        }


    }
public List<String> getFilterids(){
        return songreqids;
}


    private void loadMonthSpinner() {

        List<String> Months = Arrays.asList("NA","January","February", "March", "April", "May", "June", "July", "August", "September", "October","November", "December");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                R.layout.spinner_item, Months);

        addedspinner.setAdapter(dataAdapter);
    }

    private void loadYearSpinner() {

        List<String> Years = Arrays.asList("NA","2018","2017", "2016","2015","2014");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                R.layout.spinner_item, Years);
        releasespinner.setAdapter(dataAdapter);


    }

}*/

