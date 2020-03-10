package com.sparx1126.a2020_scouting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceMatch;
import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceRank;
import com.sparx1126.a2020_scouting.BlueAllianceData.BlueAllianceTeam;
import com.sparx1126.a2020_scouting.Data.OurRankingData;
import com.sparx1126.a2020_scouting.Utilities.BlueAllianceNetwork;
import com.sparx1126.a2020_scouting.Utilities.GetMail;
import com.sparx1126.a2020_scouting.Data.ScoutingData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class Home extends AppCompatActivity {
    static private String TAG = "Sparx: ";
    static private String HEADER = "Home: ";

    private SharedPreferences settings;
    private static BlueAllianceNetwork blueAlliance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d(TAG, HEADER + "onCreate");

        settings = getSharedPreferences(getString(R.string.SPARX_PREFS), 0);
        blueAlliance = BlueAllianceNetwork.getInstance();

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        /*AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_our_rankings, R.id.navigation_match, R.id.navigation_scoutings, R.id.navigation_settings)
                .build();*/
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_view_data, R.id.navigation_scouting, R.id.navigation_settings)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    public void onBackPressed() {
        Log.e(TAG, HEADER + "Reconfigure in Settings to go back to logging screen...");
        Toast.makeText(Home.this, "Reconfigure in Settings to go back to logging screen...", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRestart(){
        super.onRestart();
        Log.d(TAG, HEADER + "onRestart");
        if(!settings.getBoolean(getResources().getString(R.string.tablet_Configured), false)) {
            Log.d(TAG, HEADER + "going back to welcome screen");
            finish();
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, HEADER + "onStart");
        String selectedEvent = settings.getString(getResources().getString(R.string.pref_SelectedEvent), "");
        if(!selectedEvent.isEmpty()) {
            blueAlliance.downloadEventRanks(selectedEvent, new BlueAllianceNetwork.Callback() {
                @Override
                public void handleFinishDownload(final String _data) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                        if (isValidJsonObject(_data)) {
                            Log.d(TAG, HEADER + "downLoaded event ranks");
                            BlueAllianceRank.parseJson(_data);
                        } else {
                            Log.e(TAG, HEADER + "Internet returned BAD DATA for EventRanks, try another wifi!");
                            Toast.makeText(Home.this, "Internet returned BAD DATA for EventRanks, try another wifi!", Toast.LENGTH_LONG).show();
                        }
                        }
                    });
                }
            });

            blueAlliance.downloadEventTeams(selectedEvent, new BlueAllianceNetwork.Callback() {
                @Override
                public void handleFinishDownload(final String _data) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                        if (isValidJsonArray(_data)) {
                            Log.d(TAG, HEADER + "downLoaded event teams");
                            BlueAllianceTeam.parseJson(_data);
                        } else {
                            Log.e(TAG, HEADER + "Internet returned BAD DATA for EventTeams, try another wifi!");
                            Toast.makeText(Home.this, "Internet returned BAD DATA for EventTeams, try another wifi!", Toast.LENGTH_LONG).show();
                        }
                        }
                    });
                }
            });

            blueAlliance.downloadEventMatches(selectedEvent, new BlueAllianceNetwork.Callback() {
                @Override
                public void handleFinishDownload(final String _data) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                        if (isValidJsonArray(_data)) {
                            Log.d(TAG, HEADER + "downLoaded event matches");
                            BlueAllianceMatch.parseDataToBAMMap(_data);
                            if(ScoutingData.getData().isEmpty()){
                                String prefColorStr = getResources().getString(R.string.red_alliance);
                                boolean blueAllianceChosen = settings.getBoolean(getResources().getString(R.string.pref_BlueAlliance), false);
                                if(blueAllianceChosen){
                                    prefColorStr = getResources().getString(R.string.blue_alliance);
                                }

                                String prefPositionStr = getResources().getString(R.string.position_1);
                                int position = settings.getInt(getResources().getString(R.string.pref_TeamPosition), 0);
                                if(position == 2){
                                    prefPositionStr = getResources().getString(R.string.position_2);
                                }
                                else if(position == 3){
                                    prefPositionStr = getResources().getString(R.string.position_3);
                                }
                                String pref_SelectedEvent = settings.getString(getResources().getString(R.string.pref_SelectedEvent), "");
                                ScoutingData.initializeData(blueAllianceChosen, position, pref_SelectedEvent, prefColorStr,
                                        prefPositionStr);
                            }
                        } else {
                            Log.e(TAG, HEADER + "Internet returned BAD DATA for EventMatches, try another wifi!");
                            Toast.makeText(Home.this, "Internet returned BAD DATA for EventMatches, try another wifi!", Toast.LENGTH_LONG).show();
                        }
                        }
                    });
                }
            });

            GetMail email = new GetMail(Home.this, settings.getString(getString(R.string.EMAIL), ""),
                    settings.getString(getString(R.string.PASSWORD), ""),
                    new GetMail.Callback() {
                        @Override
                        public void handleFinishDownload(Map<String, JSONObject> mails) {
                            Log.d(TAG, HEADER + "email handleFinishDownload");
                            ScoutingData.parseJsons(mails);
                            String pref_SelectedEvent = settings.getString(getResources().getString(R.string.pref_SelectedEvent), "");
                            OurRankingData.parseJsons(pref_SelectedEvent, mails);
                        }
                    });
        }

        boolean tabletConfigured = settings.getBoolean(getResources().getString(R.string.tablet_Configured), false);
        if(!tabletConfigured) {
            Log.d(TAG, HEADER + "tablet not configured");
            startActivity(new Intent(Home.this, Settings.class));
        }
    }

    private boolean isValidJsonArray(String _data) {
        try {
            new JSONArray(_data);
        } catch (JSONException jsExcp) {
            return false;
        }
        return true;
    }

    private boolean isValidJsonObject(String _data) {
        try {
            new JSONObject(_data);
        } catch (JSONException jsExcp) {
            return false;
        }
        return true;
    }
}