package com.ratingrocker.ratingrockerapp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Parcel;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.ratingrocker.ratingrockerapp.DataObjects.FullSongdata;
import com.ratingrocker.ratingrockerapp.DataObjects.SCsongdata;
import com.ratingrocker.ratingrockerapp.DataObjects.spotifysong;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.spotify.sdk.android.player.Config;
import com.spotify.sdk.android.player.ConnectionStateCallback;
import com.spotify.sdk.android.player.Connectivity;
import com.spotify.sdk.android.player.Error;
import com.spotify.sdk.android.player.Metadata;
import com.spotify.sdk.android.player.PlaybackState;
import com.spotify.sdk.android.player.Player;
import com.spotify.sdk.android.player.PlayerEvent;
import com.spotify.sdk.android.player.Spotify;
import com.spotify.sdk.android.player.SpotifyPlayer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Album;
import kaaes.spotify.webapi.android.models.AlbumSimple;
import kaaes.spotify.webapi.android.models.Albums;
import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.ArtistSimple;
import kaaes.spotify.webapi.android.models.Artists;
import kaaes.spotify.webapi.android.models.AudioFeaturesTrack;
import kaaes.spotify.webapi.android.models.AudioFeaturesTracks;
import kaaes.spotify.webapi.android.models.Pager;
import kaaes.spotify.webapi.android.models.Playlist;
import kaaes.spotify.webapi.android.models.PlaylistSimple;
import kaaes.spotify.webapi.android.models.PlaylistTrack;
import kaaes.spotify.webapi.android.models.SnapshotId;
import kaaes.spotify.webapi.android.models.Track;
import kaaes.spotify.webapi.android.models.TrackToRemove;
import kaaes.spotify.webapi.android.models.TracksToRemove;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.Query;
import retrofit.http.QueryMap;

import static android.content.ContentValues.TAG;
import static android.view.View.GONE;
import static com.spotify.sdk.android.authentication.LoginActivity.REQUEST_CODE;

