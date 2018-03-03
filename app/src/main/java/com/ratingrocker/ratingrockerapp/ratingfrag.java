package com.ratingrocker.ratingrockerapp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;




//Todo make seekbars grey when toggle off
public class ratingfrag extends Fragment {

    private SeekBar partyseek, freshseek, soloseek, chillseek, tempval,
            fitval, confval;

    private TextView partyView, chillView, soloView, freshView;
    View view;
    private int currentchill, currentparty, currentmellow, currentfresh;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.rating_fragment, container, false);
        partyseek = (SeekBar) view.findViewById(R.id.partyseekBar);
        partyView = (TextView) view.findViewById(R.id.partyvibevalText);
        chillseek = (SeekBar) view.findViewById(R.id.chillseekBar);
        chillView = (TextView) view.findViewById(R.id.chilvibevalText);
        soloseek = (SeekBar) view.findViewById(R.id.soloseekBar);
        soloView = (TextView) view.findViewById(R.id.solovibevalText);
        freshseek = (SeekBar) view.findViewById(R.id.freshseekBar);
        freshView = (TextView) view.findViewById(R.id.freshvibevalText);
        currentchill = chillseek.getProgress();
        chillseek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {currentchill = progress; chillView.setText(String.valueOf(progress)); }
            public void onStartTrackingTouch(SeekBar seekBar) {}
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        currentparty = partyseek.getProgress();
        partyseek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {currentparty = progress; partyView.setText(String.valueOf(progress)); }
            public void onStartTrackingTouch(SeekBar seekBar) {}
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        currentmellow = soloseek.getProgress();
        soloseek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {currentmellow = progress;
                soloView.setText(String.valueOf(progress)); }
            public void onStartTrackingTouch(SeekBar seekBar) {}
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        currentfresh = freshseek.getProgress();
        freshseek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {currentfresh = progress;
                freshView.setText(String.valueOf(progress)); }
            public void onStartTrackingTouch(SeekBar seekBar) {}
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });


        return view;

    }


    public List<Integer> getRatingvals(View v){


        List<Integer> ratingvalues = new ArrayList<Integer>();
        ratingvalues.add(chillseek.getProgress());
        //ratingvalues.add(chillex);
        ratingvalues.add(partyseek.getProgress());
        ratingvalues.add(soloseek.getProgress());
        ratingvalues.add(freshseek.getProgress());

        return ratingvalues;
    }

    public void resetRatings(int rating1, int rating2, int rating3, int fresh) {

        partyseek.setSecondaryProgress(rating2);
        partyseek.setProgress(0);
        partyView.setText("");
        chillseek.setProgress(0);
        chillseek.setSecondaryProgress(rating1);
        soloseek.setProgress(0);
        soloseek.setSecondaryProgress(rating3);
        freshseek.setProgress(0);
        freshseek.setSecondaryProgress(fresh);
        chillView.setText("");
        soloView.setText("");
        freshView.setText("");
    }
}
