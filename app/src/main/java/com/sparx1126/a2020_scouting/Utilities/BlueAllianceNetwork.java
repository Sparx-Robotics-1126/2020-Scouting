package com.sparx1126.a2020_scouting.Utilities;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class BlueAllianceNetwork {

    public interface Callback {
        void handleFinishDownload(String _data);
    }

    private static BlueAllianceNetwork instance;
    private static OkHttpClient client;
    //The basic url to be, on which will be appened the correct tails in each method
    private final String BASE_URL="https://www.thebluealliance.com/api/v3/";
    private final String TAG = "BlueAllianceNetwork";
    private final String AUTH_TAG="X-TBA-Auth-Key";
    private static final String BLUE_ALLIANCE_KEY = "4EFyOEdszrGNcCJuibGSr6W92SjET2cfhx2QU9Agxv3LNASra77KcsCEv5GnoSIq";
    private static final String YEAR = "2019";
    //Not set as final as it is intened to be changed at will in settings menu
    private static  String teamKey = "frc1126";
    // intention is for {event_key} to be substituted in the url handed to the okhttp client
    private static String EVENT_TEAMS_URL_TAIL = "event/{event_key}/teams";
    private static String EVENT_RANKS_URL_TAIL = "event/{event_key}/rankings";
    // intention is for {team_key} to be substituted in the url handed to the okhttp client
    private static String TEAM_EVENTS_URL_TAIL = "team/{team_key}/events/" + YEAR;
    private static String EVENT_MATCHES_URL_TAIL = "event/{event_key}/matches/simple";
 //Private constructor> only one instance per app instance
    private BlueAllianceNetwork(){client=new OkHttpClient();}

    public  static synchronized BlueAllianceNetwork getInstance(){
        if (instance==null){
            instance=new BlueAllianceNetwork();
        }
        return instance;
    }
    public  String geteamKey() {
        return teamKey;
    }
    public void seteamKey(String key) {
        teamKey=key;
    }

    public void downloadEvents( final Callback callback){
        String url = BASE_URL + TEAM_EVENTS_URL_TAIL.replace("{team_key}",teamKey );
        fetchBlueAllianceData(url, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.handleFinishDownload("");

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    String rtrn = response.body().string();
                    //Log.e("YEE", rtrn);
                    assert rtrn != null;
                    callback.handleFinishDownload(rtrn);

                } else{ throw new AssertionError(response.message()+this);

                }

            }
        });

    }

    public void downloadEventTeams(String eventKey, final Callback callback){
        String url= BASE_URL+EVENT_TEAMS_URL_TAIL.replace("{event_key}",eventKey );
        fetchBlueAllianceData(url, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.handleFinishDownload("");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    String rtrn = response.body().string();
                    assert rtrn != null;
                    callback.handleFinishDownload(rtrn);

                } else{ throw new AssertionError(response.message()+this);

                }

            }
        });

    }

    public void downloadEventMatches(String eventKey,final Callback callback){
        String url = BASE_URL+TEAM_EVENTS_URL_TAIL.replace("{event_key}", eventKey);
        fetchBlueAllianceData(url, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.handleFinishDownload("");            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    String rtrn = response.body().string();
                    assert rtrn != null;
                    callback.handleFinishDownload(rtrn);

                } else{ throw new AssertionError(response.message()+this);

                }

            }
        });

    }

    public void downloadEventRanks(String eventKey,final Callback callback){
        String url = BASE_URL+EVENT_RANKS_URL_TAIL.replace("{event_key}", eventKey);
        fetchBlueAllianceData(url, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.handleFinishDownload("");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException { if(response.isSuccessful()){
                String rtrn = response.body().string();
                assert rtrn != null;
                callback.handleFinishDownload(rtrn);

            } else{ throw new AssertionError(response.message()+this);

            }

            }
        });

    }


    private void fetchBlueAllianceData(String _url, okhttp3.Callback _callback) {
        Request requestEvents = new Request.Builder()
                .addHeader(AUTH_TAG, BLUE_ALLIANCE_KEY)
                .url(_url)
                .build();
        Log.e("JT",_url);
        Log.e("JT",AUTH_TAG);
        Log.e("JT",BLUE_ALLIANCE_KEY);



        client.newCall(requestEvents).enqueue(_callback);
    }



}