public class PlayerActivity extends AppCompatActivity implements
        SpotifyPlayer.NotificationCallback, ConnectionStateCallback {
    private static final String CLIENT_ID = "d570917696114c588cc8e1b302d801ed";
    // TODO: Replace with your redirect URI
    private static final String REDIRECT_URI = "rating-rocker-login1://callback";
    final String clientSecret = "1787e07da26d42529da9768a79ac6dd5";
    private static final String TAG = "Mymessages";
    SpotifyApi api;
    SpotifyService spotify;
    MyDBHandler db;
    private Player mPlayer;
    private Metadata mMetadata;
    private BroadcastReceiver mNetworkStateReceiver;
    private PlaybackState mCurrentPlaybackState;


    private TextView mMetadataText, upgreenText, downredText;
    private int upcount, downcount, queuenum, score;
    private List<String> songqueue,  songconnectlist2, songconnectids, filterids;
    public FullSongData currentsong;
    public FullSongdata currentsong2;
    boolean offline, songqueuetf, skipped;
    private Spinner dropdown2;
    private ToggleButton playpause, Secondlooktoggle, upvotebutton, downvotebutton, Favoritetoggle, playlisttoggle;
    private String recArtist, recTrackName;



    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    private ratingfrag2 rfrag2;
    private SongConnectListFrag sclistfrag;
    private SCListFrag scListFrag;
    private FilterFragment3 filtFrag3;
    private FilterListFrag filterlistfrag;
    private FilterListFragment flistnew;
    private List<Basicsong> lastfilterlist2;
    private List<List<String>> lastfilterlist, playlists;
    private static final int REQUEST_CODE = 1337;

    private final Player.OperationCallback mOperationCallback = new Player.OperationCallback() {
        @Override
        public void onSuccess() {
            Log.i(TAG, "OK!");
        }

        @Override
        public void onError(Error error) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        registerReceiver(spotifyreciever, new IntentFilter("SpotifyMetachanged"));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Favoritetoggle = (ToggleButton) findViewById(R.id.favorite_toggle);
        Secondlooktoggle = (ToggleButton) findViewById(R.id.second_look_toggle);

        upvotebutton = findViewById(R.id.up_button);
        downvotebutton = findViewById(R.id.down_button);
        upvotebutton = findViewById(R.id.up_button);
        upgreenText = findViewById(R.id.upcountgreen);
        downredText = findViewById(R.id.downcountred);
        playpause = findViewById(R.id.pause_button);
        mMetadataText = (TextView) findViewById(R.id.metadatas);
        //mplaylist = (TextView) findViewById(R.id.metadata);


        db = new MyDBHandler(this);
        api = new SpotifyApi();

        //spot.clientAuth();
        offline = true;
        //Automatically sing in atm
        Authentication();
        songqueuetf = false;
        skipped = false;

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
       rfrag2 = (ratingfrag2) mSectionsPageAdapter.getItem(0);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 2) {
                    //if(lastfilterlist!=null) {
                    mViewPager.setCurrentItem(2);
                    //filterlistfrag = (FilterListFrag) mSectionsPageAdapter.getItem(2);
                    flistnew = (FilterListFragment) mSectionsPageAdapter.getItem(2);

                    // filterlistfrag.addfiltersongs(ffilterlist);
                    Log.e("works", "2 works");
                   /* try {
                        flistnew.addfiltersongs(lastfilterlist2);
                    }catch(Exception e){}*/
                    //  }
                }
                if (tab.getPosition() == 3 && currentsong2 != null) {
                    mViewPager.setCurrentItem(3);
                }
            if (tab.getPosition() == 0){
                    mViewPager.setCurrentItem(0);
            }  else if (tab.getPosition() == 1) {
                mViewPager.setCurrentItem(1);
            }
/*
                    scListFrag = (SCListFrag) mSectionsPageAdapter.getItem(3);
                 // TODO
                List<List<String>> gval =
                     scListFrag.getList( );*/

            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
       // ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, android.R.id.text1, exsongarray);
/*

*/

        //List<String> queuelist = new ArrayList<>();
        playlisttoggle = (ToggleButton) findViewById(R.id.playlistswitch_toggle);
        dropdown2 = findViewById(R.id.loadplaylistspinner);
        dropdown2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long arg3) {
                Log.e("playlist  nameeee",String.valueOf(dropdown2.getSelectedItem()));
                if (playlisttoggle.isChecked()) {
                  //TODO ADD PLAYLISTS BACK IN
                    /* Vibeplaylist vibeplaylist = db.getoneVibePlaylist(String.valueOf(dropdown2.getSelectedItem()));
                    if (vibeplaylist != null) {
                        Log.e("playlist  name", vibeplaylist.get_playlistname());
                       // if (songqueue != null) {
                       //     songqueue.clear();
                       // }
                        //TODO add this back for actual app
                    }*/
                    if (mPlayer != null && songqueue != null) {
                        mPlayer.playUri(mOperationCallback, songqueue.get(0), 0, 0);
                        playpause.setChecked(true);
                        String currentsong = songqueue.get(0);
                        songqueue.add(currentsong);
                        songqueue.remove(0);
                        songqueuetf = true;
                    }
                } else {
                    if (songqueuetf) {
                        songqueue.clear();
                        songqueuetf= false;
                    }
                    String playlist = playlists.get(1).get(dropdown2.getSelectedItemPosition());
                    Log.i("Item clicked", playlist);
                    if (mPlayer != null && playlist != null) {
                        mPlayer.playUri(mOperationCallback, playlist, 0, 0);
                    }
                    //addplaylistsongstodb2(playlist);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void setupViewPager(ViewPager viewpager){
       // SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        mSectionsPageAdapter.addFragment(new ratingfrag2(), "Ratings");
        //mSectionsPageAdapter.addFragment(new FilterFragment(), "Filter");
        mSectionsPageAdapter.addFragment(new FilterFragment2(), "Filter");
       // mSectionsPageAdapter.addFragment(new FilterListFrag(), "F-List");
        mSectionsPageAdapter.addFragment(new FilterListFragment(), "F-List");
       // mSectionsPageAdapter.addFragment(new SongConnectListFrag(), "SC-List");
        mSectionsPageAdapter.addFragment(new SCListFrag(), "SCList");
        viewpager.setAdapter(mSectionsPageAdapter);

    }

    BroadcastReceiver spotifyreciever = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle b = intent.getExtras();
            recTrackName = b.getString("name");
            recArtist = b.getString("artist");
            String meta = recTrackName + " - " + recArtist;
            if (offline) {
                updateView();
                mMetadataText.setText(meta);
                currentsong2 = db.getCurrentSongbyName(recTrackName);
            }
            //TODO figure out how to save song offline w/ no song uri // or how to update song uri through backend search
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE) {
            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);
            if (response.getType() == AuthenticationResponse.Type.TOKEN) {
                Config playerConfig = new Config(this, response.getAccessToken(), CLIENT_ID);
                api.setAccessToken(response.getAccessToken());
                spotify = api.getService();
                Spotify.getPlayer(playerConfig, this, new SpotifyPlayer.InitializationObserver() {
                    @Override
                    public void onInitialized(SpotifyPlayer spotifyPlayer) {
                        mPlayer = spotifyPlayer;
                        mPlayer.addConnectionStateCallback(PlayerActivity.this);
                        mPlayer.addNotificationCallback(PlayerActivity.this);
                        offline = false;
                        //TODO add back in
                         loadSpinnerData();
                    }
                    @Override
                    public void onError(Throwable throwable) {
                        Log.e("MainActivity", "Could not initialize player: " + throwable.getMessage());
                    }
                });
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Set up the broadcast receiver for network events. Note that we also unregister
        // this receiver again in onPause().
        mNetworkStateReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (mPlayer != null) {
                    Connectivity connectivity = getNetworkConnectivity(getBaseContext());
                    mPlayer.setConnectivityStatus(mOperationCallback, connectivity);
                }
            }
        };
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mNetworkStateReceiver, filter);

        if (mPlayer != null) {
            mPlayer.addNotificationCallback(PlayerActivity.this);
            mPlayer.addConnectionStateCallback(PlayerActivity.this);
        }
    }

    private Connectivity getNetworkConnectivity(Context context) {
        ConnectivityManager connectivityManager;
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            return Connectivity.fromNetworkType(activeNetwork.getType());
        } else {
            return Connectivity.OFFLINE;
        }
    }

    /*public void onSpotifyButtonClicked(View view) {
        if (SpotifyToggle.isChecked()) {
            Authentication();
        }

    }*/

    public void Authentication() {
        AuthenticationRequest.Builder builder = new AuthenticationRequest.Builder(CLIENT_ID,
                AuthenticationResponse.Type.TOKEN,
                REDIRECT_URI);
        builder.setScopes(new String[]{"user-read-private", "streaming", "playlist-read-private", "playlist-modify-public"});
        AuthenticationRequest request = builder.build();
        //List<String> items = new ArrayList<>();
        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request);
    }

    private void loadSpinnerData() {

        playlists = db.getListVibePlaylists();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.spinner_item, playlists.get(0));

        dropdown2.setAdapter(dataAdapter);

    }

   /* private void loadSpinnerData2() {

        List<String> lables = db.getListVibePlaylists();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.spinner_item, lables);
        dropdown2.setAdapter(dataAdapter);
        if (songqueue != null) {
            songqueue.clear();
        }

    }*/


    private void updateView() {

        upgreenText.setTextColor(Color.parseColor("#d3d3d3"));
        downredText.setTextColor(Color.parseColor("#d3d3d3"));
        Secondlooktoggle.setChecked(false);
        Favoritetoggle.setChecked(false);
//        LogoToggle.setChecked(false);

        if (!offline) {
            mMetadata = mPlayer.getMetadata();
            if (songqueue!=null) {
                mPlayer.queue(mOperationCallback, songqueue.get(1));
                String tcurrentsong = songqueue.get(0);
                songqueue.add(tcurrentsong);
                songqueue.remove(0);
               // songconnectView.setVisibility(GONE);
            }
            final ImageView coverArtView = (ImageView) findViewById(R.id.cover_art);
            if (mMetadata != null && mMetadata.currentTrack != null) {
              //  final String durationStr = String.format(" (%dms)", mMetadata.currentTrack.durationMs);
               // mMetadataText.setText(mMetadata.contextName + "\n" + mMetadata.currentTrack.name + " - " + mMetadata.currentTrack.artistName + durationStr);
                //mplaylist.setText(mMetadata.contextName);
                mMetadataText.setText(mMetadata.currentTrack.name + " - "
                       + mMetadata.currentTrack.artistName);
                Picasso.with(this)
                        .load(mMetadata.currentTrack.albumCoverWebUrl)
                        .into(coverArtView);
            } else {
                mMetadataText.setText("<nothing is playing>");
                // coverArtView.setBackground();
            }
          //  currentsong = db.getCurrentSong(mMetadata.currentTrack.uri);
        }
       /* else if (recTrackName!=null) {
            currentsong = db.getCurrentSongbyName(recTrackName);
        }*/
        mViewPager.setCurrentItem(0);
        rfrag2 = (ratingfrag2) mSectionsPageAdapter.getItem(0);
        try {
            rfrag2.resetRatings(Arrays.asList(currentsong2.getRatingval1(), currentsong2.getRatingval2(),currentsong2.getRatingval3(), currentsong2.getFreshval()));

       /* if (currentsong.get_mygval() != null) {
            rfrag2.onNewSong(currentsong.get_mygval());
            Log.e("v",currentsong.get_mygval());
        } else*/

            if (currentsong2.getMygenres() != null) {
            rfrag2.onNewSong(currentsong2.getMygenres());
           // Log.e("v","b");
            }}catch(Exception e){}

           //TODO add back ratings update

                if (currentsong2 != null) {
                try{
                    if (currentsong2.getFavsec() % 3 == 0 && currentsong2.getFavsec() != 0) {
                        Favoritetoggle.setChecked(true);
                    }
                    if (currentsong2.getFavsec() % 5 == 0 && currentsong2.getFavsec()!= 0) {
                        Secondlooktoggle.setChecked(true);
                    }
                }catch(Exception e){}}
            }


