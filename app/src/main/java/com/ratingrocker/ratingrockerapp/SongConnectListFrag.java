package com.ratingrocker.ratingrockerapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SongConnectListFrag extends Fragment {
    private ListView sconnectView;
    private List<String> sconnectids, sconnectlist2;
    private List<String> choosenlist;
    MyDBHandler db;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.songconnect_list_frag, container, false);
        sconnectView = (ListView) view.findViewById(R.id.connect_list2);

        List<String> songconnectlist22 = Arrays.asList("1", "2", "3", "4","1", "2", "3", "4","1", "2", "3", "4","1", "2", "3", "4");

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_multiple_choice, songconnectlist22);

        sconnectView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        sconnectView.setAdapter(adapter);
        //TODO
       // Button scSave = (Button) view.findViewById(R.id.scsave);
       // sconnectlist2 = new ArrayList<>();
       // sconnectids = new ArrayList<>();
        choosenlist = new ArrayList<>();
        db = new MyDBHandler(getActivity());
        sconnectView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("Item", String.valueOf(i));
                //Log.e("Item name", sconnectlist2.get(i));
                //if (!offline){
                // mPlayer.playUri(mOperationCallback, songconnectids.get(i), 0, 0);
                //updateView();

            }
        });
        sconnectView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                ((PlayerActivity)getActivity()).playsong(sconnectids.get(i));
                return false;
            }


        });
        //TODO
        /*
        scSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Chosen", String.valueOf(choosenlist));
            }
        });*/
       /* sconnectView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                choosenlist.add(sconnectlist2.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/

        return view;
    }
   /* public void addsongconnectlist(FullSongData currentsong){

//        choosenlist.clear();
        sconnectids = new ArrayList<>();
        sconnectlist2 = new ArrayList<>();
        List<String> sclist = db.getsongconnectlist1(currentsong);
        Collections.sort(sclist, Collections.<String>reverseOrder());
        if (sclist.size() != 0) {
            for (int j = 0; j < sclist.size(); j++) {
                String id = sclist.get(j).substring(sclist.get(j).indexOf("/") + 1, sclist.get(j).length());
                sconnectids.add(id);
                sconnectlist2.add(sclist.get(j).substring(0, sclist.get(j).indexOf("/")));
                 Log.e(sconnectlist2.get(j), sconnectids.get(j));
            }

        }
        List<String> songconnectlist22 = sconnectlist2;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_multiple_choice,  songconnectlist22);
        sconnectView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        sconnectView.setAdapter(adapter);
    }*/
    public void addsongconnectlist2(List<String> scl){

//        choosenlist.clear();
        sconnectids = new ArrayList<>();
        sconnectlist2 = new ArrayList<>();

        Collections.sort(scl, Collections.<String>reverseOrder());
        if (scl.size() != 0) {
            for (int j = 0; j < scl.size(); j++) {
                String id = scl.get(j).substring(scl.get(j).indexOf("/") + 1, scl.get(j).length());
                sconnectids.add(id);
                sconnectlist2.add(scl.get(j).substring(0, scl.get(j).indexOf("/")));
               // Log.e(sconnectlist2.get(j), sconnectids.get(j));
            }

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_multiple_choice,  sconnectlist2);
        sconnectView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        sconnectView.setAdapter(adapter);
    }

}

