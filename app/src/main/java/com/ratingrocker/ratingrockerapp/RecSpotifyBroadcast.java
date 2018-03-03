package com.ratingrocker.ratingrockerapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;



public class RecSpotifyBroadcast extends BroadcastReceiver {
    public RecSpotifyBroadcast() {

    }

    String track;
    String artist;
    String album;
    String meta;


    @Override
    public void onReceive(Context context, Intent intent) {
        track = intent.getStringExtra("track");
        album = intent.getStringExtra("album");
        artist = intent.getStringExtra("artist");
        meta = track + " - " + artist;

        Log.e("artist", artist);
        Log.e("Track", meta);
        Intent i = new Intent("SpotifyMetachanged");
        i.putExtra("name", track);
        i.putExtra("artist", artist);
        context.sendBroadcast(i);
        // Toast.makeText(context, "Broadcast Recieved", Toast.LENGTH_LONG);
    }

}
