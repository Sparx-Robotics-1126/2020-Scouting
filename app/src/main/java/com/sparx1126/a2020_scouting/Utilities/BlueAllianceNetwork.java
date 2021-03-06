package com.sparx1126.a2020_scouting.Utilities;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class BlueAllianceNetwork {
    private static final String TAG = "Sparx: ";
    private static final String HEADER = "BlueAllianceNetwork: ";

    public interface Callback {
        void handleFinishDownload(String _data);
    }

    private static BlueAllianceNetwork instance;
    private static OkHttpClient client;
    //The basic url to be, on which will be appened the correct tails in each method
    private static final String BASE_URL = "https://www.thebluealliance.com/api/v3/";
    private static final String AUTH_TAG = "X-TBA-Auth-Key";
    private static final String BLUE_ALLIANCE_KEY = "4EFyOEdszrGNcCJuibGSr6W92SjET2cfhx2QU9Agxv3LNASra77KcsCEv5GnoSIq";
    private static final String YEAR = "2020";
    //Not set as final as it is intened to be changed at will in settings menu
    private static String teamKey = "";
    // intention is for {event_key} to be substituted in the url handed to the okhttp client
    private static String EVENT_TEAMS_URL_TAIL = "event/{event_key}/teams";
    private static String EVENT_RANKS_URL_TAIL = "event/{event_key}/rankings";
    // intention is for {team_key} to be substituted in the url handed to the okhttp client
    private static String TEAM_EVENTS_URL_TAIL = "team/{team_key}/events/" + YEAR;
    private static String EVENT_MATCHES_URL_TAIL = "event/{event_key}/matches/simple";

    //Private constructor> only one instance per app instance
    private BlueAllianceNetwork() {
        client = new OkHttpClient();
    }

    public static synchronized BlueAllianceNetwork getInstance() {
        if (instance == null) {
            instance = new BlueAllianceNetwork();
        }
        return instance;
    }

    public static String geteamKey() {
        return teamKey;
    }

    public static void seteamKey(String key) {
        teamKey = key;
    }

    public void downloadEvents(Context _context, final Callback _callback) {
        final ProgressDialog progressDialog = ProgressDialog.show(_context, "Downloading Events", "Please wait...", false, false);
        String url = BASE_URL + TEAM_EVENTS_URL_TAIL.replace("{team_key}", teamKey);
        fetchBlueAllianceData(url, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, HEADER + "downloadEvents onFailure");
                progressDialog.dismiss();
                _callback.handleFinishDownload("");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String rtrn = "";
                if (response.isSuccessful()) {
                    rtrn = response.body().string();
                    if (rtrn == null) {
                        Log.e(TAG, HEADER + "downloadEvents response.body().string() is null");
                        rtrn = "";
                    }
                } else {
                    Log.e(TAG, HEADER + "downloadEvents response not successful");
                }
                progressDialog.dismiss();
                _callback.handleFinishDownload(rtrn);
            }
        });
    }

    public void downloadEventTeams(Context _context, String eventKey, final Callback callback) {
        final ProgressDialog progressDialog = ProgressDialog.show(_context, "Downloading Events Teams", "Please wait...", false, false);
        String url = BASE_URL + EVENT_TEAMS_URL_TAIL.replace("{event_key}", eventKey);
        fetchBlueAllianceData(url, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, HEADER + "downloadEventTeams onFailure");
                progressDialog.dismiss();
                callback.handleFinishDownload("");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String rtrn = "";
                if (response.isSuccessful()) {
                    rtrn = response.body().string();
                    if (rtrn == null) {
                        Log.e(TAG, HEADER + "downloadEventTeams response.body().string() is null");
                        rtrn = "";
                    }
                } else {
                    Log.e(TAG, HEADER + "downloadEventTeams response not successful");
                }
                progressDialog.dismiss();
                callback.handleFinishDownload(rtrn);
            }
        });

    }

    public void downloadEventMatches(Context _context, String eventKey, final Callback callback) {
        final ProgressDialog progressDialog = ProgressDialog.show(_context, "Downloading Events Matches", "Please wait...", false, false);
        String url = BASE_URL + EVENT_MATCHES_URL_TAIL.replace("{event_key}", eventKey);
        fetchBlueAllianceData(url, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, HEADER + "downloadEventMatches onFailure");
                progressDialog.dismiss();
                callback.handleFinishDownload("");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String rtrn = "";
                if (response.isSuccessful()) {
                    rtrn = response.body().string();
                    if (rtrn == null) {
                        Log.e(TAG, HEADER + "downloadEventMatches response.body().string() is null");
                        rtrn = "";
                    }
                } else {
                    Log.e(TAG, HEADER + "downloadEventMatches response not successful");
                }
                progressDialog.dismiss();
                callback.handleFinishDownload(rtrn);
            }
        });

    }

    public void downloadEventRanks(Context _context, String eventKey, final Callback callback) {
        final ProgressDialog progressDialog = ProgressDialog.show(_context, "Downloading Events Ranks", "Please wait...", false, false);
        String url = BASE_URL + EVENT_RANKS_URL_TAIL.replace("{event_key}", eventKey);
        fetchBlueAllianceData(url, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, HEADER + "downloadEventRanks onFailure");
                progressDialog.dismiss();
                callback.handleFinishDownload("");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String rtrn = "";
                if (response.isSuccessful()) {
                    rtrn = response.body().string();
                    if (rtrn == null) {
                        Log.e(TAG, HEADER + "downloadEventRanks response.body().string() is null");
                        rtrn = "";
                    }
                } else {
                    Log.e(TAG, HEADER + "downloadEventRanks response not successful");
                }
                progressDialog.dismiss();
                callback.handleFinishDownload(rtrn);
            }
        });
    }

    private void fetchBlueAllianceData(String _url, okhttp3.Callback _callback) {
        Request requestEvents = new Request.Builder()
                .addHeader(AUTH_TAG, BLUE_ALLIANCE_KEY)
                .url(_url)
                .build();
        client.newCall(requestEvents).enqueue(_callback);
    }
}
