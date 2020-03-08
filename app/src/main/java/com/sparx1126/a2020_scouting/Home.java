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
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_our_rankings,R.id.navigation_match, R.id.navigation_settings, R.id.navigation_scoutings)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    public void onBackPressed() {
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
                            BlueAllianceRank.parseJson(_data);
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
                            BlueAllianceTeam.parseJson(_data);
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
                            BlueAllianceMatch.parseDataToBAMMap(_data);
                            if(ScoutingData.getData().isEmpty()){
                                Map<String, BlueAllianceMatch> matches = BlueAllianceMatch.getMatches();
                                for(BlueAllianceMatch data : matches.values()){
                                    ScoutingData emptyData = new ScoutingData(data.getMatchNum());
                                    ScoutingData.getData().put(data.getMatchNum(), emptyData);
                                }
                            }
                        }
                    });
                }
            });

            GetMail email = new GetMail(Home.this);
            email.downloadMail(
                    settings.getString(getString(R.string.EMAIL), ""),
                    settings.getString(getString(R.string.PASSWORD), ""),
                    new GetMail.Callback() {
                @Override
                public void handleFinishDownload(Map<String, JSONObject> mails) {
                    for(Map.Entry<String, JSONObject> mail :  mails.entrySet()){
                        String subject = mail.getKey();
                        int indexStart= subject.indexOf("frc");
                        int endIndex = subject.indexOf(".", subject.indexOf(".")+1);
                        String team =  subject.substring(indexStart, endIndex);
                        Log.e(TAG, HEADER + "Hiram: " + team);
                        Log.e(TAG, HEADER + "Sohail" + mail.getValue().toString());
                        OurRankingData.parseJson(team, mail.getValue().toString());
                        Log.e(TAG, HEADER + "Sohail2 " + OurRankingData.getBallsScoredOnBottomTele().size());
                    }
                    OurRankingData.calculate();
                }
            });
        }

        boolean tabletConfigured = settings.getBoolean(getResources().getString(R.string.tablet_Configured), false);
        if(!tabletConfigured) {
            Log.d(TAG, HEADER + "tablet not configured");
            startActivity(new Intent(Home.this, Settings.class));
        }
    }
}