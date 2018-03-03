/*package com.ratingrocker.ratingrockerapp;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarFinalValueListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import static android.view.View.GONE;



public class FilterFragment extends Fragment {


    private Spinner dropdown, addedspinner, releasespinner;
    //private Button createbutton, backbutton, playlistsbutton;
    private GridLayout spotifyfilters, extraratings, inputratingsgrid;
    private ToggleButton expratings;
    // MyDBHandler db;
    private int addedmonth, vibenum;
    private String  releaseyear;
    // private ToggleButton conf1, conf2, conf3, temp1, temp2, temp3, fit1, fit2, fit3, tempotoggle, fittoggle, conftoggle;;
    private EditText songidInput;
    private ToggleButton happy, sad, amped, verypopular, somewhatpopular, deeppopular;
    private Button addbutton, addUPbutton, searchbtn;
    //LoadPlaylistdata spot;
    // private ListView resultsView;
    public List<String> songreqids;
    private List<Spinner> maingenrespinners2;
    private List<MultiSelectionSpinner> genrespinners2;
    private List<List<String>> genrelist2;
    private List<String> maingenrelist2;
    private List<List<Integer>> GenreOnOff2;
    private List<Integer> vals;
    private final String TAG = "FilterFragment";
    MyDBHandler db;



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.filter_frag2, container, false);

        db = new MyDBHandler(getActivity());
        vibenum = 1;
        addedmonth = 0;
        maingenrelist2 = new ArrayList<>();
        genrespinners2 = new ArrayList<>();
        GenreOnOff2 = new ArrayList<>();
        genrelist2 = new ArrayList<>();
        maingenrespinners2 = Arrays.asList((Spinner) view.findViewById(R.id.maingenre12), (Spinner) view.findViewById(R.id.maingenre22), (Spinner) view.findViewById(R.id.maingenre32));
        genrespinners2 = Arrays.asList((MultiSelectionSpinner) view.findViewById(R.id.genre1spinner2), (MultiSelectionSpinner) view.findViewById(R.id.genre2spinner2), (MultiSelectionSpinner) view.findViewById(R.id.genre3spinner2));
        vals = Arrays.asList(0, 100);
        GenreOnOff2 = Arrays.asList(Arrays.asList(1, 0, 0, 0, 0, 0), Arrays.asList(0, 0, 0, 0, 0, 0), Arrays.asList(0, 0, 0, 0, 0, 0));
        genrelist2 = Arrays.asList(Arrays.asList(" ", "-indie", "-pop", "-edm", "-hip hop"), Arrays.asList(" ", "-indie", "-alternative", "-classic", "-modern"), Arrays.asList(" ", "-edm", "-indie", "-funk", "-soul"),
                Arrays.asList(" ", "-house", "-tropical", "-dance", "-chillwave"), Arrays.asList(" ", " indie", "-pop", "-dance", "-downbeat"), Arrays.asList(" ", "-reggae", "-instrumental", "-country", "-foreign"));
        maingenrelist2 = Arrays.asList("Genre"," Rap", " Rock", " Pop", " EDM", " Indie", " Other");
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
        //resultsView = (ListView) view.findViewById(R.id.results_list);


        spotifyfilters.setVisibility(View.VISIBLE);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                R.layout.spinner_item, maingenrelist2);
        for (int y = 0; y < maingenrespinners2.size(); y++) {
            final int u = y;
            maingenrespinners2.get(y).setAdapter(dataAdapter);
            if(GenreOnOff2.get(y).get(0)<1){
                maingenrespinners2.get(y).setVisibility(View.GONE);
                genrespinners2.get(y).setVisibility(View.GONE);
                //genrespinners.get(y).setSelection(0);
            }
            maingenrespinners2.get(y).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (i != 0) {
                        genrespinners2.get(u).setItems(genrelist2.get(i - 1));
                        genrespinners2.get(u).setSelection(0);
                    }}
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
        }
        Button addgenrebutton2 = (Button)  view.findViewById(R.id.addgenrebutton2);
        addgenrebutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int f = 0; f < maingenrespinners2.size(); f++) {
                    // final int j = f;
                    if (GenreOnOff2.get(f).get(0) < 1) {
                        GenreOnOff2.get(f).set(0, 1);
                        maingenrespinners2.get(f).setVisibility(View.VISIBLE);
                        genrespinners2.get(f).setVisibility(View.VISIBLE);
                        //  genrespinners.get(f).setSelection(0);
                        f = maingenrelist2.size();
                    }
                }
            }
        });
        searchbtn = (Button)  view.findViewById(R.id.searchbtn);
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               final  List<Basicsong> results = filterresultsFunc(view);
                final Handler handler2 = new Handler();
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        ((PlayerActivity)getActivity()).sendFliterList(results);
                        chosenGenres();
                    }
                }, 1000);

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
        CrystalRangeSeekbar rangeSeekbar = (CrystalRangeSeekbar) view.findViewById(R.id.rangeSeekbar3);
        final TextView tvMin = (TextView) view.findViewById(R.id.popmin);
       final TextView tvMax = (TextView) view.findViewById(R.id.popmax);
        rangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                tvMin.setText(String.valueOf(minValue));
                tvMax.setText(String.valueOf(maxValue));

            }
        });

        rangeSeekbar.setOnRangeSeekbarFinalValueListener(new OnRangeSeekbarFinalValueListener() {
            @Override
            public void finalValue(Number minValue, Number maxValue) {
                Log.d("CRS=>", String.valueOf(minValue) + " : " + String.valueOf(maxValue));
                vals.set(0, minValue.intValue());
                vals.set(1, maxValue.intValue());
            }
        });
        return view;
    }

        public List<Basicsong> filterresultsFunc(View view){
            Vibeplaylist newplaylist = new Vibeplaylist();
//            String mygval = getgenreval(view);
           // int popmin = 0;
           // int popmax = 100;
          //  List<String> mygvallist = Arrays.asList(mygval.split("\\s*,\\s*"));
            /*if (verypopular.isChecked()|| somewhatpopular.isChecked()|| deeppopular.isChecked()){
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
           // newplaylist.set_playlist_mygval(mygval);
            songreqids = new ArrayList<>();
            List<Basicsong> reqsongs = db.getslimrequestlistgen2(vals.get(0), vals.get(1), addedmonth, releaseyear);

            return reqsongs;

        }
        public List<String> getFilterids(){
            return songreqids;
        }

    private void loadMonthSpinner() {

        List<String> Months = Arrays.asList("NA","January","February", "March", "April", "May", "June", "July", "August", "September", "October","November", "December");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, Months);

        addedspinner.setAdapter(dataAdapter);
    }


    private void loadYearSpinner() {

        List<String> Years = Arrays.asList("NA","2018","2017", "2016","2015","2014");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, Years);
        releasespinner.setAdapter(dataAdapter);


    }
    public String getgenreval(View view){

        StringBuilder userselected = new StringBuilder();
        boolean foundone = false;
        for(int oy=0;oy<maingenrespinners2.size();oy++) {

            // if (GenreOnOff.get(oy).get(0)>1) {
            //if (foundone){
            // userselected.append(", ");
            //  }
            // foundone = true;
            if (maingenrespinners2.get(oy).isShown()){
                userselected.append(maingenrelist2.get(GenreOnOff2.get(oy).get(0)));
                userselected.append(genrespinners2.get(oy).getSelectedItemsAsString2());
            }
        }
        return userselected.toString();
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

    /*public List<Basicsong> filterresultsFunc2(View view){
        List<Integer> startlist = Arrays.asList(0,160,0,100,0,100,0,100,0,100);

    }
    public String chosenGenres(){

        StringBuilder userselected = new StringBuilder();
        TreeMap<String, List<String>> genres = new TreeMap<>();
        boolean foundone = false;
        List<String> maingenres = Arrays.asList("", " rap", " rock", " pop", " edm", " indie");
        int k = 0;
       while (maingenrespinners2.get(k).isShown()){

            if(maingenrespinners2.get(k).getSelectedItemPosition()>0){
                if (foundone) {
                    userselected.append(",");
                }
                foundone=true;

                userselected.append(maingenres.get(maingenrespinners2.get(k).getSelectedItemPosition()));

                try{
                    userselected.append(genrespinners2.get(k).getSelectedStrings());
                    genres.put(maingenres.get(maingenrespinners2.get(k).getSelectedItemPosition()), genrespinners2.get(k).getSelectedStrings2());

                }catch(Exception e){
                    genres.put(maingenres.get(maingenrespinners2.get(k).getSelectedItemPosition()), Arrays.asList("NA"));
                }
            } k+=1;

            // foundone = true;
           /* if (maingenrespinners.get(oy).isShown()){
                userselected.append(maingenrelist.get(GenreOnOff.get(oy).get(0)));
                try {
                    userselected.append(genrespinners.get(oy).getSelectedItemsAsString2());
                }catch(Exception e){

                }
           }
        }
        Log.e(TAG, userselected.toString());
        Log.e(TAG, genres.toString());
        return userselected.toString();
    }
}
*/