//TODO drop to next todo
//
/*
            if (currentsong != null) {
                Log.e("v","qqq");
               // List<Basicsong> sclist = db.getsongconnectlist3(currentsong);
               // scListFrag.getList(sclist);
//                Log.e("song name", currentsong.get_songname());
                //Log.e("ratingchill", String.valueOf(currentsong.get_ratingval1())+ "-" +String.valueOf(currentsong.get_ratingval2())+"-"+String.valueOf(currentsong.get_ratingval3()));
                upcount = currentsong.get_upcount();
                downcount = currentsong.get_downcount();
                if (currentsong.get_favandsec() % 3 == 0 && currentsong.get_favandsec() != 0) {
                    Favoritetoggle.setChecked(true);
                }
                if (currentsong.get_favandsec() % 5 == 0 && currentsong.get_favandsec() != 0) {
                    Secondlooktoggle.setChecked(true);
                }
                if (currentsong.get_favandsec() % 7 == 0 && !offline) {
                    int newval = (currentsong.get_favandsec()) / 7;
                    currentsong.set_favandsec(newval);
                }


            } else {
                upcount = 0;
                downcount = 0;
              //  gfrag.resetChecks();
            }
            upgreenText.setText(String.valueOf(upcount));
            downredText.setText(String.valueOf(downcount));
            //TODO add checks for genre
            }*/

    public void saveMisc(int oldfavval, int oldscore, String songid){

        int favval = 1;

        if (Secondlooktoggle.isChecked()) {
            favval = favval * 5;
            if (oldfavval % 5 != 0 ||oldfavval == 0) {
                addsongtoplaylist("spotify:user:1221015148:playlist:6VdL1lwG6NLzzLSkIGJqOr", songid);
            }
        }
        if (Favoritetoggle.isChecked()) {
            favval = favval * 3;
            if (oldfavval % 3 != 0 || oldfavval == 0) {
                addsongtoplaylist("spotify:user:1221015148:playlist:7h3ZmXnGBNu7lZJ8Z4yhVy", songid);
            }
        }
        //final  Handler handler = new Handler();
       // final Runnable r = new Runnable() {
        //    public void run() {

        scListFrag = (SCListFrag) mSectionsPageAdapter.getItem(3);
        // TODO
        String scsavelist = scListFrag.getSCChecked();

            if (skipped) {
                score += 1;
                skipped = false;
        }
        Log.e(TAG, String.valueOf(score));

        //TODO include songconnect

        db.addMISCdata(songid, score, favval, scsavelist);


          //  }
      //  };

       // handler.postDelayed(r, 10000);

    }


    public void saveRatingOnline() {

        mViewPager.setCurrentItem(0);
        rfrag2 = (ratingfrag2) mSectionsPageAdapter.getItem(0);
            if (currentsong2 != null) {
                mMetadata = mPlayer.getMetadata();
                String songname = mMetadata.currentTrack.name + " - " + mMetadata.currentTrack.artistName;
                if(!mMetadata.currentTrack.uri.contentEquals(currentsong2.getSong_id())){
                    currentsong2 = db.getCurrentSong2(mMetadata.currentTrack.uri);
                }
                List<Integer> ratingvals = rfrag2.getRatingvals();
                String gval = rfrag2.Updategenreval();
                RatingData newsong = new RatingData();
                newsong.set_songid(currentsong2.getSong_id());
                newsong.set_songname(currentsong2.getSong_name());
                newsong.set_ratingval1(ratingvals.get(0));
                newsong.set_ratingval1(ratingvals.get(1));
                newsong.set_ratingval1(ratingvals.get(2));
                newsong.set_freshvalue(ratingvals.get(3));
                newsong.set_mygval(gval);
                    db.addnewrating(newsong);
            } else {
                Log.e("currentsong", "bad");
            }
        List<List<String>> gval = rfrag2.GenrevalList();
            currentsong2.getSpotify_data();
                mViewPager.setCurrentItem(3);
             //TODO
                List<SCsongdata> scl = db.getsongconnectlist1(gval, currentsong2.getSpotify_data() );
                for (int h = 0; h<scl.size(); h++){
                    Log.e(scl.get(h).getSong_name(),String.valueOf(scl.get(h).getSC_score()));
                }
                scListFrag = (SCListFrag) mSectionsPageAdapter.getItem(3);
                scListFrag.getList(scl);

    }


    public void getuserplaylists(){
        //TODO get users user id

        spotify.getMyPlaylists(new Callback<Pager<PlaylistSimple>>(){
            @Override
            public void success(Pager<PlaylistSimple> pl, Response response) {
                //Log.d("Album success", artist.name);
                List<PlaylistSimple> p = pl.items;
                for (int cplay=0;cplay<p.size(); cplay++) {
                    //String playlistid = p.get(cplay).id;
                    final String playlisturi = p.get(cplay).uri;
                    String playlistname = p.get(cplay).name;
                    Log.e("playlistname", playlistname);
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do something after 100ms
                            addplaylistsongstodb2(playlisturi);
                        }
                    }, 10000);



                    // Log.d("Album success genres", theg.get(0));
                }

            }
            @Override
            public void failure(RetrofitError error) {
                Log.d("playlistfailure", error.toString());
            }
        });



    }
    public void adduserplayliststodb() {


        spotify.getMyPlaylists(new Callback<Pager<PlaylistSimple>>() {
            @Override
            public void success(Pager<PlaylistSimple> pl, Response response) {
                //Log.d("Album success", artist.name);
                List<PlaylistSimple> p = pl.items;
                for (int cplay = 0; cplay < p.size(); cplay++) {
                    //String playlistid = p.get(cplay).id;
                    String playlisturi = p.get(cplay).uri;
                    String playlistname = p.get(cplay).name;
                    Log.e("playlistname", playlistname);
                    db.addUserPlaylist(playlisturi, playlistname);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("playlistfailure", error.toString());
            }
        });
    }
    public List<String> playlisturisplit(String uri) {
        int s = 0;
        String newuri = "spotify:";
        String nuri = uri.substring(13, uri.length());
        String uripart1 = nuri.substring(0, nuri.indexOf(":"));
        String n2uri = nuri.substring((nuri.indexOf(":")+1), nuri.length());
        String uripart2 = n2uri.substring((n2uri.indexOf(":")+1), n2uri.length());

        List<String> urisplit = new ArrayList<String>();
        urisplit.add(uripart1);
        urisplit.add(uripart2);
       // Log.e("uri new1", uripart1);
       // Log.e("uri new2", uripart2);
        return urisplit;
    }
    public void addplaylistsongstodb2(String playlisturi){

        List<String> uri = playlisturisplit(playlisturi);
        spotify.getPlaylist( uri.get(0), uri.get(1), new Callback<Playlist>(){

            @Override
            public void success(Playlist playlist, Response response) {
                // Log.d("Playlist success", playlist.name);
                db.addnewUserPlaylist(playlist.uri, playlist.name);
                final List<spotifysong> psongs = new ArrayList<>();

                Pager<PlaylistTrack> tracks = playlist.tracks;
                final List<PlaylistTrack> t = tracks.items;
                String trackids = "";
                final StringBuilder artistids = new StringBuilder();
                String albumids = "";
                final List<String> releasedate = new ArrayList<>();
                final List<String> artistlists20 = new ArrayList<>();
                int listlen = 0;
                for (int len = 0; len < t.size(); len++) {
                    if ((len + 1) == t.size()) {
                        trackids += t.get(len).track.id;
                        artistids.append(t.get(len).track.artists.get(0).id);
                        albumids += t.get(len).track.album.id;
                        artistlists20.add(artistids.toString());
                        Log.e("Artistids", artistids.toString());
                    } else if (listlen == 20) {
                        artistlists20.add(artistids.toString().substring(0, artistids.lastIndexOf(",")));
                        Log.e("Artistids", artistids.toString());
                        artistids.delete(0, artistids.length());
                        trackids += t.get(len).track.id + ",";
                        albumids += t.get(len).track.album.id + ",";
                        listlen = 0;
                    } else {
                        trackids += t.get(len).track.id + ",";
                        artistids.append(t.get(len).track.artists.get(0).id + ",");
                        albumids += t.get(len).track.album.id + ",";
                        listlen += 1;
                    }
                    spotify.getAlbum(t.get(len).track.album.id, new Callback<Album>(){
                        @Override
                        public void success(Album album, Response response) {
                           // Log.d("Album success", album.release_date);
                           // albumlist.addAll(albums.albums);
                            releasedate.add(album.release_date);
                           // Log.d("Albums worked", "albums");
                        }
                        @Override
                        public void failure(RetrofitError error) {
                            Log.d("Album failure", error.toString());
                        }
                    });
                }
               // Log.e("TrackIds", trackids);
               // Log.e("ArtistIds", artistids.toString());
               // Log.e("AlbumIds", albumids);

                final ArrayList<List<String>> genresl = new ArrayList<>();
                    final List<Artist> alist = new ArrayList<>();
                    final List<AudioFeaturesTrack> audiolist= new ArrayList<>();
                final List<Album> albumlist = new ArrayList<>();
                final Handler handler2 = new Handler();
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            for (int k = 0; k < artistlists20.size(); k++) {
                                spotify.getArtists(artistlists20.get(k), new Callback<Artists>() {
                                    @Override
                                    public void success(Artists artists, Response response) {
                                        //Log.d("Album success", artist.name);
                                        alist.addAll(artists.artists);
                                        for (int ar = 0; ar < artists.artists.size(); ar++) {
                                            genresl.add(artists.artists.get(ar).genres);

                                        }
                                        //  Log.d("Artists worked", "artists");
                                    }
                                    @Override
                                    public void failure(RetrofitError error) {
                                        Log.d("Artist failure", error.toString());
                                    }
                                });
                            }
                        }catch(Exception e) {
                            spotify.getArtists(artistids.toString(), new Callback<Artists>() {
                                @Override
                                public void success(Artists artists, Response response) {
                                    //Log.d("Album success", artist.name);
                                    alist.addAll(artists.artists);
                                    for (int ar = 0; ar < artists.artists.size(); ar++) {
                                        genresl.add(artists.artists.get(ar).genres);

                                    }
                                    //  Log.d("Artists worked", "artists");
                                }

                                @Override
                                public void failure(RetrofitError error) {
                                    Log.d("Artist failure", error.toString());
                                }
                            });
                        }
                    }
                }, 1000);
                    spotify.getTracksAudioFeatures(trackids, new Callback<AudioFeaturesTracks>(){
                        @Override
                        public void success(AudioFeaturesTracks audiofeat, Response response) {
                            audiolist.addAll(audiofeat.audio_features);
                           // Log.d("Audio worked", "audio");

                        }
                        @Override
                        public void failure(RetrofitError error) {
                            Log.d("audiofailure", error.toString());
                        }
                    });

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Do something after 100ms

                for (int len = 0; len< t.size(); len++) {
                    spotifysong song = new spotifysong();
                    song.setSong_id(t.get(len).track.uri);
                    song.setSong_name(t.get(len).track.name);
                    song.setPopularity(t.get(len).track.popularity);
                    song.setArtist(t.get(len).track.artists.get(0).name);
                    String genrestr = "";
                    // Log.d("Album success genres", theg.get(0));

                    try {
                        for (int gr = 0; gr < alist.get(len).genres.size(); gr++) {
                            if ((gr + 1) == alist.get(len).genres.size()) {
                                genrestr += alist.get(len).genres.get(gr);
                                //Log.d("Album genre", genrestr);
                            } else {
                                genrestr += alist.get(len).genres.get(gr) + ", ";
                                //Log.d("Album genre", genrestr);
                            }
                        }
                        Log.e("Genres", t.get(len).track.name + genrestr);
                    }catch (Exception e){
                        Log.e("Genre Prob", String.valueOf(len));
                    }
                    song.setSpotify_genre(genrestr);
                   /* try {
                        //TODO figureout how to add release dates
                      //  Log.e("Release date", releasedate.get(len));
                        song.set_releasedate(releasedate.get(len));
                    }catch(Exception e){
                        Log.e("No release date", "error");
                    }
                    song.set_added_date(t.get(len).added_at);
                    */
                    StringBuilder audio = new StringBuilder();

                  //  Log.e("added date", t.get(len).added_at);
                    String tempo = String.valueOf(audiolist.get(len).tempo);
                    audio.append(t.get(len).added_at.substring(0, t.get(len).added_at.indexOf("T")));
                    audio.append(".");
                    audio.append(releasedate.get(len));
                    audio.append(".");
                    audio.append(tempo.substring(0, tempo.indexOf(".")));
                   // String value = String.valueOf(audiolist.get(len).toString());
                    //audio.append(value.substring(1, 4));
                    List<String> feats = Arrays.asList(String.valueOf(audiolist.get(len).danceability),  String.valueOf(audiolist.get(len).energy),
                                                        String.valueOf(audiolist.get(len).valence),String.valueOf(audiolist.get(len).instrumentalness),
                                                        String.valueOf(audiolist.get(len).liveness),String.valueOf(audiolist.get(len).loudness/10),
                                                         String.valueOf(audiolist.get(len).speechiness));
                    for (int y = 0; y<feats.size(); y++){
                        //audio.append(feats.get(y).substring(1,4));
                        try {
                            audio.append(feats.get(y).substring(feats.get(y).indexOf("."), (feats.get(y).indexOf(".") + 3)));
                        }catch(Exception e ){
                            audio.append(feats.get(y).substring(feats.get(y).indexOf("."), (feats.get(y).indexOf(".", feats.get(y).indexOf(".")))));
                        }
                    }

                    Log.e("dance/energy", audio.toString());

                    Log.e("tempo", String.valueOf(audiolist.get(len).tempo));
                    song.setSpotify_data(audio.toString());
                    db.addspotifysongdata(song);
                    psongs.add(song);
                }
                //TODO connect it back to db
                       // db.addspotifysongsdata(psongs);
                    }
                }, 10000);


                }


            @Override
            public void failure(RetrofitError error) {
                Log.d("Playlist failure", error.toString());
            }
        });

    }
    ///////////////////

        /* PLAYER FUNCTIONS*/


    //////////////////
    public void addsongtoplaylist(String playlisturi, String songuri){
        final List<String> playlisturiparts = playlisturisplit(playlisturi);

       final Map<String, Object> uris = new HashMap<>();
        final Map<String, Object> body = new HashMap<>();
        //uris.put("uris:", currentsong.get_songid());

        uris.put("uris", songuri);
        body.put("uris", songuri);
        // spotify:track:4sy3B9TPNMIze8oFrDJnUN
        try {
            api.getService();
            Log.e("Add to playlist", "Good");
        }catch (Exception e){
            Log.e("Add to playlist", "Error");
            Authentication();
        }
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
        spotify.addTracksToPlaylist(playlisturiparts.get(0), playlisturiparts.get(1), uris, body, new Callback<Pager<PlaylistTrack>>(){
            @Override
            public void success(Pager<PlaylistTrack> pl , Response response) {

            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("playlistfailure", error.toString());
            }
        });
    }
    }, 10000);}
    public void createaplaylist(String playlistname, final String userid, String discription, String songuris, boolean publicprivate){


        final Map<String, Object> uris = new HashMap<>();
        Map<String, Object> body = new HashMap<>();
        //uris.put("uris:", currentsong.get_songid());
       uris.put("uris", songuris);
        body.put("name", playlistname);
        body.put("public", publicprivate);
        body.put("description", discription);
        // spotify:track:4sy3B9TPNMIze8oFrDJnUN

        // if (currentsong.get_favandsec()%5!=0){
        spotify.createPlaylist(userid, body, new Callback<Playlist>() {
            @Override
            public void success(Playlist playlist, Response response) {
                spotify.addTracksToPlaylist(userid, playlist.id, uris, uris, new Callback<Pager<PlaylistTrack>>() {
                    @Override
                    public void success(Pager<PlaylistTrack> pl, Response response) {

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("playlistfailure", error.toString());
                    }
                });
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("playlistfailure", error.toString());
            }
        });
    }


    ////////////////////////

     /*Commands for playback */




    //////////////////////

    //////////////////////////////////////////////////////////////////
    /*||||\\   ||    || |||||||| |||||||||  //|||\\    |||\     ||
      ||   \\  ||    ||    ||       ||     //     \\   || \\    ||
      ||   //  ||    ||    ||       ||    ||       ||  ||  \\   ||
      |||||/   ||    ||    ||       ||    ||       ||  ||   \\  ||
      ||   \\  ||    ||    ||       ||    ||       ||  ||    \\ ||
      ||   //  \\    //    ||       ||     \\     //   ||     \\||
      ||||//    \\||//     ||       ||      \\|||//    ||      \||
    //////////////////////////////////////////////////////////////
   /* public void onSpotifyButtonClicked(View view){
        if (SpotifyToggle.isChecked()){
            Authentication();
        }
    }
   public void onLoafButtonClicked(View view){

       db.databaseToString2();
      // addplaylistsongstodb2("spotify:user:austen2442:playlist:1VGYF8Kgk5ZC27WtSNx7JD");
      // addplaylistsongstodb2("spotify:user:spotify:playlist:37i9dQZF1CyNdxfbe6GW0h");
       //addplaylistsongstodb2("spotify:user:1221015148:playlist:4uSYwNY9LnxX5ea6L6tLVZ");
   }

    public void onNewButtonClicked(View view){
        Intent i = new Intent(this, CreateNewPlaylist.class);
        startActivity(i);
    }*/
    public void onUpButtonClicked(View view) {
        //db.getsongconnectlist(1, 80, 80, 3, 49);
        //Integer.parseInt(upgreenText.toString());
        rfrag2.Updategenreval();
        if (upvotebutton.isChecked()) {
            upgreenText.setTextColor(Color.parseColor("#FF0EAB02"));
            score += 1;
            upgreenText.setText(String.valueOf(score));
        } else {
            upgreenText.setTextColor(Color.parseColor("#d3d3d3"));
            score = score - 1;
            upgreenText.setText(String.valueOf(score));
        }


    }

    public void onDownButtonClicked(View view) {
        String hypotheticalSpotdata = "100.24.56.66.12.56";
        List<String> a =  Arrays.asList(hypotheticalSpotdata.split("\\."));
        Log.e("Main", a.get(1));
        addplaylistsongstodb2("spotify:user:spotify:playlist:37i9dQZEVXcPKy0U2e7eN5");

        if (downvotebutton.isChecked()) {
            downredText.setTextColor(Color.parseColor("#FFE53B33"));

            score -= 1;
            downredText.setText(String.valueOf(score));
        } else {
            downredText.setTextColor(Color.parseColor("#d3d3d3"));
            score += 1;
            downredText.setText(String.valueOf(score));
        }
        //deletesongfromplaylist("spotify:user:1221015148:playlist:7h3ZmXnGBNu7lZJ8Z4yhVy", "spotify:track:4sy3B9TPNMIze8oFrDJnUN");
        // getuserplaylists();
        //db.getListPlaylists();
        //db.databaseToString2();
    }
    public void onPauseButtonClicked(View view) {
        if (!offline){
            playpause = (ToggleButton) findViewById(R.id.pause_button);
            if (playpause.isChecked()){
                mPlayer.resume(mOperationCallback);

            }else {
                mPlayer.pause(mOperationCallback);
            }
           /* if (mCurrentPlaybackState != null && mCurrentPlaybackState.isPlaying) {
                mPlayer.pause(mOperationCallback);
            }
            }*/
        }}

    public void onSkipToPreviousButtonClicked(View view) {
        if (!offline) {
            mPlayer.skipToPrevious(mOperationCallback);
        }
    }

    public void onSkipToNextButtonClicked(View view) {
        if (!offline) {
            skipped= true;
            mPlayer.skipToNext(mOperationCallback);
        }
    }
    public void onToggleShuffleButtonClicked(View view) {
        if (!offline) {
            mPlayer.setShuffle(mOperationCallback, true);
            try{
                Collections.shuffle(songqueue);
            }catch(Exception e){

            }
        }
    }

    public void onToggleRepeatButtonClicked(View view) {
        mPlayer.setRepeat(mOperationCallback, !mCurrentPlaybackState.isRepeating);
    }
    /*public void onExtrasClicked (View view){
        ToggleButton extrasbtn = view.findViewById(R.id.plusExtras);
        if (songconnectView.isShown()){
            songconnectView.setVisibility(View.GONE);
            RatingFragView.setVisibility(View.VISIBLE);

        }
        else if (extrasbtn.isChecked()){
            RatingFragView.setVisibility(View.GONE);
            ExtrasFragView.setVisibility(View.VISIBLE);
        }else {
             RatingFragView.setVisibility(View.VISIBLE);
              ExtrasFragView.setVisibility(View.GONE);
        }
    }
*/




    public void playsong(String songuri){
        if (!offline){
            /*try {
                int oldscore = currentsong2.getScore();
            }catch(Exception e){ score=0;}
            try {
            int oldfavval = currentsong2.getFavsec();
            }catch(Exception e){ }*/
            currentsong2 = db.getCurrentSong2(songuri);
            //String songid = currentsong2.getSong_id();
            mPlayer.playUri(mOperationCallback, songuri, 0, 0);
            mMetadata = mPlayer.getMetadata();
         //  currentsong = db.getCurrentSong(songuri);

           // saveMisc(oldfavval, oldscore, songid);
            //updateView();
        }
    }
    public void queuesong(String songuri){
        if (!offline){
            //songqueue.add(songuri);
            mPlayer.queue(mOperationCallback, songuri);
        }
    }
    public void deletesong(String songuri){
            db.deletesongfromdb(songuri);
        }

    public void setqueue(List<String> queueUris, int shuffonoff) {
        if (!offline) {

            songqueue = queueUris;
            if (shuffonoff == 1) {
                Collections.shuffle(songqueue);
            }
            mPlayer.playUri(mOperationCallback, songqueue.get(0), 0, 0);
        }
    }
    public void sendFliterList(List<Basicsong> ffilterlist){
        lastfilterlist = new ArrayList<>();
        lastfilterlist2 = ffilterlist;
        List<String> songreqlist2 = new ArrayList<>();
        List<String> songreqids2 = new ArrayList<>();
        if (ffilterlist.size() != 0) {
            for (int p = 0; p < ffilterlist.size(); p++) {
                // String rating = String.valueOf(reqsongs.get(p).get_ratingval());
                songreqlist2.add(ffilterlist.get(p).get_songname());
                songreqids2.add(ffilterlist.get(p).get_song_id());
            }
            lastfilterlist = Arrays.asList(songreqlist2, songreqids2);
            filterlistfrag = (FilterListFrag) mSectionsPageAdapter.getItem(2);
            // filterlistfrag.addfiltersongs(ffilterlist);
            Log.e("works", "2 works");
            filterlistfrag.addfiltersongs2(lastfilterlist);
        }
       // mViewPager.setCurrentItem(2);
        //filterlistfrag = (FilterListFrag) mSectionsPageAdapter.getItem(2);
      // filterlistfrag.addfiltersongs(ffilterlist);
    }
    public void getFilterParams(List<List<String>> filtgenres, List<List<Integer>>maininputs, List<List<Integer>>hitlist){

        lastfilterlist2 = db.getfilterlist(filtgenres, maininputs, hitlist);
        flistnew = (FilterListFragment) mSectionsPageAdapter.getItem(2);
       // filterlistfrag = (FilterListFrag) mSectionsPageAdapter.getItem(2);
        // filterlistfrag.addfiltersongs(ffilterlist);
        Log.e("works", "2 works");

        flistnew.addfiltersongs(lastfilterlist2);
       // filterlistfrag.addfiltersongs(filtlist);
    }
    public List<Basicsong> sendfilt(){
        return lastfilterlist2;
    }
    @Override
    protected void onDestroy() {
        // VERY IMPORTANT! This must always be called or else you will leak resources
        Spotify.destroyPlayer(this);
        super.onDestroy();
    }

    @Override
    public void onPlaybackEvent(PlayerEvent playerEvent) {
        //Log.d("MainActivity", "Playback event received: " + playerEvent.name());
        switch (playerEvent) {

            case UNKNOWN:
                break;
            case kSpPlaybackNotifyPlay:
                playpause.setChecked(true);
                break;
            case kSpPlaybackNotifyPause:
                playpause.setChecked(false);
                break;
            case kSpPlaybackNotifyTrackChanged:
                mMetadata = mPlayer.getMetadata();
                currentsong2 = db.getCurrentSong2(mMetadata.currentTrack.uri);
                updateView();
                break;
            case kSpPlaybackNotifyNext:
                skipped = true;
                break;
            case kSpPlaybackNotifyPrev:
                break;
            case kSpPlaybackNotifyShuffleOn:
                break;
            case kSpPlaybackNotifyShuffleOff:
                break;
            case kSpPlaybackNotifyRepeatOn:
                break;
            case kSpPlaybackNotifyRepeatOff:
                break;
            case kSpPlaybackNotifyBecameActive:
                break;
            case kSpPlaybackNotifyBecameInactive:
                break;
            case kSpPlaybackNotifyLostPermission:
                break;
            case kSpPlaybackEventAudioFlush:
                break;
            case kSpPlaybackNotifyAudioDeliveryDone:
                break;
            case kSpPlaybackNotifyContextChanged:
                break;
            case kSpPlaybackNotifyTrackDelivered:
                break;
            case kSpPlaybackNotifyMetadataChanged:
                break;
        }}




    @Override
    public void onPlaybackError(Error error) {
        Log.d("MainActivity", "Playback error received: " + error.name());
        switch (error) {
            // Handle error type as necessary
            default:
                break;
        }
    }

    @Override
    public void onLoggedIn() {
        Log.d("MainActivity", "User logged in");
       // mPlayer.playUri(null, "spotify:user:akahendy:playlist:0C7xiAjvGXJg0bc8umC6JA", 0, 0);
       // mPlayer.pause(mOperationCallback);
         //mPlayer.playUri(null, "spotify:track:2TpxZ7JUBn3uw46aR7qd6V", 0, 0);
    }

    @Override
    public void onLoginFailed(Error error) {
        Log.d("MainActivity", "Login failed");
    }

    @Override
    public void onLoggedOut() {
        Log.d("MainActivity", "User logged out");
    }
    @Override
    public void onTemporaryError() {
        Log.d("MainActivity", "Temporary error occurred");
    }

    @Override
    public void onConnectionMessage(String message) {
        Log.d("MainActivity", "Received connection message: " + message);
    }


    public void testspotifysetup(){
        String savedspotifypreferences = "030211300243440500";

                // popularity/Energy/Acousticness/dancability/Tempo/Valance
        String ogsongpreference = "070611100243140500";
        String compsongpreference ="060711600243940500";

    }
}
 /*Button savebutton = (Button) findViewById(R.id.savebutton);
        if (LogoToggle.isChecked()){
                songconnectView.setVisibility(View.GONE);
                RatingFragView.setVisibility(View.GONE);
                ExtrasFragView.setVisibility(View.GONE);
                FilterFragView.setVisibility(View.VISIBLE);
                ListPlaybar.setVisibility(View.VISIBLE);
               // GenreFragView.setVisibility(View.GONE);

            savebutton.setText(R.string.searchbutton);
           // addplaylistsongstodb2("spotify:user:austen2442:playlist:1VGYF8Kgk5ZC27WtSNx7JD");
            //adduserplayliststodb();
            //loadSpinnerData();
            //createaplaylist("Testplay", "1221015148", "test play", "spotify:track:1aRtduEHwvk2AMR87ji9Tg,spotify:track:6oNZ0WxqptE7V0qpjtyJ4a,spotify:track:6Yvs6s1lnqLGAlEedPPUdY", true);
        }else{
            songconnectView.setVisibility(View.GONE);
            RatingFragView.setVisibility(View.VISIBLE);
            ExtrasFragView.setVisibility(View.GONE);
            FilterFragView.setVisibility(View.GONE);
            ListPlaybar.setVisibility(View.GONE);
           // GenreFragView.setVisibility(View.VISIBLE);
            savebutton.setText(R.string.savebutton);
        }
    }*/
   /*
    public void onSaveButtonClicked(View view) {
       // if (!LogoToggle.isChecked()) {
            String songname = "";
            String songidd = "";
           // List<String> songconnect2uncut = db.getsongconnectlist1(currentsong);
           // sclistfrag = new SongConnectListFrag();
           // mViewPager.setCurrentItem(3);
       Log.e("a",String.valueOf(mSectionsPageAdapter.getCount()));

        mViewPager.setCurrentItem(3);
        sclistfrag = (SongConnectListFrag) mSectionsPageAdapter.getItem(3);
           sclistfrag.addsongconnectlist(currentsong);
            int favval = 1;

           // RatingFragView.setVisibility(View.GONE);
           // ExtrasFragView.setVisibility(View.GONE);
           // ListPlaybar.setVisibility(View.VISIBLE);
            if (currentsong != null) {
                Log.e("currentsong", "good");
                //mMetadata = mPlayer.getMetadata();
                //songname = mMetadata.currentTrack.name + " - " + mMetadata.currentTrack.artistName;
                // songidd = mMetadata.currentTrack.uri;
                /*
                RatingData newsong = new RatingData();
                newsong.set_songid(currentsong.get_songid());
                newsong.set_songname(currentsong.get_songname());
             //   genrefrag gfrag = (genrefrag) getFragmentManager().findFragmentById(R.id.genrefrag);
              //  String mygval = gfrag.Updategenreval(view);
               // newsong.set_mygval(mygval);

               // ratingfrag rfrag = (ratingfrag) getFragmentManager().findFragmentById(R.id.ratingfrag);
               // extrasfrag extrasfrag = (extrasfrag) getFragmentManager().findFragmentById(R.id.extrasfrag);
                List<Integer> ratingvals = new ArrayList<Integer>();
                //ratingvals = rfrag.getRatingvals(view);
               // newsong.set_freshvalue(ratingvals.get(3));
               // int extravals = extrasfrag.getextravals(view);

               // newsong.set_exratevals(extravals);

                //List<Basicsong> songconnectlist = new ArrayList<Basicsong>();
/
                //if (ratingvals.get(0) > 60) {
                newsong.set_ratingval1(ratingvals.get(0));
                Log.e("Ratingvals1", String.valueOf(ratingvals.get(0)));
                //} else {
                //    newsong.set_ratingval1(0);
                // }
                //if (ratingvals.get(1) > 60) {
                newsong.set_ratingval2(ratingvals.get(1));
                Log.e("Ratingvals2", String.valueOf(ratingvals.get(1)));
                // } else {
                //    newsong.set_ratingval2(0);
                // }
                // if (ratingvals.get(2) > 60) {
                newsong.set_ratingval3(ratingvals.get(2));
                Log.e("Ratingvals3", String.valueOf(ratingvals.get(2)));
                // } else {
                //   newsong.set_ratingval3(0);
                // }



                songconnectids = new ArrayList<>();
                songconnectlist2 = new ArrayList<>();
                Collections.sort(songconnect2uncut, Collections.<String>reverseOrder());
                if (songconnect2uncut.size() != 0) {
                    for (int j = 0; j < songconnect2uncut.size(); j++) {
                        String id = songconnect2uncut.get(j).substring(songconnect2uncut.get(j).indexOf("/") + 1, songconnect2uncut.get(j).length());
                        songconnectids.add(id);
                        songconnectlist2.add(songconnect2uncut.get(j).substring(0, songconnect2uncut.get(j).indexOf("/")));
                       // Log.e(songconnectlist2.get(j), songconnectids.get(j));
                    }
                }*/
                /*
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_multiple_choice, songconnectlist2);
                songconnectView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                songconnectView.setAdapter(adapter);
                songconnectView.setVisibility(View.VISIBLE);

                if (Secondlooktoggle.isChecked()) {
                    favval = favval * 5;
                    if (currentsong.get_favandsec() % 5 != 0 || currentsong.get_favandsec() == 0) {
                        addsongtoplaylist("spotify:user:1221015148:playlist:6VdL1lwG6NLzzLSkIGJqOr", currentsong.get_songid());
                    }
                }
                if (Favoritetoggle.isChecked()) {
                    favval = favval * 3;
                    if (currentsong.get_favandsec() % 3 != 0 || currentsong.get_favandsec() == 0) {
                        addsongtoplaylist("spotify:user:1221015148:playlist:7h3ZmXnGBNu7lZJ8Z4yhVy", currentsong.get_songid());
                    }
                Map<String, Object> uris = new HashMap<>();
                Map<String, Object> body = new HashMap<>();
                //uris.put("uris:", currentsong.get_songid());
                uris.put("uris", currentsong.get_songid());
                body.put("uris", "spotify:track:4sy3B9TPNMIze8oFrDJnUN");
               // spotify:track:4sy3B9TPNMIze8oFrDJnUN

               // if (currentsong.get_favandsec()%5!=0){
                spotify.addTracksToPlaylist("1221015148", "7h3ZmXnGBNu7lZJ8Z4yhVy", uris, body, new Callback<Pager<PlaylistTrack>>(){
                   @Override
                    public void success(Pager<PlaylistTrack> pl , Response response) {

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("playlistfailure", error.toString());
                    }
                });
              //  }
                }

             //   newsong.set_favandsec(favval);
               // db.addnewrating(newsong);
            } else {
                Log.e("currentsong", "bad");
            }
       // } else {

            genrefrag gfrag = (genrefrag) getFragmentManager().findFragmentById(R.id.genrefrag);
            String mygval = gfrag.Updategenreval(view);
            FilterFrag ffrag = (FilterFrag) getFragmentManager().findFragmentById(R.id.filterfrag);
            ffrag.filterresultsFunc(view, mygval);

       // }
    }*/
                 /* public void deletesongfromplaylist(String playlisturi, String songuri){
        List<String> playlisturiparts = playlisturisplit(playlisturi);
        Map<String, Object> uris = new HashMap<>();
        Map<String, Object> body = new HashMap<>();
        //uris.put("uris:", currentsong.get_songid());
        List<String> songstoremove = new ArrayList<>();
        //songstoremove.add(songuri);
        uris.put("uris", songuri);
        body.put("track", songuri);

       // List<String> songstoremove = new ArrayList<>();
        songstoremove.add(body);
        // spotify:track:4sy3B9TPNMIze8oFrDJnUN
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"uri", songuri});
        Parcel p = Parcel.obtain();
        p.writeValue("spotify:track:1wPRqq1czM3ny6189e56mq");
        Track track = new Track();

        TrackToRemove trackToRemove = new TrackToRemove() ;

                //new TrackToRemove();
        trackToRemove.writeToParcel(p, 0);
        TracksToRemove t = new TracksToRemove();
        t.writeToParcel("");
        t.tracks.add(songstoremove);
        t.writeToParcel(p, 0);
        // if (currentsong.get_favandsec()%5!=0){
        spotify.removeTracksFromPlaylist(playlisturiparts.get(0), playlisturiparts.get(1), t, new Callback<SnapshotId>(){
            @Override
            public void success(SnapshotId snapshotId, Response response) {
                String p = snapshotId.snapshot_id;
                Log.e("Success", "remove");
            }
            @Override
            public void failure(RetrofitError error) {
                Log.d("playlistfailure", error.toString());
            }
        });
    }*/
