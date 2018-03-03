package com.ratingrocker.ratingrockerapp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.ToggleButton;



public class extrasfrag extends Fragment {

    private ToggleButton temptoggle, fittoggle, conftoggle;
    private SeekBar tempval,
            fitval, confval;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.extras_fragment, container, false);
        fittoggle = (ToggleButton) view.findViewById(R.id.fittoggle);
        temptoggle = (ToggleButton) view.findViewById(R.id.tempotoggle);
        conftoggle = (ToggleButton) view.findViewById(R.id.conftoggle);
        fitval = (SeekBar) view.findViewById(R.id.fit_seekBar);
        tempval = (SeekBar) view.findViewById(R.id.tempo_seekBar);
        confval = (SeekBar) view.findViewById(R.id.conf_seekBar);

        return view;
    }
    public int getextravals(View view){

        int extraval = 1;


        //ToggleButton Favoritetoggle = (ToggleButton) findViewById(R.id.favorite_toggle);
        //ToggleButton Secondlooktoggle = (ToggleButton) findViewById(R.id.second_look_toggle);
        // Extra ratings are Tempo, Fit and Confidence
        // They are saved using prime numbers so 3 is fit, 5 is tempo, 7 is confidence
        //To get seek value, multiply each prime by itself for the position eg. a high confidence
        // would correspond to 7*7, medium to 7, low to 1
        //To distinguish low from unchecked, unchecked corresponds to a higher prime number
        // Fit to 23, tempo to 29, conf to 31
        if (fittoggle.isChecked()){

            int f = fitval.getProgress();
            int i = 0 ;
            while (i < f){
                extraval = extraval*3;
                i = i+1;
            }
        }else{ extraval = extraval*23;}
        if (temptoggle.isChecked()){

            int t = tempval.getProgress();
            int i = 0 ;
            while (i< t){
                extraval = extraval*5;
                i = i+1;
            }
        }else { extraval = extraval*29;}
        if (conftoggle.isChecked()){

            int c = confval.getProgress();
            int i = 0 ;
            while (i< c){
                extraval = extraval*7;
                i = i+1;
            }
        }else {extraval = extraval*31;}


        return extraval;
    }
}
