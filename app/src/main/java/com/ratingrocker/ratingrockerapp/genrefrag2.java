package com.ratingrocker.ratingrockerapp;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class genrefrag2 extends Fragment  {
    private List<CheckBox> genrecheckboxes;
    private List<MultiSelectionSpinner> genrespinners;

    private List<List<String>> genrelist;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.genrebar2, container, false);
        genrecheckboxes = new ArrayList<>();
        genrecheckboxes = Arrays.asList((CheckBox) view.findViewById(R.id.checkboxtextGenre1),(CheckBox) view.findViewById(R.id.checkboxtextGenre2),(CheckBox) view.findViewById(R.id.checkboxtextGenre3),(CheckBox) view.findViewById(R.id.checkboxtextGenre4),(CheckBox) view.findViewById(R.id.checkboxtextGenre5),(CheckBox) view.findViewById(R.id.checkboxtextGenre6));
        genrespinners = new ArrayList<>();
        genrespinners = Arrays.asList((MultiSelectionSpinner) view.findViewById(R.id.genre1spinner),(MultiSelectionSpinner) view.findViewById(R.id.genre2spinner),(MultiSelectionSpinner) view.findViewById(R.id.genre3spinner),(MultiSelectionSpinner) view.findViewById(R.id.genre4spinner),(MultiSelectionSpinner) view.findViewById(R.id.genre5spinner),(MultiSelectionSpinner) view.findViewById(R.id.genre6spinner));
        genrelist = new ArrayList<>();
        List<String>genre1list = new ArrayList<String>();
        genre1list = Arrays.asList(" Rap","-Indie","-Pop", "-Edm", "-Hip-Hop");
        List<String> genre2list = new ArrayList<String>();
        genre2list = Arrays.asList( " Rock","-Indie", "-Alternative", "-Classic", "-Modern");
        List<String> genre3list = new ArrayList<String>();
        genre3list = Arrays.asList(" Pop","-Edm","-Indie","-Funk","-Soul");
        List<String> genre4list = new ArrayList<String>();
        genre4list = Arrays.asList(" EDM","-House","-Tropical","-Dance","-Chillwave");
        List<String>genre5list = new ArrayList<String>();
        genre5list = Arrays.asList(" Indie","-Pop","-Dance","-Downbeat");
        List<String>genre6list = new ArrayList<String>();
        genre6list = Arrays.asList(" Other", "-Reggae", "-Instrumental", "-Country", "-Foreign");
        genrelist = Arrays.asList(genre1list, genre2list, genre3list, genre4list, genre5list, genre6list);
        for (int i = 0;i< genrespinners.size(); i++){
                genrespinners.get(i).setItems(genrelist.get(i));
        }
        return view;
    }
    public void resetChecks() {
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

    }
    public String Updategenreval(View view){

        StringBuilder userselected = new StringBuilder();
        boolean foundone = false;
        for(int oy=0;oy<genrelist.size();oy++) {

            if (genrecheckboxes.get(oy).isChecked()) {
                if (foundone){
                    userselected.append(", ");
                }
                foundone = true;
                userselected.append(genrespinners.get(oy).getSelectedItemsAsString2());
            }
        }
        return userselected.toString();
    }
}


