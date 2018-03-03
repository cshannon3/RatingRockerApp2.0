package com.ratingrocker.ratingrockerapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ratingfrag2 extends Fragment {

    private final String TAG = "ratingfrag2";
    private List<Spinner> maingenrespinners;
    private List<MultiSelectionSpinner> genrespinners;
    private List<List<String>> genrelist;
    private List<String> maingenrelist;
    private List<SeekBar> seekbarlist;
    private List<TextView> ratingbarValues;
    View view;
    int u;
    private List<Integer> currentvals;
    private List<List<Integer>> GenreOnOff;
    private List<List<String>> currentgenreval;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.rating_frag2, container, false);
        seekbarlist = Arrays.asList((SeekBar) view.findViewById(R.id.ratingbar1), (SeekBar) view.findViewById(R.id.ratingbar2), (SeekBar) view.findViewById(R.id.ratingbar3), (SeekBar) view.findViewById(R.id.freshbar));
        ratingbarValues = Arrays.asList((TextView) view.findViewById(R.id.ratingval1), (TextView) view.findViewById(R.id.ratingval2), (TextView) view.findViewById(R.id.ratingval3), (TextView) view.findViewById(R.id.freshval));
        maingenrespinners = new ArrayList<>();
        maingenrespinners = Arrays.asList((Spinner) view.findViewById(R.id.maingenre1), (Spinner) view.findViewById(R.id.maingenre2), (Spinner) view.findViewById(R.id.maingenre3));
        genrespinners = new ArrayList<>();
        genrespinners = Arrays.asList((MultiSelectionSpinner) view.findViewById(R.id.genre1spinner), (MultiSelectionSpinner) view.findViewById(R.id.genre2spinner), (MultiSelectionSpinner) view.findViewById(R.id.genre3spinner));
        genrelist = new ArrayList<>();

        //genreval = new ArrayList<>();

        genrelist = Arrays.asList(Arrays.asList(" ", "-indie", "-pop", "-edm", "-hip hop"), Arrays.asList(" ", "-indie", "-alternative", "-classic", "-modern"), Arrays.asList(" ", "-edm", "-indie", "-funk", "-soul"),
                Arrays.asList(" ", "-house", "-tropical", "-dance", "-chillwave"), Arrays.asList(" ", " indie", "-pop", "-dance", "-downbeat"), Arrays.asList(" ", "-reggae", "-instrumental", "-country", "-foreign"));
        maingenrelist = Arrays.asList("Genre", " Rap", " Rock", " Pop", " EDM", " Indie", " Other");

        currentvals = new ArrayList<>();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                R.layout.spinner_item, maingenrelist);

        for (int y = 0; y < maingenrespinners.size(); y++) {
            u = y;
            final int h = y;
            maingenrespinners.get(y).setAdapter(dataAdapter);
            if ((GenreOnOff == null && y > 0) || (GenreOnOff != null && GenreOnOff.get(y).get(0) < 1)) {
                maingenrespinners.get(y).setVisibility(View.GONE);
                genrespinners.get(y).setVisibility(View.GONE);
            }
            if (y > 0 && maingenrespinners.get(y).getSelectedItemPosition() == 0) {
                maingenrespinners.get(y).setVisibility(View.GONE);
                genrespinners.get(y).setVisibility(View.GONE);
            }
            maingenrespinners.get(y).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override

                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (i != 0) {
                        genrespinners.get(h).setItems(genrelist.get(i - 1));
                        genrespinners.get(h).setSelection(0);
                       // GenreOnOff.get(h).set(0, i);

                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }

        Button addgenrebutton = (Button) view.findViewById(R.id.addgenrebutton);
        addgenrebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int z = 0; z < maingenrespinners.size(); z++) {

                    if (!maingenrespinners.get(z).isShown()) {
                        maingenrespinners.get(z).setVisibility(View.VISIBLE);
                        genrespinners.get(z).setVisibility(View.VISIBLE);
                        z = maingenrelist.size();
                    } else if (maingenrespinners.get(z).isShown() && maingenrespinners.get(z).getSelectedItemPosition() == 0) {
                        maingenrespinners.get(z).setVisibility(View.GONE);
                        genrespinners.get(z).setVisibility(View.GONE);
                    }
                }
            }
        });
        for (int p = 0; p < seekbarlist.size(); p++) {
            currentvals.add(seekbarlist.get(p).getProgress());
            final int j = p;
            seekbarlist.get(p).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    currentvals.set(j, progress);
                    ratingbarValues.get(j).setText(String.valueOf(progress));
                }

                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });
        }
        Button savebtn = (Button) view.findViewById(R.id.saveratingbutton);
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Log.e("RatingFrag", Updategenreval(view));
                ((PlayerActivity) getActivity()).saveRatingOnline();
            }
        });
        return view;

    }


    public List<Integer> getRatingvals() {

        return currentvals;
    }

    public void resetRatings(List<Integer> ratings) {

        for (int k = 0; k < seekbarlist.size(); k++) {
            seekbarlist.get(k).setSecondaryProgress(ratings.get(k));
            seekbarlist.get(k).setProgress(0);
            ratingbarValues.get(k).setText("");
        }

    }

    public void onNewSong(String genre) {
    /*for (int k = 0; k < seekbarlist.size(); k++) {
        seekbarlist.get(k).setSecondaryProgress(ratings.get(k));
        seekbarlist.get(k).setProgress(0);
        ratingbarValues.get(k).setText("");
    }*/
        Log.e(TAG, genre);
        GenreOnOff = new ArrayList<>();
        GenreOnOff = Arrays.asList(Arrays.asList(1, 0, 0, 0, 0, 0), Arrays.asList(0, 0, 0, 0, 0, 0), Arrays.asList(0, 0, 0, 0, 0, 0));
        if (genre.length() > 0) {
            // if (genre.indexOf(",")>0){
            // List<String> genres = Arrays.asList(genre.split(","));
            List<String> maingenres = Arrays.asList("", " rap", " rock", " pop", " edm", " indie");
            int j = 0;
            boolean isfound = false;
            boolean selectionfound = false;
            for (int t = 1; t < maingenres.size(); t++) {
                Pattern genrep = Pattern.compile(maingenres.get(t));
                Matcher match = genrep.matcher(genre);
                while (match.find()) {
                    List<String> genreentry = new ArrayList<>();
                    genreentry.add(maingenres.get(t));
                    List<String> selection = new ArrayList<>();
                    selection.add(" ");
                    if (isfound) {
                        maingenrespinners.get(j).setVisibility(View.VISIBLE);
                        genrespinners.get(j).setVisibility(View.VISIBLE);
                    }
                    isfound = true;
                    Log.e(String.valueOf(match.start()), String.valueOf(match.end()));
                    GenreOnOff.get(j).set(0, t);

                    if (genre.substring(match.end() - 1, genre.length()).contains(",")) {
                        Log.e("g-sub0", genre.substring(match.end(), genre.indexOf(",", match.end())));
                        if (genre.substring(match.end() - 1, genre.length()).contains("-") && (genre.indexOf("-", match.end() - 1) < genre.indexOf(",", match.end() - 1))) {
                            //TODO finish this by comparing and selecting the values for the secondary genres
                            Log.e("g-sub1", genre.substring(match.end(), genre.indexOf(",", match.end())));
                            for (int u = 1; u < genrelist.get(j).size(); u++) {
                                if (genre.substring(match.end(), genre.indexOf(",", match.end())).contains(genrelist.get(j).get(u))) {
                                    selection.add(genrelist.get(j).get(u));
                                    genreentry.add(genrelist.get(j).get(u));
                                    selectionfound = true;
                                }
                            }
                        }
                        // String gg = genre.substring(genre.indexOf(match.end() + 1), genre.indexOf(",", genre.indexOf(match.end() + 1)));
                    } else if (genre.substring(match.end() - 1, genre.length()).contains("-")) {
                        Log.e("g-sub2", genre.substring(match.end(), genre.length()));
                        for (int u = 1; u < genrelist.get(j).size(); u++) {
                            if (genre.substring(match.end(), genre.length()).contains(genrelist.get(j).get(u))) {
                                selection.add(genrelist.get(j).get(u));
                                genreentry.add(genrelist.get(j).get(u));
                                selectionfound = true;
                            }
                        }
                    }
                    //String gg = genre.substring(genre.indexOf(match.end()+1), genre.indexOf("," ,genre.indexOf(match.end()+1)));

                    Log.e("genreonoff", GenreOnOff.toString());
                    maingenrespinners.get(j).setSelection(t);
                    genrespinners.get(j).setItems(genrelist.get(j));
                    try {
                        genrespinners.get(j).setSelection(selection);
                    } catch (Exception e) {
                        genrespinners.get(j).setSelection(0);
                    }
                    //currentgenreval.add(genreentry);
                    j += 1;
                }
            }
            // }


        }

    }


    /* public void resetChecks() {
         for (int y = 0; y < genrecheckboxes.size(); y++) {
             genrecheckboxes.get(y).setChecked(false);
         }

     }
     public void loadnewgenrevals(String mygval){
         for(int oy=0;oy<genrelist.size();oy++) {

             if (mygval.contains(genrelist.get(oy).get(0))) {
                 genrecheckboxes.get(oy).setChecked(true);
                 int[] selected = {0};
                 int i = 1;
                 for (int h = 1; h < genrelist.get(0).size(); h++) {
                     if (mygval.contains(genrelist.get(0).get(h))) {
                         selected[i] = h;
                         i += 1;
                     }
                 }
                 genrespinners.get(oy).setSelection(selected);
             } else {
                 genrecheckboxes.get(oy).setChecked(false);
             }

         }

     }*/
    public String Updategenreval() {

        StringBuilder userselected = new StringBuilder();
        boolean foundone = false;
        List<String> maingenres = Arrays.asList("", " rap", " rock", " pop", " edm", " indie");
        int p = 0;
        while (maingenrespinners.get(p).isShown()) {
            if (maingenrespinners.get(p).getSelectedItemPosition() > 0) {
                if (foundone) {
                    userselected.append(",");
                }
                foundone = true;
                userselected.append(maingenres.get(maingenrespinners.get(p).getSelectedItemPosition()));

                try {
                    userselected.append(genrespinners.get(p).getSelectedItemsAsString2());
                } catch (Exception e) {

                }
            }
            p++;
        }
        Log.e(TAG, userselected.toString());
        return userselected.toString();
    }

    public List<List<String>> GenrevalList() {

        List<List<String>> genres = new ArrayList<>();
        boolean foundone = false;
        List<List<String>> Genrelist = new ArrayList<>();
        List<String> maingenres = Arrays.asList("", " rap", " rock", " pop", " edm", " indie");
        int p = 0;
        while (maingenrespinners.get(p).isShown()) {
            if (maingenrespinners.get(p).getSelectedItemPosition() > 0) {
                List<String> gen = new ArrayList<>();
                gen.add(maingenres.get(maingenrespinners.get(p).getSelectedItemPosition()));

                try {
                    List<String> k = genrespinners.get(p).getSelectedStrings2();
                    for (int l = 0; l < k.size(); l++) {
                        gen.add(k.get(l));
                    }

                } catch (Exception e) {
                }
                Genrelist.add(gen);
            }
            p+=1;
        }
            return Genrelist;
        }
}