//song.set_songid(t.get(len).track.uri);
// Log.d("Playlist success song", t.get(len).track.id);
//song.set_songname(n);
//song.set_popularity(p);
// List<ArtistSimple> simpleArtists = t.get(len).track.artists;
//song.set_artist(simpleArtists.get(0).name);
//Log.d("Artists", simpleArtists.get(0).name);
// AlbumSimple simpleAlbum = t.get(len).track.album;
                 /* String genrestr = "";
                            // Log.d("Album success genres", theg.get(0));
                            if (p>75){
                                genrestr+= "popular, ";
                            }
                            for (int gr = 0; gr< artist.genres.size(); gr++) {
                                if ((gr+1)==artist.genres.size()){
                                    genrestr+= artist.genres.get(gr);
                                    //Log.d("Album genre", genrestr);
                                }else {
                                    genrestr += artist.genres.get(gr) + ", ";
                                    //Log.d("Album genre", genrestr);
                                }
                            }
                            song.set_spotifygenres(genrestr);

                        }*/
//Log.d("Album success", artist.name);
// Log.d("Album success genres", theg.get(0));
                           /* song.set_acousticness(audiofeat.acousticness);
                            song.set_danceability(audiofeat.danceability);
                            song.set_energy(audiofeat.energy);
                            song.set_instrumentalness(audiofeat.instrumentalness);
                            song.set_liveness(audiofeat.liveness);
                            song.set_loudness(audiofeat.loudness);
                            song.set_speechiness(audiofeat.speechiness);
                            song.set_valence(audiofeat.valence);
                            song.set_tempo(audiofeat.tempo);
*/

