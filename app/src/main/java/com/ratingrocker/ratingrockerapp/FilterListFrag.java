package com.ratingrocker.ratingrockerapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import java.util.List;

public class FilterListFrag extends Fragment {

    public ListView resultsView;
    public  List<String> songreqids, songreqlist;
    RecyclerView mRecyclerView;

    private LineAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.filter_list_frag, container, false);
        resultsView = (ListView) view.findViewById(R.id.results_list);
        //songreqids = new ArrayList<>();
       // List<String> songreqlist2 = Arrays.asList("a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c");
       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
              //  android.R.layout.simple_spinner_item, songreqlist);
       // resultsView.setAdapter(adapter);
        resultsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("Item", String.valueOf(songreqids.get(i)));
                ((PlayerActivity)getActivity()).playsong(songreqids.get(i));
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
        List<Button> listbar = Arrays.asList((Button) view.findViewById(R.id.listplaybutton),(Button) view.findViewById(R.id.listshuffbutton), (Button) view.findViewById(R.id.playlistcreatebutton));
        for (int b= 0; b<listbar.size();b++){
            final int o = b;
            listbar.get(b).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (o<2){
                        ((PlayerActivity)getActivity()).setqueue(songreqids, o);
                    }
                }
            });
        }

        return view;
    }
    public void addfiltersongs2(List<List<String>> reqsongs) {

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                    R.layout.spinner_item, reqsongs.get(0));
            resultsView.setAdapter(adapter);
        songreqids = reqsongs.get(1);
    }
    public void addfiltersongs(List<Basicsong> reqsongs) {

        List<String> songreqlist2 = new ArrayList<>();
      List<String> songreqids2 = new ArrayList<>();

        if (reqsongs.size() != 0) {

            for (int p = 0; p < reqsongs.size(); p++) {

                // String rating = String.valueOf(reqsongs.get(p).get_ratingval());
                songreqlist2.add(reqsongs.get(p).get_songname());
                songreqids2.add(reqsongs.get(p).get_song_id());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                    R.layout.spinner_item, songreqlist2);
            resultsView.setAdapter(adapter);

            songreqlist= songreqlist2;
            songreqids = songreqids2;
        }
    }
    private void setupRecycler() {

        // Configurando o gerenciador de layout para ser uma lista.
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);

        // Adiciona o adapter que irá anexar os objetos à lista.
        // Está sendo criado com lista vazia, pois será preenchida posteriormente.
        mAdapter = new LineAdapter(new ArrayList<>(0));
        mRecyclerView.setAdapter(mAdapter);

        // Configurando um dividr entre linhas, para uma melhor visualização.
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }
}




