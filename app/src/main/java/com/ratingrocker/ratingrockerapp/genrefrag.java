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
import java.util.List;
public class genrefrag extends Fragment  {
    private CheckBox rap, pop, rock, house, hiphop, funk, folk, indie, dance, tropical, popular, edm;
    private GridLayout txt_help_gest;
    //private ToggleButton genreexp;
    public int genreval;

    List<String> gtestlist = new ArrayList<>();
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.genre_bar, container, false);
        rap = (CheckBox) view.findViewById(R.id.checkboxtextGenre1);
        rock = (CheckBox) view.findViewById(R.id.checkboxtextGenre2);
        edm = (CheckBox) view.findViewById(R.id.checkboxtextGenre3);
        popular = (CheckBox) view.findViewById(R.id.checkboxtextGenre4);
        pop = (CheckBox) view.findViewById(R.id.checkboxtextGenre5);
        funk = (CheckBox) view.findViewById(R.id.checkboxtextGenre6);
        hiphop = (CheckBox) view.findViewById(R.id.checkboxtextGenre7);
        indie = (CheckBox) view.findViewById(R.id.checkboxtextGenre8);
        house = (CheckBox) view.findViewById(R.id.checkboxtextGenre9);
        dance = (CheckBox) view.findViewById(R.id.checkboxtextGenre10);
        folk = (CheckBox) view.findViewById(R.id.checkboxtextGenre11);
        tropical = (CheckBox) view.findViewById(R.id.checkboxtextGenre12);
        txt_help_gest = (GridLayout) view.findViewById(R.id.help_gest);
        gtestlist.add("rap");
        gtestlist.add("rock");
        gtestlist.add("edm");
        gtestlist.add("popular");
        gtestlist.add("pop");
        gtestlist.add("funk");
        gtestlist.add("hip hop");
        gtestlist.add("indie");
        gtestlist.add("house");
        gtestlist.add("dance");
        gtestlist.add("folk");
        gtestlist.add("tropical");


        return view;
    }
    public void resetChecks(){
        rap.setChecked(false);
        rock.setChecked(false);
        popular.setChecked(false);
        edm.setChecked(false);
        pop.setChecked(false);
        funk.setChecked(false);
        hiphop.setChecked(false);
        indie.setChecked(false);
        house.setChecked(false);
        dance.setChecked(false);
        folk.setChecked(false);
        tropical.setChecked(false);
    }


    public void loadnewgenrevals(String mygval){
        if (mygval.contains(gtestlist.get(0))) {
            rap.setChecked(true);
        }else{rap.setChecked(false);}
        if (mygval.contains(gtestlist.get(1))) {
            rock.setChecked(true);
        }else{rock.setChecked(false);}
        if (mygval.contains(gtestlist.get(2))) {
            edm.setChecked(true);
        }else{edm.setChecked(false);}
        if (mygval.contains(gtestlist.get(3))) {
            popular.setChecked(true);
        }else{popular.setChecked(false);}
        if (mygval.contains(gtestlist.get(4))) {
            pop.setChecked(true);
        }else{pop.setChecked(false);}
        if (mygval.contains(gtestlist.get(5))) {
            funk.setChecked(true);
        }else{funk.setChecked(false);}
        if (mygval.contains(gtestlist.get(6))) {
            hiphop.setChecked(true);
        }else{hiphop.setChecked(false);}
        if (mygval.contains(gtestlist.get(7))) {
            indie.setChecked(true);
        }else{indie.setChecked(false);}
        if (mygval.contains(gtestlist.get(8))) {
            house.setChecked(true);
        }else{house.setChecked(false);}
        if (mygval.contains(gtestlist.get(9))) {
            dance.setChecked(true);
        }else{dance.setChecked(false);}
        if (mygval.contains(gtestlist.get(10))) {
            folk.setChecked(true);
        }else{folk.setChecked(false);}
        if (mygval.contains(gtestlist.get(11))) {
            tropical.setChecked(true);
        }else{tropical.setChecked(false);}


    }
    public String Updategenreval(View view){

        String genrevalstr = "";
        if (rap.isChecked()){
           // Log.e("GENRE", "RAP");
            genrevalstr+= gtestlist.get(0)+",";
        }
        if (rock.isChecked()){
            //Log.e("GENRE", "ROCK");
            genrevalstr+= gtestlist.get(1)+",";
        }
        if (edm.isChecked()){
            //Log.e("GENRE", "EDM");
            genrevalstr+= gtestlist.get(2)+",";
        }
        if (popular.isChecked()){
           // Log.e("GENRE", "POP");
            genrevalstr+= gtestlist.get(3)+",";
        }
        if (pop.isChecked()){
           // Log.e("GENRE", "FUNK");
            genrevalstr+= gtestlist.get(4)+",";
        }

        if (funk.isChecked()){
           // Log.e("GENRE", "soul");
            genrevalstr+= gtestlist.get(5)+",";
        }
        if (hiphop.isChecked()){
           // Log.e("GENRE", "beach");
            genrevalstr+= gtestlist.get(6)+",";
        }
        if (indie.isChecked()){
            //Log.e("GENRE", "other");
            genrevalstr+= gtestlist.get(7)+",";
        }
        if (house.isChecked()){
           // Log.e("GENRE", "other");
            genrevalstr+= gtestlist.get(8)+",";
        }
        if (dance.isChecked()){
            //Log.e("GENRE", "other");
            genrevalstr+= gtestlist.get(9)+",";
        }
        if (folk.isChecked()){
            //Log.e("GENRE", "other");
            genrevalstr+= gtestlist.get(10)+",";
        }
        if (tropical.isChecked()){
            //Log.e("GENRE", "other");
            genrevalstr+= gtestlist.get(11)+",";
        }

        //activityCommander.getGenreval(genreval);
        try{
            genrevalstr = genrevalstr.substring(0, genrevalstr.lastIndexOf(","));
        }catch(Exception e){

        }
        Log.e("G val", genrevalstr);
        return genrevalstr;
    }

}


